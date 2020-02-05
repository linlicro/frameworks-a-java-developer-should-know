package me.icro.java.springboot.scaffold.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-02-05 7:17 下午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SampleVO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 数据
     */
    private String data;
    /**
     * 消息
     */
    private String message;
    /**
     * 时间
     */
    private Date date;
}
