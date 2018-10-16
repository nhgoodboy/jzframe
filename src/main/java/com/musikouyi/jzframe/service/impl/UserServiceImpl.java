package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.entity.User;
import com.musikouyi.jzframe.domain.enums.ResultEnum;
import com.musikouyi.jzframe.domain.enums.SexEnum;
import com.musikouyi.jzframe.domain.enums.UserStatusEnum;
import com.musikouyi.jzframe.dto.*;
import com.musikouyi.jzframe.repository.DeptRepository;
import com.musikouyi.jzframe.repository.RoleRepository;
import com.musikouyi.jzframe.repository.UserRepository;
import com.musikouyi.jzframe.service.IFileInfService;
import com.musikouyi.jzframe.service.IUserService;
import com.musikouyi.jzframe.utils.JwtTokenUtil;
import com.musikouyi.jzframe.utils.ResultUtil;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final DeptRepository deptRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, DeptRepository deptRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.deptRepository = deptRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public User getByAccount(String account) {
        return userRepository.findUserByAccount(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Result userInfo(Integer userId) {
        User user = userRepository.findById(userId).get();
        List<String> roleNameList = new ArrayList<>();
        roleNameList.add(roleRepository.findById(user.getRoleId()).get().getTips());
        UserInfoRespDto userInfoRespDto = new UserInfoRespDto();
        userInfoRespDto.setAvatar(SpringContextHolder.getBean(IFileInfService.class)
                .getSmallPictUrl(user.getUserHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE));
        userInfoRespDto.setAccount(user.getAccount());
        userInfoRespDto.setName(user.getName());
        userInfoRespDto.setSex(SexEnum.fromCode(user.getSex()));
        userInfoRespDto.setRoles(roleNameList);
        userInfoRespDto.setRole(roleRepository.findById(user.getRoleId()).get().getName());
        userInfoRespDto.setDept(deptRepository.findById(user.getDeptId()).get().getFullName());
        userInfoRespDto.setEmail(user.getEmail());
        userInfoRespDto.setPhone(user.getPhone());
        userInfoRespDto.setBirthday(user.getBirthday());
        userInfoRespDto.setCreateTime(user.getCreateTime());
        return ResultUtil.success(userInfoRespDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Result findAll(ListReqDto listReqDto) {
        Page<User> userPage = userRepository.findByStatusIsNotAndAccountLike(UserStatusEnum.DELETED.getCode(), "%" + listReqDto.getQueryContent() + "%", PageRequest.of(listReqDto.getPage() - 1, listReqDto.getLimit()));
        List<User> userList = userPage.getContent();
        ListRespDto<UserRespDto> listRespDto = new ListRespDto<>();
        List<UserRespDto> userRespDtoList = new ArrayList<>();
        for (User user : userList) {
            UserRespDto userRespDto = new UserRespDto(
                    user.getId(),
                    user.getAccount(),
                    user.getName(),
                    SexEnum.fromCode(user.getSex()),
                    roleRepository.findById(user.getRoleId()).get().getName(),
                    deptRepository.findById(user.getDeptId()).get().getFullName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getCreateTime(),
                    UserStatusEnum.fromCode(user.getStatus())
            );
            userRespDtoList.add(userRespDto);
        }
        listRespDto.setItems(userRespDtoList);
        listRespDto.setTotal(userPage.getTotalElements());
        return ResultUtil.success(listRespDto);
    }

    @Override
    @Transactional
    public Result delete(Integer id) {
        if (Global.SUPER_USER_ID == id) {
            return ResultUtil.error(ResultEnum.FORBIDDEN);
        }
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        user.setStatus(UserStatusEnum.DELETED.getCode());
        userRepository.saveAndFlush(user);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result create(UserReqDto userReqDto) {
        User user = new User();
        user.setAccount(userReqDto.getAccount());
        user.setPhone(userReqDto.getPhone());
        user.setEmail(userReqDto.getEmail());
        user.setPassword(userReqDto.getPassword());
        user.setName(userReqDto.getName());
        user.setStatus(UserStatusEnum.toCode(userReqDto.getStatus()));
        user.setSex(SexEnum.toCode(userReqDto.getSex()));
        user.setCreateTime(new Date());
        user.setRoleId(roleRepository.findIdByName(userReqDto.getRole()));
        user.setDeptId(deptRepository.findIdByName(userReqDto.getDept()));
        userRepository.saveAndFlush(user);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result modify(UserReqDto userReqDto) {
        Optional<User> userOptional = userRepository.findById(userReqDto.getId());
        User user = userOptional.get();
        user.setPhone(userReqDto.getPhone());
        user.setEmail(userReqDto.getEmail());
        user.setName(userReqDto.getName());
        user.setStatus(UserStatusEnum.toCode(userReqDto.getStatus()));
        user.setSex(SexEnum.toCode(userReqDto.getSex()));
        user.setRoleId(roleRepository.findIdByName(userReqDto.getRole()));
        user.setDeptId(deptRepository.findIdByName(userReqDto.getDept()));
        userRepository.saveAndFlush(user);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result changePwd(Integer id, String newPassword) {
        User user = userRepository.findById(id).get();
        user.setPassword(newPassword);
        userRepository.saveAndFlush(user);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result editUserInfo(UserInfoReqDto userInfoReqDto) {
        User user = userRepository.findById(JwtTokenUtil.getUserIdFromToken(userInfoReqDto.getToken())).get();
        user.setName(userInfoReqDto.getName());
        user.setSex(SexEnum.toCode(userInfoReqDto.getSex()));
        user.setEmail(userInfoReqDto.getEmail());
        user.setPhone(userInfoReqDto.getPhone());
        user.setBirthday(userInfoReqDto.getBirthday());
        userRepository.saveAndFlush(user);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result changeAvatar(Integer userHeadId, Integer userId) throws FileNotFoundException {
        User user = userRepository.findById(userId).get();
        User oldUser = new User();
        BeanUtils.copyProperties(user, oldUser);
        user.setUserHeadPictId(userHeadId);
        SpringContextHolder.getBean(IFileInfService.class).syncBusinessObject(user.getId(), user, oldUser, User.class);
        userRepository.saveAndFlush(user);
        String pictPath = SpringContextHolder.getBean(IFileInfService.class).getSmallPictUrl(userRepository.findById(userId).get().getUserHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE);
        return ResultUtil.success(pictPath);
    }
}
