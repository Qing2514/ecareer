package com.ecareer.modules.ums.model;

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
 * 后台部门表
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@Getter
@Setter
@TableName("ums_department")
@ApiModel(value = "UmsDepartment对象", description = "后台部门表")
public class UmsDepartment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("部门简介")
    private String introduction;

    @ApiModelProperty("部门管理人ID")
    private Long managerId;

    @ApiModelProperty("部门人数")
    private Integer adminNumber;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("逻辑删除标识（0为否，1为是）")
    private Integer deleted;

}
