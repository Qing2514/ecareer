package com.ecareer.modules.tbl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecareer.modules.tbl.dto.TblCareerPlanParam;
import com.ecareer.modules.tbl.mapper.TblCareerPlanMapper;
import com.ecareer.modules.tbl.model.TblCareerPlan;
import com.ecareer.modules.tbl.service.TblCareerPlanService;
import com.ecareer.modules.ums.model.UmsAdmin;
import com.ecareer.modules.ums.service.UmsAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 职业规划表 服务实现类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@Service
public class TblCareerPlanServiceImpl extends ServiceImpl<TblCareerPlanMapper, TblCareerPlan> implements TblCareerPlanService {

    @Autowired
    private UmsAdminService adminService;

    @Override
    public TblCareerPlan getByUsername(String username) {
        UmsAdmin admin = adminService.getAdminByUsername(username);
        return getById(admin.getId());
    }

    @Override
    public Page<TblCareerPlan> getPage(Long adminId, Integer pageSize, Integer pageNum) {
        Page<TblCareerPlan> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<TblCareerPlan> careerPlanWrapper = new LambdaQueryWrapper<>();
        if (adminId != null) {
            careerPlanWrapper.eq(TblCareerPlan::getAdminId, adminId);
        }
        return page(page, careerPlanWrapper);
    }

    @Override
    public int addCareerPlan(String username, TblCareerPlanParam careerPlanParam) {
        UmsAdmin user = adminService.getAdminByUsername(username);
        if (user == null) {
            return -1;
        }
        LambdaQueryWrapper<TblCareerPlan> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblCareerPlan::getAdminId, user.getId());
        TblCareerPlan careerPlan = getOne(wrapper);
        if (careerPlan != null) {
            return -2;
        }
        careerPlan = new TblCareerPlan();
        BeanUtils.copyProperties(careerPlanParam, careerPlan);
        careerPlan.setCreateTime(new Date());
        save(careerPlan);
        return 1;
    }

    @Override
    public boolean deleteByUsername(String username) {
        UmsAdmin admin = adminService.getAdminByUsername(username);
        return removeById(admin.getId());
    }

    @Override
    public int updateCareerPlan(TblCareerPlan careerPlan) {
        TblCareerPlan oldCareerPlan = getById(careerPlan.getId());
        if (oldCareerPlan == null) {
            return -1;
        }
        UmsAdmin user = adminService.getAdminById(careerPlan.getAdminId());
        if (user == null) {
            return -2;
        }
        careerPlan.setUpdateTime(new Date());
        updateById(careerPlan);
        return 1;
    }

}
