package me.icro.java.springboot.scaffold.security.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lin
 * @version v 0.1 2020/3/11
 **/
@Data
public class LoginRequest {
    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String phone;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 记住我
     */
    private Boolean rememberMe = false;
}
