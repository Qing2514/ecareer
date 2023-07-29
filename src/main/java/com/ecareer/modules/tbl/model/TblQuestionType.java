package com.ecareer.modules.tbl.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 试题类型表
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-21
 */
@Data
@TableName("tbl_question_type")
@ApiModel(value = "TblQuestionTNoe对象", description = "试题类型表")
public class TblQuestionType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    @ApiModelProperty("试题类型ID")
    private Long id;

    @ApiModelProperty("类型名称")
    private String name;

}
