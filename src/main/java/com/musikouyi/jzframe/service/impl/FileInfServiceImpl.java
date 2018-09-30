package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.common.jpaQuery.Criteria;
import com.musikouyi.jzframe.common.jpaQuery.Restrictions;
import com.musikouyi.jzframe.domain.entity.FileInf;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.enums.BoolCodeEnum;
import com.musikouyi.jzframe.domain.enums.BusinessTypeCodeEnum;
import com.musikouyi.jzframe.dto.FileInfDto;
import com.musikouyi.jzframe.dto.FileInfListReq;
import com.musikouyi.jzframe.repository.FileInfRepository;
import com.musikouyi.jzframe.service.IFileInfService;
import com.musikouyi.jzframe.utils.FileUrlHelper;
import com.musikouyi.jzframe.utils.ResultUtil;
import com.musikouyi.jzframe.utils.WebContextHolder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class FileInfServiceImpl implements IFileInfService {

    @Autowired
    private FileInfRepository fileInfRepository;

    @Override
    @Transactional
    public Result saveTempFile(String fileName, InputStream fileStream) {
        FileOutputStream fileOutputStream = null;
        try {
            String fileTypeNm = FilenameUtils.getExtension(fileName);
            String fileUUID = UUID.randomUUID().toString();
            String filePath = ResourceUtils.getURL(Global.CLASSPATH).getPath() + Global.TEMP_PATH + fileUUID + "." + fileTypeNm;
            fileOutputStream = new FileOutputStream(filePath);
            IOUtils.copy(fileStream, fileOutputStream);
            FileInfDto fileInfDto = new FileInfDto();
            fileInfDto.setFileNm(fileName);
            fileInfDto.setFilePath(filePath);
            fileInfDto.setFileTypeNm(fileTypeNm);
            System.out.println(ResourceUtils.getURL(Global.CLASSPATH).getPath());
            System.out.println(WebContextHolder.getContextPath());
            System.out.println(WebContextHolder.getWarPath());
            fileInfDto.setFileUrl(filePath);
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
     * 同步某个业务下的资源文件集合.
     * 如果ID不在已有里的，就添加；如果有在的，就不管；如果不在的，就删除(标记为failUse，等整理时再批量删除)。
     * @param businessTypeCode
     * @param fileInfBarDtoList
     * @return 替换用的map，将文档内容里的temp url替换为upload url
     */
    @Override
    @Transactional
    public Map<String, String> syncFileInfList(BusinessTypeCodeEnum businessTypeCode, Integer businessObjectId, List<FileInfDto> fileInfBarDtoList){
        List<FileInf> savedFileInfList = this.findByBusinessTypeCodeBusinessObjectId(businessTypeCode, businessObjectId);

        List<Integer> savedFileInfIdList = new ArrayList<>();
        for(FileInf savedFileInf : savedFileInfList){
            savedFileInfIdList.add(savedFileInf.getFileInfId());
        }

        List<FileInfDto> addFileInfBarDto = new ArrayList<>();

        List<Integer> updateFileInfIdList = new ArrayList<>();
        for(FileInfDto fileInfBarDto : fileInfBarDtoList){
            if(fileInfBarDto.getFileInfId() < 0){ //新增
                addFileInfBarDto.add(fileInfBarDto);
            }else{
                updateFileInfIdList.add(fileInfBarDto.getFileInfId());
            }
        }

        Collection<Integer> deleteList = CollectionUtils.subtract(savedFileInfIdList, updateFileInfIdList);
        for(Integer deleteId: deleteList){
            FileInf toDelete = fileInfRepository.findById(deleteId).get();
            toDelete.setIfFailUse(BoolCodeEnum.YES.getMsg());
            fileInfRepository.save(toDelete); //软删除
        }

        Map<String, String> result = new HashMap<>();
        Date now = new Date();
        for(FileInfDto fileInfBarDto: addFileInfBarDto){
            File tempFile = new File(WebContextHolder.getWarPath() + File.separator + fileInfBarDto.getFilePath());
            Calendar calender = Calendar.getInstance();

            String moveDirPath = new StringBuilder(Global.UPLOAD_DIR)
                    .append(calender.get(Calendar.YEAR))
                    .append(File.separator)
                    .append(calender.get(Calendar.MONTH))
                    .append(File.separator)
                    .append(calender.get(Calendar.DAY_OF_MONTH)).toString();
            try {
                FileUtils.moveFileToDirectory(tempFile, new File(WebContextHolder.getWarPath() + File.separator + moveDirPath), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            FileInf fileInf = new FileInf();
            fileInf.setFileNm(fileInfBarDto.getFileNm());
            fileInf.setFileTypeNm(fileInfBarDto.getFileTypeNm());
            fileInf.setIfFailUse(BoolCodeEnum.NO.getMsg());
            fileInf.setFileTime(now);
            fileInf.setFilePath(moveDirPath + File.separator + FilenameUtils.getName(fileInfBarDto.getFilePath()));
            fileInf.setBusinessObjectId(businessObjectId);
            fileInf.setBusinessTypeCode(businessTypeCode.getMsg());
            fileInfRepository.save(fileInf);
            result.put(fileInfBarDto.getFileUrl(), FileUrlHelper.getFileSystemUrl(fileInf.getFilePath()));
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<FileInf> findByBusinessTypeCodeBusinessObjectId(BusinessTypeCodeEnum businessTypeCode, Integer businessObjectId) {
        return findByBusinessTypeCodeBusinessObjectId(businessTypeCode, businessObjectId, false);
    }

    @Transactional(readOnly = true)
    public List<FileInf> findByBusinessTypeCodeBusinessObjectId(BusinessTypeCodeEnum businessTypeCode, Integer businessObjectId, boolean ifFailUse) {
        FileInfListReq fileInfListReq = new FileInfListReq();
        fileInfListReq.setBusinessTypeCode(businessTypeCode.getMsg());
        fileInfListReq.setBusinessObjectId(businessObjectId);
        fileInfListReq.setIfFailUse(BoolCodeEnum.toCode(ifFailUse));
        return findSysFileInfList(fileInfListReq);
    }

    @Transactional(readOnly = true)
    public List<FileInf> findSysFileInfList(FileInfListReq fileInfListReq) {
        return fileInfRepository.findAll(
                new Criteria<FileInf>()
                        .add(Restrictions.eq(FileInf.BUSINESS_TYPE_CODE, fileInfListReq.getBusinessTypeCode()))
                        .add(Restrictions.eq(FileInf.IF_FAIL_USE, fileInfListReq.getIfFailUse()))
                        .add(Restrictions.eq(FileInf.BUSINESS_OBJECT_ID, fileInfListReq.getBusinessObjectId()))
                        .add(Restrictions.like(FileInf.FILE_NM, fileInfListReq.getFileNm()))
        );
    }
}
