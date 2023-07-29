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
 * 培训资料学科表
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-21
 */
@Getter
@Setter
@TableName("tbl_materials_subject")
@ApiModel(value = "TblMaterialsSubject对象", description = "培训资料学科表")
public class TblMaterialSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("资料学科ID")
    private Long id;

    @ApiModelProperty("学科名称（计算机、金融）")
    private String name;


}
