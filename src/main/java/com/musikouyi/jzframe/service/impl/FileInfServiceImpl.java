package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.common.helper.SmallPictImageQueueHelper;
import com.musikouyi.jzframe.common.jpaQuery.Criteria;
import com.musikouyi.jzframe.common.jpaQuery.Restrictions;
import com.musikouyi.jzframe.common.queue.vo.SmallPictEventData;
import com.musikouyi.jzframe.domain.entity.FileInf;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.entity.SmallPict;
import com.musikouyi.jzframe.domain.entity.SmallPictSetup;
import com.musikouyi.jzframe.domain.enums.BoolCodeEnum;
import com.musikouyi.jzframe.dto.FileInfDto;
import com.musikouyi.jzframe.repository.FileInfRepository;
import com.musikouyi.jzframe.repository.SmallPictRepository;
import com.musikouyi.jzframe.repository.SmallPictSetupRepository;
import com.musikouyi.jzframe.service.IFileInfService;
import com.musikouyi.jzframe.utils.FileUrlHelper;
import com.musikouyi.jzframe.utils.ResultUtil;
import com.musikouyi.jzframe.utils.SmallPictUtil;
import com.musikouyi.jzframe.utils.WebContextHolder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
public class FileInfServiceImpl implements IFileInfService {

    @Autowired
    private FileInfRepository fileInfRepository;

    @Autowired
    private SmallPictSetupRepository smallPictSetupRepository;

    @Autowired
    private SmallPictRepository smallPictRepository;

    private SmallPictImageQueueHelper smallPictImageQueueHelper;

    public void setSmallPictImageQueueHelper(SmallPictImageQueueHelper smallPictImageQueueHelper) {
        this.smallPictImageQueueHelper = smallPictImageQueueHelper;
    }

    private static int seed = 0;
    private static final int TEMP_FILE_CACHE_SIZE = 1000;

    /**
     * 将文件保存到临时目录.
     *
     * @param fileName
     * @param fileStream
     * @return
     */
    @Override
    @Transactional
    public Result saveTempFile(String fileName, InputStream fileStream) {
        FileOutputStream fileOutputStream = null;
        try {
            String fileTypeNm = FilenameUtils.getExtension(fileName);
            String fileUUID = UUID.randomUUID().toString();
            String filePath = new StringBuilder(Global.TEMP_DIR).append(File.separator).append(fileUUID).append(".").append(fileTypeNm).toString();
            StringBuilder outputFilePath = new StringBuilder(WebContextHolder.getWarPath()).append(File.separator).append(filePath);
            fileOutputStream = new FileOutputStream(outputFilePath.toString());
            IOUtils.copy(fileStream, fileOutputStream);
            FileInfDto fileInfDto = new FileInfDto();
            fileInfDto.setFileNm(fileName);
            fileInfDto.setFilePath(filePath);
            fileInfDto.setFileTypeNm(fileTypeNm);

            //TODO 下面语句可以添加CDN或流量地址转换
            fileInfDto.setFileUrl(new StringBuilder(WebContextHolder.getContextPath()).append("/").append(Global.TEMP_DIR).append("/").append(fileUUID).append(".").append(fileTypeNm).toString());
            synchronized (this) {
                fileInfDto.setFileInfId(-(((int) (System.currentTimeMillis() & 0xffffffL) << 7) + seed)); //保证在一个会话里时间ID不一样，为负数的是临时ID
                seed = (seed + 1) & 0xaf;
            }
            tempFileCache.put(WebContextHolder.getSessionContextStore().getSessionId() + fileInfDto.getFileInfId(), fileInfDto);
            return ResultUtil.success(fileInfDto);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (fileOutputStream != null) {
                IOUtils.closeQuietly(fileOutputStream);
            }
        }
    }

