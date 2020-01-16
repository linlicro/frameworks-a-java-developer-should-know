package me.icro.java.springboot.scaffold.dao.db1.domain;

import lombok.Data;

import java.util.Date;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-16 5:27 PM
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
