package com.ecareer.modules.tbl.service;

import com.ecareer.modules.tbl.model.TblQuestionType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 试题类型 服务类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-21
 */
public interface TblQuestionTypeService extends IService<TblQuestionType> {

    /**
     * 新增试题类型
     * @param name 试题类型名称
     * @return int 成功标志
     */
    int addType(String name);

}
