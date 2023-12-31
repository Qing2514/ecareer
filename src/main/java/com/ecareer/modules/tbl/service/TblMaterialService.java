package com.ecareer.modules.tbl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ecareer.modules.tbl.dto.TblMaterialParam;
import com.ecareer.modules.tbl.model.TblMaterial;
import com.ecareer.modules.tbl.model.TblMaterialCourse;
import com.ecareer.modules.tbl.model.TblMaterialSubject;
import com.ecareer.modules.tbl.model.TblMaterialType;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 培训资料表 服务类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
public interface TblMaterialService extends IService<TblMaterial> {

    /**
     * 根据培训资料ID获取培训资料
     * @param id 培训资料ID
     * @return TblMaterial 培训资料
     */
    TblMaterial getMaterialsById(Long id);

    /**
     * 根据培训资料类型ID分页获取职业测评列表
     * @param typeId 培训资料类型ID
     * @param courseId 培训资料课程ID
     * @param subjectId 培训资料学科ID
     * @param pageSize 页大小
     * @param pageNum 页码
     * @return Page<TblMaterial> 培训资料页面
     */
    Page<TblMaterial> getPage(Long typeId, Long courseId, Long subjectId, Integer pageSize, Integer pageNum);

    /**
     * 分页获取职业测评类型
     * @param typeId 类型ID
     * @param pageSize 页大小
     * @param pageNum 页码
     * @return Page<TblMaterialType> 培训资料类型
     */
    Page<TblMaterialType> getTypePage(Long typeId, Integer pageSize, Integer pageNum);

    /**
     * 分页获取职业测评所属课程
     * @param courseId 课程ID
     * @param pageSize 页大小
     * @param pageNum 页码
     * @return Page<TblMaterialCourse> 培训资料所属课程
     */
    Page<TblMaterialCourse> getCoursePage(Long courseId, Integer pageSize, Integer pageNum);

    /**
     * 分页获取职业测评所属学科
     * @param subjectId 学科ID
     * @param pageSize 页大小
     * @param pageNum 页码
     * @return Page<TblMaterialSubject> 培训资料所属学科
     */
    Page<TblMaterialSubject> getSubjectPage(Long subjectId, Integer pageSize, Integer pageNum);

    /**
     * 新增培训资料
     * @param materialParam 培训资料校验参数
     * @param file 上传的文件
     * @return int 成功标志
     */
    int addMaterial(TblMaterialParam materialParam, MultipartFile file);

    /**
     * 根据培训资料ID删除培训资料
     * @param id 培训资料ID
     * @return int 成功标志
     */
    int deleteMaterialById(Long id);

    /**
     * 修改培训资料
     * @param material 培训资料
     * @return int 成功标志
     */
    int updateMaterial(TblMaterial material);

}
