package com.ecareer.modules.tbl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecareer.modules.tbl.model.TblMaterialCourse;

/**
 * <p>
 * 培训资料所属课程 服务类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-21
 */
public interface TblMaterialCourseService extends IService<TblMaterialCourse> {

    /**
     * 新增培训资料所属课程
     * @param name 培训资料所属课程名称
     * @param subjectId 课程所属学科ID
     * @return int 成功标志
     */
    int addCourse(String name, Long subjectId);

}
