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
 * 培训资料表
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-21
 */
@Getter
@Setter
@TableName("tbl_training_materials")
@ApiModel(value = "TblTrainingMaterials对象", description = "培训资料表")
public class TblMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("资料名称")
    private String name;

    @ApiModelProperty("资料路径")
    private String url;

    @ApiModelProperty("资料大小（单位B）")
    private Long size;

    @ApiModelProperty("资料类型ID（文本、视频）")
    private Long typeId;

    @ApiModelProperty("所属课程ID（计网、数据库）")
    private Long courseId;

    @ApiModelProperty("所属学科ID（计算机、金融）")
    private Long subjectId;

    @ApiModelProperty("上传用户ID")
    private Long adminId;

    @ApiModelProperty("上传时间")
    private Date uploadTime;

    @ApiModelProperty("逻辑删除标识（0为否，1为是）")
    private Integer deleted;


}
