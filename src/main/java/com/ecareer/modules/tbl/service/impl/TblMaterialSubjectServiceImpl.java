package com.ecareer.modules.tbl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecareer.modules.tbl.mapper.TblMaterialSubjectMapper;
import com.ecareer.modules.tbl.model.TblMaterialSubject;
import com.ecareer.modules.tbl.service.TblMaterialSubjectService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 培训资料所属学科 服务实现类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-21
 */
@Service
public class TblMaterialSubjectServiceImpl extends ServiceImpl<TblMaterialSubjectMapper, TblMaterialSubject> implements TblMaterialSubjectService {

    @Override
    public int addSubject(String name) {
        LambdaQueryWrapper<TblMaterialSubject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblMaterialSubject::getName, name);
        if (getOne(wrapper) != null) {
            return -1;
        }
        TblMaterialSubject subject = new TblMaterialSubject();
        subject.setName(name);
        save(subject);
        return 1;
    }
}
