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
 * 单项选择题试题表
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-21
 */
@Getter
@Setter
@TableName("tbl_choice_question")
@ApiModel(value = "TblChoiceQuestion对象", description = "单项选择题试题表")
public class TblChoiceQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("试题类型ID")
    private Long typeId;

    @ApiModelProperty("题目内容")
    private String content;

    @ApiModelProperty("选项1")
    private String option1;

    @ApiModelProperty("选项2")
    private String option2;

    @ApiModelProperty("选项3")
    private String option3;

    @ApiModelProperty("选项4")
    private String option4;

    @ApiModelProperty("正确答案")
    private String answer;

    @ApiModelProperty("解析")
    private String analysis;


}
