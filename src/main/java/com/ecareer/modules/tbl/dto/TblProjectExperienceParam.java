package com.ecareer.modules.tbl.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 项目经验参数校验
 * @author Qing2514
 * @since 2023-07-22
 */
@Data
public class TblProjectExperienceParam {

    @NotNull
    @ApiModelProperty(value = "用户ID", required = true)
    private Long adminId;

    @NotEmpty
    @ApiModelProperty(value = "项目名称", required = true)
    private String name;

    @ApiModelProperty(value = "项目描述")
    private String introduction;

    @NotNull
    @ApiModelProperty(value = "担任岗位ID", required = true)
    private Long postId;

    @NotNull
    @ApiModelProperty(value = "开始时间", required = true)
    private Date startTime;

    @NotNull
    @ApiModelProperty(value = "结束时间", required = true)
    private Date finishTime;

}
