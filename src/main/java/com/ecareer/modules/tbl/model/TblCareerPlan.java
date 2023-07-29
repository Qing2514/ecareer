package com.ecareer.modules.tbl.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 职业规划表
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-21
 */
@Getter
@Setter
@TableName("tbl_career_plan")
@ApiModel(value = "TblCareerPlan对象", description = "职业规划表")
public class TblCareerPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("用户ID")
    private Long adminId;

    @ApiModelProperty("短期时间")
    private String shortTime;

    @ApiModelProperty("短期目标")
    private String shortGoals;

    @ApiModelProperty("长期时间")
    private String longTime;

    @ApiModelProperty("长期目标")
    private String longGoals;

    @ApiModelProperty("目标薪资")
    private String salary;

    @ApiModelProperty("目标岗位")
    private String post;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;


}
