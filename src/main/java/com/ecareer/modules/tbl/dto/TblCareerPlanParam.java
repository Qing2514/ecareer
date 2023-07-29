package com.ecareer.modules.tbl.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 职业规划参数校验
 * @author Qing2514
 * @since 2023-07-22
 */
@Data
public class TblCareerPlanParam {

    @NotNull
    @ApiModelProperty(value = "用户ID", required = true)
    private Long adminId;

    @NotEmpty
    @ApiModelProperty(value = "短期时间", required = true)
    private String shortTime;

    @NotEmpty
    @ApiModelProperty(value = "短期目标", required = true)
    private String shortGoals;

    @NotEmpty
    @ApiModelProperty(value = "长期时间", required = true)
    private String longTime;

    @NotEmpty
    @ApiModelProperty(value = "长期目标", required = true)
    private String longGoals;

    @ApiModelProperty(value = "目标薪资")
    private String salary;

    @ApiModelProperty(value = "目标岗位")
    private String post;

}
