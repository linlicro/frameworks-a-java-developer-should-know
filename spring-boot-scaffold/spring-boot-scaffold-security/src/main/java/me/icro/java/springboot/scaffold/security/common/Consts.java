package me.icro.java.springboot.scaffold.security.common;

/**
 * @author lin
 * @version v 0.1 2020/3/11
 **/
public interface Consts {

    /**
     * JWT 在 Redis 中保存的key前缀
     */
    String REDIS_JWT_KEY_PREFIX = "security:jwt:";

    /**
     * token类型
     */
    String TOKEN_TYPE = "Bearer";
}
