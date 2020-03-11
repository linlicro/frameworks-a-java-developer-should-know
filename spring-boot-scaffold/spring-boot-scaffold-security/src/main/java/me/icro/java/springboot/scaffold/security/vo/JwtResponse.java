package me.icro.java.springboot.scaffold.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.icro.java.springboot.scaffold.security.common.Consts;

/**
 * JWT 响应返回
 *
 * @author lin
 * @version v 0.1 2020/3/11
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    /**
     * token 字段
     */
    private String token;
    /**
     * token类型
     */
    private String tokenType = Consts.TOKEN_TYPE;

    public JwtResponse(String token) {
        this.token = token;
    }
}
