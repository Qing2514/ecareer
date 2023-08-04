package com.ecareer.modules.ums.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecareer.modules.ums.dto.UmsDepartmentParam;
import com.ecareer.modules.ums.mapper.UmsDepartmentMapper;
import com.ecareer.modules.ums.model.UmsAdmin;
import com.ecareer.modules.ums.model.UmsDepartment;
import com.ecareer.modules.ums.service.UmsAdminService;
import com.ecareer.modules.ums.service.UmsDepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 后台部门表 服务实现类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@Service
public class UmsDepartmentServiceImpl extends ServiceImpl<UmsDepartmentMapper, UmsDepartment> implements UmsDepartmentService {

    @Autowired
    private UmsAdminService adminService;

    @Override
    public UmsDepartment getDepartmentById(Long id) {
        LambdaQueryWrapper<UmsDepartment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsDepartment::getId, id).eq(UmsDepartment::getDeleted, 0);
        return getOne(wrapper);
    }

    @Override
    public Page<UmsDepartment> getPage(String departmentName, Integer pageSize, Integer pageNum) {
        Page<UmsDepartment> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<UmsDepartment> departmentWrapper = new LambdaQueryWrapper<>();
        departmentWrapper.eq(UmsDepartment::getDeleted, 0);
        if (StrUtil.isNotEmpty(departmentName)) {
            departmentWrapper.like(UmsDepartment::getName, departmentName);
        }
        return page(page, departmentWrapper);
    }

    @Override
    public int addDepartment(UmsDepartmentParam departmentParam) {
        UmsAdmin user = adminService.getAdminById(departmentParam.getManagerId());
        if (user == null) {
            return -1;
        }
        LambdaQueryWrapper<UmsDepartment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsDepartment::getName, departmentParam.getName()).eq(UmsDepartment::getDeleted, 0);
        if (getOne(wrapper) != null) {
            return -2;
        }
        UmsDepartment department = new UmsDepartment();
        BeanUtils.copyProperties(departmentParam, department);
        department.setAdminNumber(0);
        department.setCreateTime(new Date());
        department.setDeleted(0);
        save(department);
        return 1;
    }

    @Override
    public int deleteDepartment(Long id) {
        UmsDepartment department = getDepartmentById(id);
        if (department == null) {
            return -1;
        }
        department.setUpdateTime(new Date());
        department.setDeleted(1);
        updateById(department);
        return 1;
    }

    @Override
    public int updateDepartment(UmsDepartment department) {
        UmsDepartment oldDepartment = getDepartmentById(department.getId());
        if (oldDepartment == null) {
            return -1;
        }
        UmsAdmin user = adminService.getAdminById(department.getManagerId());
        if (user == null) {
            return -2;
        }
        department.setUpdateTime(new Date());
        updateById(department);
        return 1;
    }
    
}
