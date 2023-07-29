package com.ecareer.modules.tbl.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecareer.common.api.CommonPage;
import com.ecareer.common.api.CommonResult;
import com.ecareer.modules.tbl.dto.TblQuestionParam;
import com.ecareer.modules.tbl.model.TblChoiceQuestion;
import com.ecareer.modules.tbl.service.TblQuestionService;
import com.ecareer.modules.tbl.service.TblQuestionTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 职业测评表 前端控制器
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@RestController
@Api(tags = "TblQuestionController")
@Tag(name = "TblQuestionController", description = "职业测评管理")
@RequestMapping("/question")
public class TblQuestionController {

    @Autowired
    private TblQuestionService questionService;

    @Autowired
    private TblQuestionTypeService questionTypeService;

    @ApiOperation(value = "从题库随机抽选题目发放给用户")
    @GetMapping(value = "/user/getQuestions")
    public CommonResult<List<List<TblChoiceQuestion>>> getQuestions() {
        List<List<TblChoiceQuestion>> questionList = questionService.getQuestions();
        return CommonResult.success(questionList);
    }

    @ApiOperation(value = "根据试题类型ID获取试题列表")
    @GetMapping(value = "/{typeId}")
    public CommonResult<List<TblChoiceQuestion>> getById(@PathVariable Long typeId) {
        List<TblChoiceQuestion> questionList = questionService.getByTypeId(typeId);
        return CommonResult.success(questionList);
    }

    @ApiOperation(value = "根据试题类型ID分页获取试题列表")
    @GetMapping(value = "/getPage")
    public CommonResult<CommonPage<TblChoiceQuestion>> getPage(@RequestParam(value = "typeId", required = false) Long typeId,
                                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<TblChoiceQuestion> questionList = questionService.getPage(typeId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(questionList));
    }

    @ApiOperation(value = "新增试题")
    @PostMapping(value = "")
    public CommonResult<Object> add(@Validated @RequestBody TblQuestionParam questionParam) {
        int status = questionService.addQuestion(questionParam);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("试题类型不存在");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "新增试题类型")
    @PostMapping(value = "/{name}")
    public CommonResult<Object> addType(@PathVariable String name) {
        int status = questionTypeService.addType(name);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("试题类型已存在");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "根据试题ID删除试题")
    @DeleteMapping(value = "/{id}")
    public CommonResult<Object> delete(@PathVariable Long id) {
        boolean flag = questionService.removeById(id);
        if (flag) {
            return CommonResult.success();
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改试题")
    @PutMapping(value = "")
    public CommonResult<Object> update(@RequestBody TblChoiceQuestion question) {
        int status = questionService.updateQuestion(question);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("试题不存在");
        } else if (status == -2) {
            return CommonResult.failed("试题类型不存在");
        } else {
            return CommonResult.failed();
        }
    }

}

