package me.icro.java.springboot.scaffold.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述: 测试 Controller
 *
 * @author Lin
 * @since 2020-01-21 11:28 上午
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public Dict test(String who) {
        return Dict.create().set("who", StrUtil.isBlank(who) ? "me" : who);
    }
}
