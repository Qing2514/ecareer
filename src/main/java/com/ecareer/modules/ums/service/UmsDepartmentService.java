package com.ecareer.modules.ums.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ecareer.modules.ums.dto.UmsDepartmentParam;
import com.ecareer.modules.ums.model.UmsDepartment;

/**
 * <p>
 * 后台部门表 服务类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
public interface UmsDepartmentService extends IService<UmsDepartment> {

    /**
     * 根据部门ID获取部门
     * @param id 部门ID
     * @return UmsDepartment 部门信息
     */
    UmsDepartment getDepartmentById(Long id);

    /**
     * 根据所属部门名称或部门名称分页获取部门列表
     * @param departmentName 部门名称
     * @param pageSize 页大小
     * @param pageNum 页码
     * @return Page<UmsDepartment> 部门页面
     */
    Page<UmsDepartment> getPage(String departmentName, Integer pageSize, Integer pageNum);

    /**
     * 新增部门
     * @param departmentParam 部门校验参数
     * @return int 成功标志
     */
    int addDepartment(UmsDepartmentParam departmentParam);

    /**
     * 根据部门ID删除部门
     * @param id 部门ID
     * @return int 成功标志
     */
    int deleteDepartment(Long id);

    /**
     * 修改部门
     * @param department 部门
     * @return int 成功标志
     */
    int updateDepartment(UmsDepartment department);

}
