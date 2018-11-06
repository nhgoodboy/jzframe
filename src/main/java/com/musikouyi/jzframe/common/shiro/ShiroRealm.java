package com.musikouyi.jzframe.common.shiro;

import com.musikouyi.jzframe.dao.mapper.PermissionMapper;
import com.musikouyi.jzframe.dao.mapper.UserMapper;
import com.musikouyi.jzframe.domain.entity.User;
import com.musikouyi.jzframe.service.IUserService;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Set;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 获取授权信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String userName = (String) principals.getPrimaryPrincipal();
//        String role = getRoleByUserName(userName);
        Set<String> permissions = getPermissionByUserName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 获取认证信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        String password = getPasswordByUserName(userName);
        if (password == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userName,
                password,
                ByteSource.Util.bytes(SpringContextHolder.getBean(UserMapper.class).findSaltByAccount(userName)),
                getName());
        return authenticationInfo;
    }

    private String getPasswordByUserName(String userName) {
        User user = SpringContextHolder.getBean(IUserService.class).getByAccount(userName);
        if (user == null){
            return null;
        }
        return user.getPassword();
    }

    private String getRoleByUserName(String userName) {
        return null;
    }

    private Set<String> getPermissionByUserName(String userName) {
        log.info("从数据库中获取授权数据");
        return SpringContextHolder.getBean(PermissionMapper.class).getPermissionByUserName(userName);
    }
}
