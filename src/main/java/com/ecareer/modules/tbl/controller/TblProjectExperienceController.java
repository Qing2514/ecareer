package com.ecareer.modules.tbl.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecareer.common.api.CommonPage;
import com.ecareer.common.api.CommonResult;
import com.ecareer.modules.tbl.dto.TblProjectExperienceParam;
import com.ecareer.modules.tbl.model.TblProjectExperience;
import com.ecareer.modules.tbl.service.TblProjectExperienceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 项目经验表 前端控制器
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@RestController
@Api(tags = "TblProjectExperienceController")
@Tag(name = "TblProjectExperienceController", description = "项目经验管理")
@RequestMapping("/projectExperience")
public class TblProjectExperienceController {

    @Autowired
    private TblProjectExperienceService projectExperienceService;

    @ApiOperation(value = "根据用户ID获取项目经验")
    @GetMapping(value = "/{id}")
    public CommonResult<List<TblProjectExperience>> getById(@PathVariable Long id) {
        List<TblProjectExperience> projectExperience = projectExperienceService.getProjectExperienceByUserId(id);
        return CommonResult.success(projectExperience);
    }

    @ApiOperation(value = "根据项目名称分页获取项目经验列表")
    @GetMapping(value = "/getPage")
    public CommonResult<CommonPage<TblProjectExperience>> getPage(@RequestParam(value = "projectName", required = false) String projectName,
                                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<TblProjectExperience> projectExperienceList = projectExperienceService.getPage(projectName, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(projectExperienceList));
    }

    @ApiOperation(value = "新增项目经验")
    @PostMapping(value = "")
    public CommonResult<Object> add(@Validated @RequestBody TblProjectExperienceParam projectExperienceParam) {
        int status = projectExperienceService.addProjectExperience(projectExperienceParam);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("用户不存在");
        } else if (status == -2) {
            return CommonResult.failed("岗位不存在");
        } else if (status == -3) {
            return CommonResult.failed("项目经验已存在");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "根据项目经验ID删除项目经验")
    @DeleteMapping(value = "/{id}")
    public CommonResult<Object> delete(@PathVariable Long id) {
        int status = projectExperienceService.deleteProjectExperience(id);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("项目经验不存在");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改项目经验")
    @PutMapping(value = "")
    public CommonResult<Object> update(@RequestBody TblProjectExperience projectExperience) {
        int status = projectExperienceService.updateProjectExperience(projectExperience);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("项目经验不存在");
        } else if (status == -2) {
            return CommonResult.failed("用户不存在");
        } else if (status == -3) {
            return CommonResult.failed("岗位不存在");
        } else {
            return CommonResult.failed();
        }
    }

}

