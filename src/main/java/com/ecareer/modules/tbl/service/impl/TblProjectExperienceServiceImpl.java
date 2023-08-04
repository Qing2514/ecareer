package com.ecareer.modules.tbl.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecareer.modules.tbl.dto.TblProjectExperienceParam;
import com.ecareer.modules.tbl.mapper.TblProjectExperienceMapper;
import com.ecareer.modules.tbl.model.TblProjectExperience;
import com.ecareer.modules.tbl.service.TblProjectExperienceService;
import com.ecareer.modules.ums.model.UmsAdmin;
import com.ecareer.modules.ums.model.UmsPost;
import com.ecareer.modules.ums.service.UmsAdminService;
import com.ecareer.modules.ums.service.UmsPostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 项目经验表 服务实现类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@Service
public class TblProjectExperienceServiceImpl extends ServiceImpl<TblProjectExperienceMapper, TblProjectExperience> implements TblProjectExperienceService {

    @Autowired
    private UmsAdminService adminService;

    @Autowired
    private UmsPostService postService;

    @Override
    public List<TblProjectExperience> getProjectExperienceByUserId(Long id) {
        LambdaQueryWrapper<TblProjectExperience> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblProjectExperience::getAdminId, id);
        return list(wrapper);
    }

    @Override
    public List<TblProjectExperience> getProjectExperienceByUsername(String username) {
        UmsAdmin admin = adminService.getAdminByUsername(username);
        return getProjectExperienceByUserId(admin.getId());
    }

    @Override
    public Page<TblProjectExperience> getPage(String projectName, Integer pageSize, Integer pageNum) {
        Page<TblProjectExperience> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<TblProjectExperience> projectExperienceWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(projectName)) {
            projectExperienceWrapper.like(TblProjectExperience::getName, projectName);
        }
        return page(page, projectExperienceWrapper);
    }

    @Override
    public int addProjectExperience(String username, TblProjectExperienceParam projectExperienceParam) {
        UmsAdmin user = adminService.getAdminByUsername(username);
        if (user == null) {
            return -1;
        }
        UmsPost post = postService.getPostById(projectExperienceParam.getPostId());
        if (post == null) {
            return -2;
        }
        LambdaQueryWrapper<TblProjectExperience> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblProjectExperience::getName, projectExperienceParam.getName());
        if (getOne(wrapper) != null) {
            return -3;
        }
        TblProjectExperience projectExperience = new TblProjectExperience();
        BeanUtils.copyProperties(projectExperienceParam, projectExperience);
        save(projectExperience);
        return 1;
    }

    @Override
    public int deleteProjectExperienceById(Long id) {
        TblProjectExperience projectExperience = getById(id);
        if (projectExperience == null) {
            return -1;
        }
        removeById(id);
        return 1;
    }

    @Override
    public int deleteProjectExperienceByUsername(String username, Long id) {
        UmsAdmin admin = adminService.getAdminByUsername(username);
        TblProjectExperience projectExperience = getById(id);
        if (projectExperience == null) {
            return -1;
        }
        if (!projectExperience.getAdminId().equals(admin.getId())) {
            return -2;
        }
        removeById(id);
        return 1;
    }

    @Override
    public int updateProjectExperience(TblProjectExperience projectExperience) {
        TblProjectExperience oldProjectExperience = getById(projectExperience.getId());
        if (oldProjectExperience == null) {
            return -1;
        }
        UmsAdmin user = adminService.getAdminById(projectExperience.getAdminId());
        if (user == null) {
            return -2;
        }
        UmsPost post = postService.getPostById(projectExperience.getPostId());
        if (post == null) {
            return -3;
        }
        updateById(projectExperience);
        return 1;
    }
    
}
