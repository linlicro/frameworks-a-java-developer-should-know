package me.icro.java.springboot.scaffold.service.impl;

import me.icro.java.springboot.scaffold.service.ILoginService;
import me.icro.java.springboot.scaffold.vo.MessageVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-14 3:42 PM
 */
public class LoginServiceImpl implements ILoginService {

    @Override
    public MessageVO userLogin(HttpServletRequest request, String username, String password) {
        return null;
    }

    @Override
    public MessageVO userLogout(HttpServletRequest request) {
        return null;
    }
}
