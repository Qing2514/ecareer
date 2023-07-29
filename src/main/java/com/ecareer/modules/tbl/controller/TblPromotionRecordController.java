package com.ecareer.modules.tbl.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecareer.common.api.CommonPage;
import com.ecareer.common.api.CommonResult;
import com.ecareer.modules.tbl.dto.TblPromotionRecordParam;
import com.ecareer.modules.tbl.model.TblPromotionRecord;
import com.ecareer.modules.tbl.service.TblPromotionRecordService;
import com.ecareer.modules.tbl.vo.PromotionRecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 晋升记录表 前端控制器
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@RestController
@Api(tags = "TblPromotionRecordController")
@Tag(name = "TblPromotionRecordController", description = "晋升记录管理")
@RequestMapping("/promotionRecord")
public class TblPromotionRecordController {

    @Autowired
    private TblPromotionRecordService promotionRecordService;

    @ApiOperation(value = "根据用户ID获取晋升记录")
    @GetMapping(value = "/{id}")
    public CommonResult<List<PromotionRecordVO>> getById(@PathVariable Long id) {
        List<PromotionRecordVO> promotionRecordVO = promotionRecordService.getPromotionRecordByUserId(id);
        return CommonResult.success(promotionRecordVO);
    }

    @ApiOperation(value = "根据用户ID分页获取晋升记录列表")
    @GetMapping(value = "/getPage")
    public CommonResult<CommonPage<PromotionRecordVO>> getPage(@RequestParam(value = "adminId", required = false) Long adminId,
                                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<PromotionRecordVO> promotionRecordList = promotionRecordService.getPage(adminId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(promotionRecordList));
    }

    @ApiOperation(value = "新增晋升记录")
    @PostMapping(value = "")
    public CommonResult<Object> add(@Validated @RequestBody TblPromotionRecordParam promotionRecordParam) {
        int status = promotionRecordService.addPromotionRecord(promotionRecordParam);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("用户不存在");
        } else if (status == -2) {
            return CommonResult.failed("晋升前岗位不存在");
        } else if (status == -3) {
            return CommonResult.failed("晋升后岗位不存在");
        } else if (status == -4) {
            return CommonResult.failed("晋升记录已存在");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "根据晋升记录ID删除晋升记录")
    @DeleteMapping(value = "/{id}")
    public CommonResult<Object> delete(@PathVariable Long id) {
        int status = promotionRecordService.deletePromotionRecord(id);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("晋升记录不存在");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改晋升记录")
    @PutMapping(value = "")
    public CommonResult<Object> update(@RequestBody TblPromotionRecord promotionRecord) {
        int status = promotionRecordService.updatePromotionRecord(promotionRecord);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("晋升记录不存在");
        } else if (status == -2) {
            return CommonResult.failed("用户不存在");
        } else if (status == -3) {
            return CommonResult.failed("晋升前岗位不存在");
        } else if (status == -4) {
            return CommonResult.failed("晋升后岗位不存在");
        } else {
            return CommonResult.failed();
        }
    }

}

