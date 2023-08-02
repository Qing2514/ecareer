package com.ecareer.modules.ums.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Qing2514
 * @since 2023-07-22
 */
@Data
public class AdminBasicInfoParam {

    @NotEmpty
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    @ApiModelProperty(value = "头像")
    private String icon;

    @NotNull
    @ApiModelProperty(value = "性别", required = true)
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @NotEmpty
    @ApiModelProperty(value = "教育背景", required = true)
    private String background;

    @NotEmpty
    @ApiModelProperty(value = "工作经历", required = true)
    private String experience;

    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "个人简介")
    private String introduction;

    @NotNull
    @ApiModelProperty(value = "所属岗位ID", required = true)
    private Long postId;

    @NotNull
    @ApiModelProperty(value = "所属部门ID", required = true)
    private Long departmentId;

    @ApiModelProperty(value = "备注信息")
    private String note;

}
