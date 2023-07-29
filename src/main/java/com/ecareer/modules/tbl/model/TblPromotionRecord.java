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
 * 晋升记录表
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-21
 */
@Getter
@Setter
@TableName("tbl_promotion_record")
@ApiModel(value = "TblPromotionRecord对象", description = "晋升记录表")
public class TblPromotionRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("用户ID")
    private Long adminId;

    @ApiModelProperty("晋升前岗位ID")
    private Long postBeforeId;

    @ApiModelProperty("晋升后岗位ID")
    private Long postAfterId;

    @ApiModelProperty("晋升时间")
    private Date promotionTime;


}
