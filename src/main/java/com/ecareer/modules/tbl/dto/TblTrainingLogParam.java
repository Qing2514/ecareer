package com.ecareer.modules.tbl.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 培训记录参数校验
 * @author Qing2514
 * @since 2023-07-22
 */
@Data
public class TblTrainingLogParam {

    @NotNull
    @ApiModelProperty(value = "用户ID", required = true)
    private Long adminId;

    @NotNull
    @ApiModelProperty(value = "培训资料ID", required = true)
    private Long materialId;

}
