package com.ecareer.modules.tbl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecareer.modules.tbl.mapper.TblMaterialCourseMapper;
import com.ecareer.modules.tbl.model.TblMaterialCourse;
import com.ecareer.modules.tbl.model.TblMaterialSubject;
import com.ecareer.modules.tbl.service.TblMaterialCourseService;
import com.ecareer.modules.tbl.service.TblMaterialSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 培训资料所属课程 服务实现类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-21
 */
@Service
public class TblMaterialCourseServiceImpl extends ServiceImpl<TblMaterialCourseMapper, TblMaterialCourse> implements TblMaterialCourseService {

    @Autowired
    private TblMaterialSubjectService materialSubjectService;

    @Override
    public int addCourse(String name, Long subjectId) {
        LambdaQueryWrapper<TblMaterialCourse> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblMaterialCourse::getName, name);
        if (getOne(wrapper) != null) {
            return -1;
        }
        TblMaterialSubject subject = materialSubjectService.getById(subjectId);
        if (subject == null) {
            return -2;
        }
        TblMaterialCourse course = new TblMaterialCourse();
        course.setName(name);
        course.setSubjectId(subjectId);
        save(course);
        return 1;
    }
}
