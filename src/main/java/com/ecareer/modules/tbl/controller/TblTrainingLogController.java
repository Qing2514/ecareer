package com.ecareer.modules.tbl.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecareer.common.api.CommonPage;
import com.ecareer.common.api.CommonResult;
import com.ecareer.modules.tbl.dto.TblTrainingLogParam;
import com.ecareer.modules.tbl.model.TblTrainingLog;
import com.ecareer.modules.tbl.service.TblTrainingLogService;
import com.ecareer.modules.tbl.vo.TrainingLogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * <p>
 * 培训记录表 前端控制器
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@RestController
@Api(tags = "TblTrainingLogController")
@Tag(name = "TblTrainingLogController", description = "培训记录管理")
@RequestMapping("/trainingLog")
public class TblTrainingLogController {

    @Autowired
    private TblTrainingLogService trainingLogService;

    @ApiOperation(value = "根据当前用户获取培训记录")
    @GetMapping(value = "/user")
    public CommonResult<List<TrainingLogVO>> getById(Principal principal) {
        List<TrainingLogVO> trainingLog = trainingLogService.getTrainingLogByUsername(principal.getName());
        return CommonResult.success(trainingLog);
    }

    @ApiOperation(value = "新增当前用户培训记录")
    @PostMapping(value = "/user")
    public CommonResult<Object> add(@Validated @RequestBody TblTrainingLogParam trainingLogParam,
                                    Principal principal) {
        int status = trainingLogService.addTrainingLog(principal.getName(), trainingLogParam);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("用户不存在");
        } else if (status == -2) {
            return CommonResult.failed("资料不存在");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "根据当前用户的培训记录ID删除培训记录")
    @DeleteMapping(value = "/user/{materialId}")
    public CommonResult<Object> delete(@PathVariable Long materialId, Principal principal) {
        int status = trainingLogService.deleteTrainingLogByUsername(principal.getName(), materialId);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("培训记录不存在");
        } else if (status == -2) {
            return CommonResult.failed("培训记录不属于该用户");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改培训记录")
    @PutMapping(value = "/user")
    public CommonResult<Object> update(@RequestBody TblTrainingLog trainingLog) {
        int status = trainingLogService.updateTrainingLog(trainingLog);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("用户不存在");
        } else if (status == -2) {
            return CommonResult.failed("培训资料不存在");
        } else if (status == -3) {
            return CommonResult.failed("培训记录不存在");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "根据用户ID获取培训记录")
    @GetMapping(value = "/{id}")
    public CommonResult<List<TrainingLogVO>> getById(@PathVariable Long id) {
        List<TrainingLogVO> trainingLog = trainingLogService.getTrainingLogByUserId(id);
        return CommonResult.success(trainingLog);
    }

    @ApiOperation(value = "根据用户ID分页获取培训记录列表")
    @GetMapping(value = "/getPage")
    public CommonResult<CommonPage<TrainingLogVO>> getPage(@RequestParam(value = "adminId", required = false) Long adminId,
                                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<TrainingLogVO> trainingLogList = trainingLogService.getPage(adminId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(trainingLogList));
    }

    @ApiOperation(value = "根据培训记录ID删除培训记录")
    @DeleteMapping(value = "/{materialId}")
    public CommonResult<Object> delete(@PathVariable Long materialId) {
        int status = trainingLogService.deleteTrainingLogById(materialId);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("培训记录不存在");
        } else {
            return CommonResult.failed();
        }
    }

}

