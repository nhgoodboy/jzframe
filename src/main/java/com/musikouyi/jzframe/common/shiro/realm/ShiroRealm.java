package com.musikouyi.jzframe.common.shiro.realm;

import com.musikouyi.jzframe.dao.mapper.MenuMapper;
import com.musikouyi.jzframe.domain.entity.User;
import com.musikouyi.jzframe.service.IUserService;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

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
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, password, "shiroRealm");
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
        return SpringContextHolder.getBean(MenuMapper.class).getPermissionByUserName(userName);
    }
}
