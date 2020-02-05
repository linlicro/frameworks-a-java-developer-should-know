package me.icro.java.springboot.scaffold.controller;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.icro.java.springboot.scaffold.model.ApiResponse;
import me.icro.java.springboot.scaffold.model.DataType;
import me.icro.java.springboot.scaffold.model.ParamType;
import me.icro.java.springboot.scaffold.vo.SampleVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * 描述: 测试 Controller
 *
 * @author Lin
 * @since 2020-01-21 11:28 上午
 */
@RestController
@RequestMapping("/test")
@Api(tags = "1.0.0-SNAPSHOT", value = "测试API")
@Slf4j
public class TestController {

    @GetMapping
    @ApiOperation(value = "查询", notes = "备注")
    @ApiImplicitParams({@ApiImplicitParam(name = "who", value = "用户名", dataType = DataType.STRING, paramType = ParamType.QUERY, defaultValue = "")})
    public ApiResponse<String> test(String who) {
        log.info("多个参数用  @ApiImplicitParams");
        return ApiResponse.ofSuccess(StrUtil.isBlank(who) ? "me" : who);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "主键查询 操作", notes = "备注")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "主键", dataType = DataType.INT, paramType = ParamType.PATH)})
    public ApiResponse<SampleVO> get(@PathVariable Integer id) {
        log.info("单个参数用  @ApiImplicitParam");
        return ApiResponse.ofSuccess(Objects.isNull(id)
                ? SampleVO.builder().id(1).message("sample data").data("data").build()
                : SampleVO.builder().id(2).message("sample data").data("data").build());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除操作", notes = "备注")
    @ApiImplicitParam(name = "id", value = "主键", dataType = DataType.INT, paramType = ParamType.PATH)
    public void delete(@PathVariable Integer id) {
        log.info("delete {}", id);
    }

    @PostMapping
    @ApiOperation(value = "添加操作")
    public ApiResponse<SampleVO> post(@RequestBody SampleVO sampleVO) {
        log.info("如果是 POST PUT 这种带 @RequestBody 的可以不用写 @ApiImplicitParam");
        sampleVO.setId(1);
        return ApiResponse.ofSuccess(sampleVO);
    }

    @PostMapping("/multipar")
    @ApiOperation(value = "添加操作")
    public ApiResponse<List<SampleVO>> multipar(@RequestBody List<SampleVO> sampleVOs) {
        log.info("如果是 POST PUT 这种带 @RequestBody 的可以不用写 @ApiImplicitParam");
        return ApiResponse.ofSuccess(Lists.newArrayList());
    }

    @PostMapping("/array")
    @ApiOperation(value = "添加操作")
    public ApiResponse<List<SampleVO>> array(List<SampleVO> sampleVOList) {
        log.info("如果是 POST PUT 这种带 @RequestBody 的可以不用写 @ApiImplicitParam");
        return ApiResponse.ofSuccess(sampleVOList);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改操作")
    public void put(@PathVariable Integer id, @RequestBody SampleVO sampleVO) {
        log.info("如果你不想写 @ApiImplicitParam 那么 swagger 也会使用默认的参数名作为描述信息 ");
    }

    @PostMapping("/{id}/file")
    @ApiOperation(value = "文件上传")
    public ApiResponse<String> file(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        log.info(file.getContentType());
        log.info(file.getName());
        log.info(file.getOriginalFilename());
        return ApiResponse.ofSuccess(file.getOriginalFilename());
    }
}
