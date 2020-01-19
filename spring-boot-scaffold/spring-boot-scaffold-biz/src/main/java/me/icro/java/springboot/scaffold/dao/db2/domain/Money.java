package me.icro.java.springboot.scaffold.dao.db2.domain;

import lombok.Data;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-16 5:27 PM
 */
@Data
public class Money {
    private Long id;
    private Long account_id;
    private Integer basic;
    private Integer reward;
    private Integer punishment;
}