    /**
     * key值采用sessionId+时间标记的格式，例如"9dff284715064894881984838e-13352353"
     */
    private Map<String, FileInfDto> tempFileCache = new LinkedHashMap<String, FileInfDto>(1000) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, FileInfDto> eldest) {
            // 当前记录数大于设置的最大的记录数，删除最旧记录（即最近访问最少的记录）
            return size() > TEMP_FILE_CACHE_SIZE;
        }
    };

    /**
     * 同步多个资源文件集合.
     * 如果ID不在已有里的，就添加；如果有在的，就不管；如果不在的，就删除。
     *
     * @param businessClassNm  文件类名称，删除时应将对应的记录下的文件也清理
     * @param businessObjectId 业务对象ID，新增时保存用
     * @param savedFileInfIds  原保存的文件ID列表字符串
     * @param newFileInfIds    当前保存更新后的文件ID列表
     * @return SyncFileInfResult 包括替换用的map，将文档内容里的temp url替换为upload url，以及替换后新增的文件ID列表
     * @Deprecated 使用syncBusinessObject
     */
    private SyncFileInfResult syncFileInfList(String businessClassNm, Integer businessObjectId, String savedFileInfIds, String newFileInfIds) {

        List<FileInfDto> fileInfBarDtoList = loadFileInfDtoList(newFileInfIds);

        List<FileInf> savedFileInfList = new ArrayList<FileInf>();
        if (!StringUtils.isEmpty(savedFileInfIds)) {   //待检查
            for (String oldFileInfId : savedFileInfIds.split(Global.DEFAULT_TEXT_SPLIT_CHAR)) {
                savedFileInfList.add(fileInfRepository.getOne(new Integer(oldFileInfId)));
            }
        }

        List<Integer> savedFileInfIdList = new ArrayList<>();
        for (FileInf savedFileInf : savedFileInfList) {
            savedFileInfIdList.add(savedFileInf.getFileInfId());
        }

        List<FileInfDto> addFileInfBarDto = new ArrayList<>();

        List<Integer> updateFileInfIdList = new ArrayList<>();
        for (FileInfDto fileInfBarDto : fileInfBarDtoList) {
            if (fileInfBarDto.getFileInfId() < 0) { //新增
                addFileInfBarDto.add(fileInfBarDto);
            } else {
                updateFileInfIdList.add(fileInfBarDto.getFileInfId());
            }
        }

        Collection<Integer> deleteList = CollectionUtils.subtract(savedFileInfIdList, updateFileInfIdList);
        for (Integer deleteId : deleteList) {
            FileInf fileInf = fileInfRepository.getOne(deleteId);
            FileUtils.deleteQuietly(new File(WebContextHolder.getWarPath() + File.separator + fileInf.getFilePath()));

            fileInfRepository.delete(fileInf);

            //删除所有小图
            for (SmallPict smallPict : smallPictRepository.findByFileInfId(fileInf.getFileInfId())) {
                String smallPictPath = new StringBuilder(FilenameUtils.removeExtension(fileInf.getFilePath().replaceFirst(Global.UPLOAD_DIR, Global.SMALL_PICT_DIR)))
                        .append("_")
                        .append(smallPict.getSmallPictWidth())
                        .append(Global.SMALL_PICT_SIZE_SPLIT_CHAR)
                        .append(smallPict.getSmallPictHeight())
                        .append(".")
                        .append(SmallPictUtil.DEFAULT_OUTPUT_FORMAT)
                        .toString();
                FileUtils.deleteQuietly(new File(WebContextHolder.getWarPath() + File.separator + smallPictPath));

                smallPictRepository.delete(smallPict);
            }
        }

        Map<String, String> replaceMap = new HashMap<String, String>();
        Map<Integer, Integer> replaceIdMap = new HashMap<Integer, Integer>();
        Date now = new Date();
        for (FileInfDto fileInfBarDto : addFileInfBarDto) {
            File tempFile = new File(WebContextHolder.getWarPath() + File.separator + fileInfBarDto.getFilePath());
            Calendar calender = Calendar.getInstance();

            String moveDirPath = new StringBuilder(Global.UPLOAD_DIR)
                    .append(File.separator)
                    .append(calender.get(Calendar.YEAR))
                    .append(File.separator)
                    .append(calender.get(Calendar.MONTH) + 1)
                    .append(File.separator)
                    .append(calender.get(Calendar.DAY_OF_MONTH)).toString();
            try {
                //本地文件的策略
                FileUtils.moveFileToDirectory(tempFile, new File(WebContextHolder.getWarPath() + File.separator + moveDirPath), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            FileInf fileInf = new FileInf();
            fileInf.setFileNm(fileInfBarDto.getFileNm());
            fileInf.setFileTypeNm(fileInfBarDto.getFileTypeNm());
            fileInf.setBusinessClassNm(businessClassNm);
            boolean isPict = fileInf.getFileTypeNm().equalsIgnoreCase("png") || fileInf.getFileTypeNm().equalsIgnoreCase("jpg") || fileInf.getFileTypeNm().equalsIgnoreCase("gif");
            fileInf.setIfPict(BoolCodeEnum.fromValue(isPict).getMsg());
            fileInf.setFileTime(now);
            fileInf.setFileSizeKb(new Long(tempFile.length() / 1024).intValue());
            fileInf.setFilePath(moveDirPath + File.separator + FilenameUtils.getName(fileInfBarDto.getFilePath()));
            fileInf.setBusinessObjectId(businessObjectId);
            fileInfRepository.save(fileInf);

            //小图逻辑：同步生成150x150内裁切小图，然后异步生成其它小图
            if (isPict) {
                SmallPict smallPict = new SmallPict();
                int fileSizeKb = SmallPictUtil.generateSmallPict(
                        Global.DEFAULT_SMALL_PICT_SIZE,
                        Global.DEFAULT_SMALL_PICT_SIZE,
                        WebContextHolder.getWarPath() + File.separator + fileInf.getFilePath(),
                        true
                );
                if (fileSizeKb != -1) { //原位置有图片则忽略
                    smallPict.setFileInfId(fileInf.getFileInfId());
                    smallPict.setFileSizeKb(fileSizeKb);
                    smallPict.setSmallPictWidth(Global.DEFAULT_SMALL_PICT_SIZE);
                    smallPict.setSmallPictHeight(Global.DEFAULT_SMALL_PICT_SIZE);
                    smallPict.setFileTime(new Date());
                    smallPictRepository.save(smallPict);
                }
            }

            replaceIdMap.put(fileInfBarDto.getFileInfId(), fileInf.getFileInfId());

            replaceMap.put(fileInfBarDto.getFileUrl(), FileUrlHelper.getFileSystemUrl(fileInf.getFilePath()));
        }
        List<String> fileInfIds = new ArrayList<String>();
        for (FileInfDto fileInfDto : fileInfBarDtoList) {
            if (fileInfDto.getFileInfId() < 0) { //add
                fileInfIds.add(replaceIdMap.get(fileInfDto.getFileInfId()).toString());
            } else {
                fileInfIds.add(fileInfDto.getFileInfId().toString());
            }
        }
        return new SyncFileInfResult(replaceMap, StringUtils.join(fileInfIds, Global.DEFAULT_TEXT_SPLIT_CHAR));
    }

    /**
     * 根据ID获得文件信息列表，如果是负数从临时文件缓存读取，否则从数据库读取
     *
     * @param fileInfIds
     * @return
     */
    public List<FileInfDto> loadFileInfDtoList(String fileInfIds) {
        List<FileInfDto> result = new ArrayList<FileInfDto>();
        for (String fileInfIdStr : fileInfIds.split(Global.DEFAULT_TEXT_SPLIT_CHAR)) {
            if (fileInfIdStr.startsWith("-")) {
                FileInfDto fileInfDto = tempFileCache.get(WebContextHolder.getSessionContextStore().getSessionId() + fileInfIdStr);
                if (fileInfDto != null) {
                    result.add(fileInfDto);
                }
            } else {
                FileInfDto fileInfDto = new FileInfDto();
                FileInf fileInf = fileInfRepository.getOne(Integer.parseInt(fileInfIdStr));
                BeanUtils.copyProperties(fileInf, fileInfDto);

                //TODO 下面语句可以添加CDN或流量地址转换
                fileInfDto.setFileUrl(new StringBuilder(WebContextHolder.getContextPath()).append("/").append(fileInf.getFilePath().replace(File.separatorChar, '/')).toString());
                result.add(fileInfDto);
            }
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<FileInf> findSysFileInfList(Integer businessObjectId, String fileNm) {
        return fileInfRepository.findAll(
                new Criteria<FileInf>()
                        .add(Restrictions.eq(FileInf.BUSINESS_OBJECT_ID, businessObjectId))
                        .add(Restrictions.like(FileInf.FILE_NM, fileNm))
        );
    }

    /**
     * 根据对象来同步更新所有的文件字段，包括图片ID集合的字段，图片ID字段。返回所有要更新的URL地址
     *
     * @param newEntity
     * @param savedEntity
     * @param entityClass
     * @return replaceMap
     */
    @Override
    @Transactional
    public Map<String, String> syncBusinessObject(Integer businessObjectId, Object newEntity, Object savedEntity, Class<?> entityClass) {
        Map<String, String> replaceMap = new HashMap<>();
        for (Field field : entityClass.getDeclaredFields()) {
            if (Character.isUpperCase(field.getName().charAt(0))) {
                continue;
            }
            if (field.getName().endsWith(Global.PICT_ID_FIELD_SUFFIX)
                    || field.getName().endsWith(Global.PICT_IDS_FIELD_SUFFIX)
                    || field.getName().endsWith(Global.FILE_ID_FIELD_SUFFIX)
                    || field.getName().endsWith(Global.FILE_IDS_FIELD_SUFFIX)
                    ) {
                try {
                    Object newValue = BeanUtils.getPropertyDescriptor(entityClass, field.getName()).getReadMethod().invoke(newEntity);
                    Object oldValue = (savedEntity == null ? null : BeanUtils.getPropertyDescriptor(entityClass, field.getName()).getReadMethod().invoke(savedEntity));
                    SyncFileInfResult syncFileInfResult = syncFileInfList(entityClass.getSimpleName(), businessObjectId, oldValue == null ? "" : oldValue.toString(), newValue == null ? "" : newValue.toString());
                    if (field.getName().endsWith(Global.PICT_ID_FIELD_SUFFIX)) {
                        if (StringUtils.isEmpty(syncFileInfResult.getFileInfIds())) {
                            BeanUtils.getPropertyDescriptor(entityClass, field.getName()).getWriteMethod().invoke(newEntity, (Object) null);
                        } else {
                            BeanUtils.getPropertyDescriptor(entityClass, field.getName()).getWriteMethod().invoke(newEntity, new Integer(syncFileInfResult.getFileInfIds()));
                        }
                    } else {
                        BeanUtils.getPropertyDescriptor(entityClass, field.getName()).getWriteMethod().invoke(newEntity, syncFileInfResult.getFileInfIds());
                    }
                    replaceMap.putAll(syncFileInfResult.getReplaceMap());

                    //处理所有的小图
                    if (field.getName().endsWith(Global.PICT_ID_FIELD_SUFFIX) || field.getName().endsWith(Global.PICT_IDS_FIELD_SUFFIX)) {
                        syncFileSmallPicts(entityClass.getSimpleName(), field.getName(), syncFileInfResult.getFileInfIds(), field.getName().endsWith(Global.PICT_IDS_FIELD_SUFFIX));
                    }

                } catch (BeansException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    new RuntimeException(e);
                }
            }
        }
        return replaceMap;
    }

    public static final class SyncFileInfResult {
        private Map<String, String> replaceMap;
        private String fileInfIds;

        public SyncFileInfResult(Map<String, String> replaceMap, String fileInfIds) {
            this.replaceMap = replaceMap;
            this.fileInfIds = fileInfIds;
        }

        public Map<String, String> getReplaceMap() {
            return replaceMap;
        }

        public void setReplaceMap(Map<String, String> replaceMap) {
            this.replaceMap = replaceMap;
        }

        public String getFileInfIds() {
            return fileInfIds;
        }

        public void setFileInfIds(String fileInfIds) {
            this.fileInfIds = fileInfIds;
        }
    }

    private void syncFileSmallPicts(String className, String fieldName, String fileInfIds, boolean isMulti) {
        if (StringUtils.isNotBlank(fileInfIds)) {
            for (String fileIdStr : fileInfIds.split(Global.DEFAULT_TEXT_SPLIT_CHAR)) {
                String filePath = fileInfRepository.findById(new Integer(fileIdStr)).get().getFilePath();
                List<SmallPictSetup> smallPictSetupList = smallPictSetupRepository.findByBusinessClassNmAndBusinessFieldNm(className, fieldName);
                for (SmallPictSetup smallPictSetup : smallPictSetupList) {
                    if (StringUtils.isNoneBlank(smallPictSetup.getSmallPictSpec())) {
                        for (String specSetupStr : smallPictSetup.getSmallPictSpec().split(Global.DEFAULT_TEXT_SPLIT_CHAR)) { //解析配置字符串，例如120x80o,150x100,175x200i
                            boolean ifInnerCut = true;
                            int outSpecIndex = specSetupStr.indexOf('o');
                            if (outSpecIndex > -1) {
                                ifInnerCut = false;
                            }
                            String[] sizeArray = specSetupStr.replaceAll("i|o", "").split(Global.SMALL_PICT_SIZE_SPLIT_CHAR);
                            smallPictImageQueueHelper.publishEvent(new SmallPictEventData(smallPictSetup.getSmallPictSetupId(), new Integer(fileIdStr), filePath, Integer.parseInt(sizeArray[0]), Integer.parseInt(sizeArray[1]), ifInnerCut));
                        }
                    }
                }
            }
        }

    }
}
