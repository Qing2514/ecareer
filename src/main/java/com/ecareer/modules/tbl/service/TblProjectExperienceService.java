package com.ecareer.modules.tbl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ecareer.modules.tbl.dto.TblProjectExperienceParam;
import com.ecareer.modules.tbl.model.TblProjectExperience;

import java.util.List;

/**
 * <p>
 * 项目经验表 服务类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
public interface TblProjectExperienceService extends IService<TblProjectExperience> {

    /**
     * 根据项目经验ID获取项目经验
     * @param id 用户ID
     * @return TblProjectExperience 项目经验信息
     */
    List<TblProjectExperience> getProjectExperienceByUserId(Long id);

    /**
     * 根据所属项目经验名称或项目经验名称分页获取项目经验列表
     * @param projectName 项目名称
     * @param pageSize 页大小
     * @param pageNum 页码
     * @return Page<TblProjectExperience> 项目经验页面
     */
    Page<TblProjectExperience> getPage(String projectName, Integer pageSize, Integer pageNum);

    /**
     * 新增项目经验
     * @param projectExperienceParam 项目经验校验参数
     * @return int 成功标志
     */
    int addProjectExperience(TblProjectExperienceParam projectExperienceParam);

    /**
     * 根据项目经验ID删除项目经验
     * @param id 项目经验ID
     * @return int 成功标志
     */
    int deleteProjectExperience(Long id);

    /**
     * 修改项目经验
     * @param projectExperience 项目经验
     * @return int 成功标志
     */
    int updateProjectExperience(TblProjectExperience projectExperience);

}
