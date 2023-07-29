package com.ecareer.modules.ums.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ecareer.modules.ums.dto.UmsPostParam;
import com.ecareer.modules.ums.model.UmsPost;

/**
 * <p>
 * 后台岗位表 服务类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
public interface UmsPostService extends IService<UmsPost> {

    /**
     * 根据岗位ID获取指定岗位
     * @param id 用户ID
     * @return UmsPost 岗位信息
     */
    UmsPost getPostById(Long id);

    /**
     * 根据所属部门名称或岗位名称分页获取岗位列表
     * @param departmentName 所属部门名称
     * @param postName 岗位名称
     * @param pageSize 页大小
     * @param pageNum 页码
     * @return Page<UmsPost> 岗位页面
     */
    Page<UmsPost> getPage(String departmentName, String postName, Integer pageSize, Integer pageNum);

    /**
     * 新增岗位
     * @param postParam 岗位校验参数
     * @return int 成功标志
     */
    int addPost(UmsPostParam postParam);

    /**
     * 根据岗位ID删除岗位
     * @param id 岗位ID
     * @return int 成功标志
     */
    int deletePost(Long id);

    /**
     * 修改岗位
     * @param post 岗位
     * @return int 成功标志
     */
    int updatePost(UmsPost post);

}
