package com.ecareer.modules.tbl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecareer.modules.tbl.model.TblMaterialSubject;

/**
 * <p>
 * 培训资料所属学科 服务类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-21
 */
public interface TblMaterialSubjectService extends IService<TblMaterialSubject> {

    /**
     * 新增培训资料所属学科
     * @param name 培训资料所属学科名称
     * @return int 成功标志
     */
    int addSubject(String name);

}
