package com.ecareer.modules.tbl.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 职业测评参数校验
 * @author Qing2514
 * @since 2023-07-22
 */
@Data
public class TblQuestionParam {

    @NotNull
    @ApiModelProperty(value = "试题类型ID", required = true)
    private Long typeId;

    @NotEmpty
    @ApiModelProperty(value = "题目内容", required = true)
    private String content;

    @NotEmpty
    @ApiModelProperty(value = "选项1", required = true)
    private String option1;

    @NotEmpty
    @ApiModelProperty(value = "选项2", required = true)
    private String option2;

    @NotEmpty
    @ApiModelProperty(value = "选项3", required = true)
    private String option3;

    @NotEmpty
    @ApiModelProperty(value = "选项4", required = true)
    private String option4;

    @NotEmpty
    @ApiModelProperty(value = "正确答案", required = true)
    private String answer;

    @ApiModelProperty(value = "解析")
    private String analysis;

}
