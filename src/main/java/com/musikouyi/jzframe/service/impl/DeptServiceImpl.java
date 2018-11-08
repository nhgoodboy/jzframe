package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.common.exception.GlobalException;
import com.musikouyi.jzframe.dao.mapper.DeptMapper;
import com.musikouyi.jzframe.dao.mapper.RoleMapper;
import com.musikouyi.jzframe.dao.mapper.UserMapper;
import com.musikouyi.jzframe.dao.repository.DeptRepository;
import com.musikouyi.jzframe.domain.entity.Dept;
import com.musikouyi.jzframe.domain.entity.Role;
import com.musikouyi.jzframe.domain.entity.User;
import com.musikouyi.jzframe.domain.enums.ResultEnum;
import com.musikouyi.jzframe.dto.DeptDto;
import com.musikouyi.jzframe.dto.ListReqDto;
import com.musikouyi.jzframe.dto.ListRespDto;
import com.musikouyi.jzframe.service.IDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/16 21:56
 **/
@Slf4j
@Service
public class DeptServiceImpl implements IDeptService {

    private final DeptMapper deptMapper;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    private final DeptRepository deptRepository;

    @Autowired
    public DeptServiceImpl(DeptRepository deptRepository, DeptMapper deptMapper, UserMapper userMapper, RoleMapper roleMapper) {
        this.deptRepository = deptRepository;
        this.deptMapper = deptMapper;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getDeptNameList() {
        return deptRepository.findName();
    }

    //命中率低，不采用缓存机制
    @Override
    @Transactional(readOnly = true)
    public ListRespDto<DeptDto> findAll(ListReqDto listReqDto) {
        Page<Dept> deptPage = deptRepository.findAll(PageRequest.of(listReqDto.getPage() - 1, listReqDto.getLimit()));
        List<Dept> deptList = deptPage.getContent();
        ListRespDto<DeptDto> listRespDto = new ListRespDto<>();
        List<DeptDto> deptDtoList = new ArrayList<>();
        for (Dept dept : deptList) {
            DeptDto deptDto = new DeptDto(
                    dept.getId(),
                    dept.getFullName(),
                    dept.getParentId() == Global.SUPER_DEPT_PARENT ? null : deptRepository.findById(dept.getParentId())
                            .orElseThrow(() -> new GlobalException(ResultEnum.DATABASE_QUERRY_ERROR)).getFullName()
            );
            deptDtoList.add(deptDto);
        }
        listRespDto.setItems(deptDtoList);
        listRespDto.setTotal(deptPage.getTotalElements());
        return listRespDto;
    }

    @Override
    @Transactional
    @CacheEvict(value = "redisCache", key = "'redis_dept_'+#id")
    public void delete(Integer id) {
        if (Global.SUPER_DEPT_ID == id) {
            throw new GlobalException(ResultEnum.FORBIDDEN);
        }
        List<User> users = userMapper.findByDeptId(id);
        if (users != null && users.size() > 0) {
            throw new GlobalException(ResultEnum.DEPT_USER_USED);
        }
        List<Role> roles = roleMapper.findByDeptId(id);
        if (roles != null && roles.size() > 0) {
            throw new GlobalException(ResultEnum.DEPT_ROLE_USED);
        }
        deptRepository.deleteById(id);
    }

    @Override
    @Transactional
    @CachePut(value = "redisCache", key = "'redis_dept_'+#result.id")
    public Dept create(DeptDto deptDto) {
        Dept dept = new Dept();
        dept.setFullName(deptDto.getFullName());
        dept.setParentId(deptRepository.findIdByName(deptDto.getParent_dept()));
        deptRepository.saveAndFlush(dept);
        return dept;
    }

    @Override
    @Transactional
    @CachePut(value = "redisCache", condition = "#result != 'null'", key = "'redis_dept_'+#deptDto.id")
    public Dept modify(DeptDto deptDto) {
        Dept dept = deptRepository.findById(deptDto.getId()).orElseThrow(() -> new GlobalException(ResultEnum.DATABASE_QUERRY_ERROR));
        dept.setFullName(deptDto.getFullName());
        dept.setParentId(deptRepository.findIdByName(deptDto.getParent_dept()));
        deptRepository.saveAndFlush(dept);
        return dept;
    }

    @Override
    @Transactional(readOnly = true)
    public ListRespDto<DeptDto> getDepts(ListReqDto listReqDto) {
        List<Dept> deptList = deptMapper.getDepts(listReqDto.getPage() - 1, listReqDto.getLimit());
        ListRespDto<DeptDto> listRespDto = new ListRespDto<>();
        List<DeptDto> deptDtoList = new ArrayList<>();
        for (Dept dept : deptList) {
            DeptDto deptDto = new DeptDto(
                    dept.getId(),
                    dept.getFullName(),
                    dept.getParentId() == Global.SUPER_DEPT_PARENT ? null : deptMapper.findById(dept.getParentId()).getFullName()
            );
            deptDtoList.add(deptDto);
        }
        listRespDto.setItems(deptDtoList);
        listRespDto.setTotal(deptMapper.getTotalNum());
        return listRespDto;
    }
}
