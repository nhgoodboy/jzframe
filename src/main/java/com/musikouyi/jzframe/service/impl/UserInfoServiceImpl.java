package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.common.exception.GlobalException;
import com.musikouyi.jzframe.dao.repository.DeptRepository;
import com.musikouyi.jzframe.dao.repository.RoleRepository;
import com.musikouyi.jzframe.dao.repository.UserRepository;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.entity.Role;
import com.musikouyi.jzframe.domain.entity.User;
import com.musikouyi.jzframe.domain.enums.ResultEnum;
import com.musikouyi.jzframe.domain.enums.SexEnum;
import com.musikouyi.jzframe.dto.UserInfoReqDto;
import com.musikouyi.jzframe.dto.UserInfoRespDto;
import com.musikouyi.jzframe.service.IFileInfService;
import com.musikouyi.jzframe.service.IPermissionService;
import com.musikouyi.jzframe.service.IUserInfoService;
import com.musikouyi.jzframe.utils.JwtTokenUtil;
import com.musikouyi.jzframe.utils.ResultUtil;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;

@Service
public class UserInfoServiceImpl implements IUserInfoService {


    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final DeptRepository deptRepository;

    @Autowired
    public UserInfoServiceImpl(UserRepository userRepository, RoleRepository roleRepository, DeptRepository deptRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.deptRepository = deptRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Result userInfo(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new GlobalException(ResultEnum.DATABASE_QUERRY_ERROR));
        UserInfoRespDto userInfoRespDto = new UserInfoRespDto();
        userInfoRespDto.setAvatar(SpringContextHolder.getBean(IFileInfService.class)
                .getSmallPictUrl(user.getUserHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE));
        userInfoRespDto.setAccount(user.getAccount());
        userInfoRespDto.setName(user.getName());
        userInfoRespDto.setSex(SexEnum.fromCode(user.getSex()));
        Role role = roleRepository.findById(user.getRoleId()).orElseThrow(() -> new GlobalException(ResultEnum.DATABASE_QUERRY_ERROR));
        userInfoRespDto.setRole(role.getName());
        userInfoRespDto.setDept(deptRepository.findById(user.getDeptId()).orElseThrow(() -> new GlobalException(ResultEnum.DATABASE_QUERRY_ERROR)).getFullName());
        userInfoRespDto.setEmail(user.getEmail());
        userInfoRespDto.setPhone(user.getPhone());
        userInfoRespDto.setBirthday(user.getBirthday());
        userInfoRespDto.setCreateTime(user.getCreateTime());
        userInfoRespDto.setPermissions(SpringContextHolder.getBean(IPermissionService.class).getCodesByRoleId(role.getId()));
        return ResultUtil.success(userInfoRespDto);
    }

    @Override
    @Transactional
    public Result editUserInfo(UserInfoReqDto userInfoReqDto) {
        User user = userRepository.findById(JwtTokenUtil.getUserIdFromToken(userInfoReqDto.getToken())).orElseThrow(() -> new GlobalException(ResultEnum.DATABASE_QUERRY_ERROR));
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
        User user = userRepository.findById(userId).orElseThrow(() -> new GlobalException(ResultEnum.DATABASE_QUERRY_ERROR));
        User oldUser = new User();
        BeanUtils.copyProperties(user, oldUser);
        user.setUserHeadPictId(userHeadId);
        SpringContextHolder.getBean(IFileInfService.class).syncBusinessObject(user.getId(), user, oldUser, User.class);
        userRepository.saveAndFlush(user);
        String pictPath = SpringContextHolder.getBean(IFileInfService.class).getSmallPictUrl(userRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(ResultEnum.DATABASE_QUERRY_ERROR)).getUserHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE);
        return ResultUtil.success(pictPath);
    }
}
