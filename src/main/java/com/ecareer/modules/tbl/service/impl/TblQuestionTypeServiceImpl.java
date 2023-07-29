package com.ecareer.modules.tbl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ecareer.modules.tbl.model.TblQuestionType;
import com.ecareer.modules.tbl.mapper.TblQuestionTypeMapper;
import com.ecareer.modules.tbl.service.TblQuestionTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 试题类型 服务实现类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-21
 */
@Service
public class TblQuestionTypeServiceImpl extends ServiceImpl<TblQuestionTypeMapper, TblQuestionType> implements TblQuestionTypeService {

    @Override
    public int addType(String name) {
        LambdaQueryWrapper<TblQuestionType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblQuestionType::getName, name);
        if (getOne(wrapper) != null) {
            return -1;
        }
        TblQuestionType questionType = new TblQuestionType();
        questionType.setName(name);
        save(questionType);
        return 1;
    }
}
