package me.icro.java.springboot.scaffold.entity;

import lombok.Data;

import java.util.Date;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-10 3:10 PM
 */
@Data
public class Account {
    private Long id;
    private String name;
    private String password;
    private String salt;
    private String email;
    private Integer status;
    private Date createTime;
    private Date lastLoginTime;
    private Date lastUpdateTime;
}
