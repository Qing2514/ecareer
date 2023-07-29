package com.ecareer.modules.ums.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Qing2514
 * @since 2023-07-22
 */
@Data
public class UmsDepartmentParam {

    @NotEmpty
    @ApiModelProperty(value = "部门名称", required = true)
    private String name;

    @ApiModelProperty(value = "部门简介")
    private String introduction;

    @NotNull
    @ApiModelProperty(value = "部门管理人ID", required = true)
    private Long managerId;

}
