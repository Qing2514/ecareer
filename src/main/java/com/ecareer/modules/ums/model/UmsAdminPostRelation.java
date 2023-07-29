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
 * 后台用户岗位关联表
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@Getter
@Setter
@TableName("ums_admin_post_relation")
@ApiModel(value = "UmsAdminPostRelation对象", description = "后台用户岗位关联表")
public class UmsAdminPostRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户ID")
    private Long adminId;

    @ApiModelProperty("待过的岗位ID")
    private Long postId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("逻辑删除标识（0为否，1为是）")
    private Integer deleted;

}
