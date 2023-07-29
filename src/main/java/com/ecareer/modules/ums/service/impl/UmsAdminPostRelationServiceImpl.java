package com.ecareer.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ecareer.modules.ums.model.UmsAdminPostRelation;
import com.ecareer.modules.ums.mapper.UmsAdminPostRelationMapper;
import com.ecareer.modules.ums.service.UmsAdminPostRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台用户岗位关联表 服务实现类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@Service
public class UmsAdminPostRelationServiceImpl extends ServiceImpl<UmsAdminPostRelationMapper, UmsAdminPostRelation> implements UmsAdminPostRelationService {

    @Override
    public List<UmsAdminPostRelation> getAdminList(Long adminId) {
        LambdaQueryWrapper<UmsAdminPostRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsAdminPostRelation::getAdminId, adminId).eq(UmsAdminPostRelation::getDeleted, 0);
        return list(wrapper);
    }

    @Override
    public int addAdminPost(Long adminId, Long postId) {
        LambdaQueryWrapper<UmsAdminPostRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsAdminPostRelation::getAdminId, adminId).eq(UmsAdminPostRelation::getPostId, postId).eq(UmsAdminPostRelation::getDeleted, 0);
        if (getOne(wrapper) != null) {
            return -1;
        }
        UmsAdminPostRelation adminPostRelation = new UmsAdminPostRelation();
        adminPostRelation.setAdminId(adminId);
        adminPostRelation.setPostId(postId);
        adminPostRelation.setCreateTime(new Date());
        adminPostRelation.setDeleted(0);
        save(adminPostRelation);
        return 1;
    }

}
