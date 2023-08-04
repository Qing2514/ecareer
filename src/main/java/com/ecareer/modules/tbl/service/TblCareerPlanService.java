package com.ecareer.modules.tbl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ecareer.modules.tbl.dto.TblCareerPlanParam;
import com.ecareer.modules.tbl.model.TblCareerPlan;

import java.util.List;

/**
 * <p>
 * 职业规划表 服务类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
public interface TblCareerPlanService extends IService<TblCareerPlan> {

    /**
     * 根据所属职业规划名称或职业规划名称分页获取职业规划列表
     * @param username 用户名
     * @return TblCareerPlan 职业规划
     */
    TblCareerPlan getByUsername(String username);

    /**
     * 根据所属职业规划名称或职业规划名称分页获取职业规划列表
     * @param adminId 用户ID
     * @param pageSize 页大小
     * @param pageNum 页码
     * @return Page<TblCareerPlan> 职业规划页面
     */
    Page<TblCareerPlan> getPage(Long adminId, Integer pageSize, Integer pageNum);

    /**
     * 新增职业规划
     * @param username 用户名
     * @param careerPlanParam 职业规划校验参数
     * @return int 成功标志
     */
    int addCareerPlan(String username, TblCareerPlanParam careerPlanParam);

    /**
     * 删除职业规划
     * @param username 用户名
     * @return int 成功标志
     */
    boolean deleteByUsername(String username);

    /**
     * 修改职业规划
     * @param careerPlan 职业规划
     * @return int 成功标志
     */
    int updateCareerPlan(TblCareerPlan careerPlan);

}
