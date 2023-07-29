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
public class UmsPostParam {

    @NotNull
    @ApiModelProperty(value = "所属部门ID", required = true)
    private Long departmentId;

    @NotEmpty
    @ApiModelProperty(value = "岗位名称", required = true)
    private String name;

    @ApiModelProperty(value = "岗位介绍")
    private String introduction;

    @ApiModelProperty(value = "上级岗位ID")
    private Long superiorId;

}
