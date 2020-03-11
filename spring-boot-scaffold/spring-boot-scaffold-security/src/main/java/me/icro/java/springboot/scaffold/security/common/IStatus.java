package me.icro.java.springboot.scaffold.security.common;

/**
 * @author lin
 * @version v 0.1 2020/3/11
 **/
public interface IStatus {
    /**
     * 状态码
     *
     * @return 状态码
     */
    Integer getCode();

    /**
     * 返回信息
     *
     * @return 返回信息
     */
    String getMessage();
}
