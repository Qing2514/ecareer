package com.ecareer.modules.ums.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecareer.modules.ums.dto.UmsPostParam;
import com.ecareer.modules.ums.mapper.UmsPostMapper;
import com.ecareer.modules.ums.model.UmsDepartment;
import com.ecareer.modules.ums.model.UmsPost;
import com.ecareer.modules.ums.service.UmsDepartmentService;
import com.ecareer.modules.ums.service.UmsPostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台岗位表 服务实现类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@Service
public class UmsPostServiceImpl extends ServiceImpl<UmsPostMapper, UmsPost> implements UmsPostService {

    @Autowired
    private UmsDepartmentService departmentService;

    @Override
    public UmsPost getPostById(Long id) {
        LambdaQueryWrapper<UmsPost> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsPost::getId, id).eq(UmsPost::getDeleted, 0);
        return getOne(wrapper);
    }

    @Override
    public Page<UmsPost> getPage(String departmentName, String postName, Integer pageSize, Integer pageNum) {
        Page<UmsPost> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<UmsPost> postWrapper = new LambdaQueryWrapper<>();
        postWrapper.eq(UmsPost::getDeleted, 0);
        if (StrUtil.isNotEmpty(postName)) {
            postWrapper.like(UmsPost::getName, postName);
        }
        LambdaQueryWrapper<UmsDepartment> departmentWrapper = new LambdaQueryWrapper<>();
        departmentWrapper.eq(UmsDepartment::getDeleted, 0);
        if (StrUtil.isNotEmpty(departmentName)) {
            departmentWrapper.like(UmsDepartment::getName, departmentName);
            List<UmsDepartment> departmentList = departmentService.list(departmentWrapper);
            Long departmentId = departmentList.get(0).getId();
            postWrapper.eq(UmsPost::getDepartmentId, departmentId);
        }
        return page(page, postWrapper);
    }

    @Override
    public int addPost(UmsPostParam postParam) {
        UmsDepartment department = departmentService.getDepartmentById(postParam.getDepartmentId());
        if (department == null) {
            return -1;
        }
        if (postParam.getSuperiorId() != null) {
            UmsPost tmp = getPostById(postParam.getSuperiorId());
            if (tmp == null) {
                return -2;
            }
        }
        LambdaQueryWrapper<UmsPost> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsPost::getName, postParam.getName()).eq(UmsPost::getDeleted, 0);
        if (getOne(wrapper) != null) {
            return -3;
        }
        UmsPost post = new UmsPost();
        BeanUtils.copyProperties(postParam, post);
        post.setAdminNumber(0);
        post.setCreateTime(new Date());
        post.setDeleted(0);
        save(post);
        return 1;
    }

    @Override
    public int deletePost(Long id) {
        UmsPost post = getPostById(id);
        if (post == null) {
            return -1;
        }
        post.setUpdateTime(new Date());
        post.setDeleted(1);
        updateById(post);
        // 设置上级岗位ID为该岗位ID的值为NULL
        LambdaUpdateWrapper<UmsPost> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UmsPost::getSuperiorId, post.getId()).set(UmsPost::getSuperiorId, null);
        update(wrapper);
        return 1;
    }

    @Override
    public void deletePostByDepartmentId(Long departmentId) {
        LambdaUpdateWrapper<UmsPost> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UmsPost::getDepartmentId, departmentId)
                .set(UmsPost::getUpdateTime, new Date())
                .set(UmsPost::getDeleted, 1);
        update(wrapper);
    }

    @Override
    public int updatePost(UmsPost post) {
        UmsPost oldPost = getPostById(post.getId());
        if (oldPost == null) {
            return -1;
        }
        UmsDepartment department = departmentService.getDepartmentById(post.getDepartmentId());
        if (department == null) {
            return -2;
        }
        UmsPost supPost = getPostById(post.getSuperiorId());
        if (supPost == null) {
            return -3;
        }
        post.setUpdateTime(new Date());
        updateById(post);
        return 1;
    }

}
