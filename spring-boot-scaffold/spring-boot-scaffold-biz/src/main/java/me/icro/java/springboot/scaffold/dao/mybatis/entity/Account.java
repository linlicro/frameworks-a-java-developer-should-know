package me.icro.java.springboot.scaffold.dao.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 描述: 账号实体类
 *
 * @author Lin
 * @since 2020-02-04 10:31 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sc_account")
public class Account implements Serializable {
    private static final long serialVersionUID = -7957930839586024909L;

    /**
     * 主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 加密后的密码
     */
    private String password;

    /**
     * 盐 加密使用
     */
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 状态，-1：逻辑删除，0：禁用，1：启用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 上次更新时间
     */
    private Date lastUpdateTime;
}
