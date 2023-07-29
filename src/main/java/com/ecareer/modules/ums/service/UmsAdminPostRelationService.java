package com.ecareer.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecareer.modules.ums.model.UmsAdminPostRelation;

import java.util.List;

/**
 * <p>
 * 后台用户岗位关联表 服务类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
public interface UmsAdminPostRelationService extends IService<UmsAdminPostRelation> {

    /**
     * 获取指定用户待过的岗位信息
     * @param adminId 用户id
     * @return List<UmsAdminPostRelation> 用户岗位关联列表
     */
    List<UmsAdminPostRelation> getAdminList(Long adminId);

    /**
     * 获取指定用户待过的岗位信息
     * @param adminId 用户id
     * @param postId 岗位id
     * @return int 成功标志
     */
    int addAdminPost(Long adminId, Long postId);

}
