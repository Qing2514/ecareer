package com.ecareer.modules.tbl.vo;

import com.ecareer.modules.tbl.model.TblPromotionRecord;
import com.ecareer.modules.ums.model.UmsPost;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 晋升记录视图
 * @author Qing2514
 * @since 23-7-23
 */
@Data
@AllArgsConstructor
public class PromotionRecordVO {

    @ApiModelProperty(value = "晋升记录")
    TblPromotionRecord promotionRecord;

    @ApiModelProperty(value = "晋升前岗位")
    UmsPost postBefore;

    @ApiModelProperty(value = "晋升后岗位")
    UmsPost postAfter;

}
