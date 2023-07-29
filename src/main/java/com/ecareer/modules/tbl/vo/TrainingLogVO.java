package com.ecareer.modules.tbl.vo;

import com.ecareer.modules.tbl.model.TblTrainingLog;
import com.ecareer.modules.tbl.model.TblMaterial;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 培训记录视图
 * @author Qing2514
 * @since 23-7-23
 */
@Data
@AllArgsConstructor
public class TrainingLogVO {

    @ApiModelProperty(value = "培训记录")
    TblTrainingLog trainingLog;

    @ApiModelProperty(value = "培训资料")
    TblMaterial trainingMaterials;

}
