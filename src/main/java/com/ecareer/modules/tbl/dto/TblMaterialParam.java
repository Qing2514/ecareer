package com.ecareer.modules.tbl.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 培训资料参数校验
 * @author Qing2514
 * @since 2023-07-22
 */
@Data
@AllArgsConstructor
public class TblMaterialParam {

    @NotNull
    @ApiModelProperty(value = "上传用户ID", required = true)
    private Long adminId;

    @NotNull
    @ApiModelProperty(value = "资料类型ID", required = true)
    private Long typeId;

    @NotNull
    @ApiModelProperty(value = "资料所属课程ID", required = true)
    private Long courseId;

    @NotNull
    @ApiModelProperty(value = "资料所属学科ID", required = true)
    private Long subjectId;

}
