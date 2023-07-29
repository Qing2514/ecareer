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
 * 项目经验表
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-21
 */
@Getter
@Setter
@TableName("tbl_project_experience")
@ApiModel(value = "TblProjectExperience对象", description = "项目经验表")
public class TblProjectExperience implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("用户id")
    private Long adminId;

    @ApiModelProperty("项目名称")
    private String name;

    @ApiModelProperty("项目描述")
    private String introduction;

    @ApiModelProperty("担任岗位ID")
    private Long postId;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date finishTime;


}
