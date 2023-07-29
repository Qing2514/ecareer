package com.ecareer.modules.tbl.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 晋升记录参数校验
 * @author Qing2514
 * @since 2023-07-22
 */
@Data
public class TblPromotionRecordParam {

    @NotNull
    @ApiModelProperty(value = "用户ID", required = true)
    private Long adminId;

    @NotNull
    @ApiModelProperty(value = "晋升前岗位ID", required = true)
    private Long postBeforeId;

    @NotNull
    @ApiModelProperty(value = "晋升后岗位ID", required = true)
    private Long postAfterId;

}
