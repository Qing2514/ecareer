package com.ecareer.modules.tbl.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecareer.common.api.CommonPage;
import com.ecareer.common.api.CommonResult;
import com.ecareer.modules.tbl.dto.TblCareerPlanParam;
import com.ecareer.modules.tbl.model.TblCareerPlan;
import com.ecareer.modules.tbl.service.TblCareerPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 职业规划表 前端控制器
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@RestController
@Api(tags = "TblCareerPlanController")
@Tag(name = "TblCareerPlanController", description = "职业规划管理")
@RequestMapping("/careerPlan")
public class TblCareerPlanController {

    @Autowired
    private TblCareerPlanService careerPlanService;

    @ApiOperation(value = "根据用户ID获取职业规划")
    @GetMapping(value = "/{id}")
    public CommonResult<TblCareerPlan> getById(@PathVariable Long id) {
        TblCareerPlan careerPlan = careerPlanService.getById(id);
        return CommonResult.success(careerPlan);
    }

    @ApiOperation(value = "根据用户ID分页获取职业规划列表")
    @GetMapping(value = "/getPage")
    public CommonResult<CommonPage<TblCareerPlan>> getPage(@RequestParam(value = "adminId", required = false) Long adminId, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<TblCareerPlan> careerPlanList = careerPlanService.getPage(adminId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(careerPlanList));
    }

    @ApiOperation(value = "新增职业规划")
    @PostMapping(value = "")
    public CommonResult<Object> add(@Validated @RequestBody TblCareerPlanParam careerPlanParam) {
        int status = careerPlanService.addCareerPlan(careerPlanParam);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("用户不存在");
        } else if (status == -2) {
            return CommonResult.failed("职业规划已存在");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "根据职业规划ID删除职业规划")
    @DeleteMapping(value = "/{id}")
    public CommonResult<Object> delete(@PathVariable Long id) {
        boolean flag = careerPlanService.removeById(id);
        if (flag) {
            return CommonResult.success();
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改职业规划")
    @PutMapping(value = "")
    public CommonResult<Object> update(@RequestBody TblCareerPlan careerPlan) {
        int status = careerPlanService.updateCareerPlan(careerPlan);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("职业规划不存在");
        } else if (status == -2) {
            return CommonResult.failed("用户不存在");
        } else {
            return CommonResult.failed();
        }
    }

}

