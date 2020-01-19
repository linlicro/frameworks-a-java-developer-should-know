package me.icro.java.springboot.scaffold.service;

import me.icro.java.springboot.scaffold.vo.MessageVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述: 登录体系
 *
 * @author Lin
 * @since 2020-01-14 3:36 PM
 */
public interface ILoginService {

    /**
     * 用户登陆
     * @param request
     * @param username
     * @param password
     * @return
     */
    MessageVO userLogin(HttpServletRequest request, String username, String password);

    /**
     * 注销登录
     * @param request
     * @return
     */
    MessageVO userLogout(HttpServletRequest request);
}
