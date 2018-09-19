package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.entity.User;
import com.musikouyi.jzframe.domain.enums.SexEnum;
import com.musikouyi.jzframe.domain.enums.UserStatusEnum;
import com.musikouyi.jzframe.dto.ListReqDto;
import com.musikouyi.jzframe.dto.ListRespDto;
import com.musikouyi.jzframe.dto.UserInfoRespDto;
import com.musikouyi.jzframe.dto.UserRespDto;
import com.musikouyi.jzframe.repository.UserRepository;
import com.musikouyi.jzframe.service.IDeptService;
import com.musikouyi.jzframe.service.IRoleService;
import com.musikouyi.jzframe.service.IUserService;
import com.musikouyi.jzframe.utils.ResultUtil;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public int setStatus(Integer userId, int status) {
        return 0;
    }

    @Override
    public int changePwd(Integer userId, String pwd) {
        return 0;
    }

    @Override
    public int setRoles(Integer userId, String roleIds) {
        return 0;
    }

    @Override
    public User getByAccount(String account) {
        return userRepository.findUserByAccount(account);
    }

    @Override
    public Result findById(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        List<String> roleNameList = new ArrayList<>();
        roleNameList.add(SpringContextHolder.getBean(IRoleService.class).findById(user.getRoleid()).getTips());
        UserInfoRespDto userInfoRespDto = new UserInfoRespDto(user.getName(), user.getAvatar(), roleNameList);
        return ResultUtil.success(userInfoRespDto);
    }

    @Override
    public Result findAll(ListReqDto listReqDto) {
        Page<User> userPage = userRepository.findAll(PageRequest.of(listReqDto.getPage() - 1, listReqDto.getLimit()));
        List<User> userList = userPage.getContent();
        ListRespDto<UserRespDto> listRespDto = new ListRespDto<>();
        List<UserRespDto> userRespDtoList = new ArrayList<>();
        for (User user :
                userList) {
            UserRespDto userRespDto = new UserRespDto(
                    user.getAccount(),
                    user.getName(),
                    SexEnum.fromCode(user.getSex()),
                    SpringContextHolder.getBean(IRoleService.class).findById(user.getRoleid()).getName(),
                    SpringContextHolder.getBean(IDeptService.class).findById(user.getDeptid()).getFullname(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getCreatetime(),
                    UserStatusEnum.fromCode(user.getStatus())
            );
            userRespDtoList.add(userRespDto);
        }
        listRespDto.setItems(userRespDtoList);
        listRespDto.setTotal(userPage.getTotalElements());
        return ResultUtil.success(listRespDto);
    }
}
