package com.ecareer.modules.ums.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecareer.common.api.CommonPage;
import com.ecareer.common.api.CommonResult;
import com.ecareer.modules.ums.dto.UmsDepartmentParam;
import com.ecareer.modules.ums.model.UmsDepartment;
import com.ecareer.modules.ums.service.UmsDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 后台部门表 前端控制器
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@RestController
@Api(tags = "UmsDepartmentController")
@Tag(name = "UmsDepartmentController", description = "后台部门管理")
@RequestMapping("/department")
public class UmsDepartmentController {

    @Autowired
    private UmsDepartmentService departmentService;

    @ApiOperation(value = "根据部门ID获取部门")
    @GetMapping(value = "/user/{id}")
    public CommonResult<UmsDepartment> getById(@PathVariable Long id) {
        UmsDepartment department = departmentService.getDepartmentById(id);
        return CommonResult.success(department);
    }

    @ApiOperation(value = "根据部门名称分页获取部门列表")
    @GetMapping(value = "/user/getPage")
    public CommonResult<CommonPage<UmsDepartment>> getPage(@RequestParam(value = "departmentName", required = false) String departmentName,
                                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<UmsDepartment> departmentList = departmentService.getPage(departmentName, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(departmentList));
    }

    @ApiOperation(value = "新增部门")
    @PostMapping(value = "")
    public CommonResult<Object> add(@Validated @RequestBody UmsDepartmentParam departmentParam) {
        int status = departmentService.addDepartment(departmentParam);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("部门管理人不存在");
        } else if (status == -2) {
            return CommonResult.failed("部门已存在");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "根据部门ID删除部门")
    @DeleteMapping(value = "/{id}")
    public CommonResult<Object> delete(@PathVariable Long id) {
        int status = departmentService.deleteDepartment(id);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("部门不存在");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改部门")
    @PutMapping(value = "")
    public CommonResult<Object> update(@RequestBody UmsDepartment department) {
        int status = departmentService.updateDepartment(department);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("部门不存在");
        } else if (status == -2) {
            return CommonResult.failed("部门管理人不存在");
        } else {
            return CommonResult.failed();
        }
    }

}

