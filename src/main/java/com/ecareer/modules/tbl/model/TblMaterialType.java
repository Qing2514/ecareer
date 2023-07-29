package com.ecareer.modules.tbl.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 培训资料类型表
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-21
 */
@Getter
@Setter
@TableName("tbl_materials_type")
@ApiModel(value = "TblMaterialsType对象", description = "培训资料类型表")
public class TblMaterialType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    @ApiModelProperty("资料类型ID")
    private Long id;

    @ApiModelProperty("类型名称（文本、视频）")
    private String name;


}
