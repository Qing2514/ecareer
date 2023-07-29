package com.ecareer.modules.tbl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecareer.modules.tbl.mapper.TblMaterialTypeMapper;
import com.ecareer.modules.tbl.model.TblMaterialType;
import com.ecareer.modules.tbl.service.TblMaterialTypeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 培训资料类型 服务实现类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-21
 */
@Service
public class TblMaterialTypeServiceImpl extends ServiceImpl<TblMaterialTypeMapper, TblMaterialType> implements TblMaterialTypeService {

    @Override
    public int addType(String name) {
        LambdaQueryWrapper<TblMaterialType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblMaterialType::getName, name);
        if (getOne(wrapper) != null) {
            return -1;
        }
        TblMaterialType type = new TblMaterialType();
        type.setName(name);
        save(type);
        return 1;
    }
}
