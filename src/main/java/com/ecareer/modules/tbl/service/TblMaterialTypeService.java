package com.ecareer.modules.tbl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecareer.modules.tbl.model.TblMaterialType;

/**
 * <p>
 * 培训资料类型 服务类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-21
 */
public interface TblMaterialTypeService extends IService<TblMaterialType> {

    /**
     * 新增培训资料类型
     * @param name 培训资料类型名称
     * @return int 成功标志
     */
    int addType(String name);

}
