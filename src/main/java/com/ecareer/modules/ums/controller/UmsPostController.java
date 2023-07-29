package com.ecareer.modules.ums.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecareer.common.api.CommonPage;
import com.ecareer.common.api.CommonResult;
import com.ecareer.modules.ums.dto.UmsPostParam;
import com.ecareer.modules.ums.model.UmsAdminPostRelation;
import com.ecareer.modules.ums.model.UmsPost;
import com.ecareer.modules.ums.service.UmsAdminPostRelationService;
import com.ecareer.modules.ums.service.UmsPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 后台岗位表 前端控制器
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@RestController
@Api(tags = "UmsPostController")
@Tag(name = "UmsPostController", description = "后台岗位管理")
@RequestMapping("/post")
public class UmsPostController {

    @Autowired
    private UmsPostService postService;

    @Autowired
    private UmsAdminPostRelationService adminPostRelationService;

    @ApiOperation(value = "根据岗位ID获取岗位")
    @GetMapping(value = "/user/{id}")
    public CommonResult<UmsPost> getById(@PathVariable Long id) {
        UmsPost post = postService.getPostById(id);
        return CommonResult.success(post);
    }

    @ApiOperation(value = "根据所属部门名称或岗位名称分页获取岗位列表")
    @GetMapping(value = "/user/getPage")
    public CommonResult<CommonPage<UmsPost>> getPage(@RequestParam(value = "departmentName", required = false) String departmentName,
                                                     @RequestParam(value = "postName", required = false) String postName,
                                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<UmsPost> postList = postService.getPage(departmentName, postName, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(postList));
    }

    @ApiOperation(value = "根据用户ID获取用户待过的岗位列表")
    @GetMapping(value = "/user/list/{adminId}")
    public CommonResult<List<UmsAdminPostRelation>> getAdminList(@PathVariable Long adminId) {
        List<UmsAdminPostRelation> postList = adminPostRelationService.getAdminList(adminId);
        return CommonResult.success(postList);
    }

    @ApiOperation(value = "新增岗位")
    @PostMapping(value = "")
    public CommonResult<Object> add(@Validated @RequestBody UmsPostParam postParam) {
        int status = postService.addPost(postParam);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("部门不存在");
        } else if (status == -2) {
            return CommonResult.failed("上级岗位不存在");
        } else if (status == -3) {
            return CommonResult.failed("岗位已存在");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "根据岗位ID删除岗位")
    @DeleteMapping(value = "/{id}")
    public CommonResult<Object> delete(@PathVariable Long id) {
        int status = postService.deletePost(id);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("岗位不存在");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改岗位")
    @PutMapping(value = "")
    public CommonResult<Object> update(@RequestBody UmsPost post) {
        int status = postService.updatePost(post);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("岗位不存在");
        } else if (status == -2) {
            return CommonResult.failed("部门不存在");
        } else if (status == -3) {
            return CommonResult.failed("上级岗位不存在");
        } else {
            return CommonResult.failed();
        }
    }

}

