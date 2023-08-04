package com.ecareer.modules.tbl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ecareer.modules.tbl.dto.TblTrainingLogParam;
import com.ecareer.modules.tbl.model.TblTrainingLog;
import com.ecareer.modules.tbl.vo.TrainingLogVO;

import java.util.List;

/**
 * <p>
 * 培训记录表 服务类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
public interface TblTrainingLogService extends IService<TblTrainingLog> {

    /**
     * 根据用户ID获取培训记录
     * @param id 用户ID
     * @return TblTrainingLog 培训记录列表
     */
    List<TrainingLogVO> getTrainingLogByUserId(Long id);

    /**
     * 根据用户名获取培训记录
     * @param username 用户名
     * @return TblTrainingLog 培训记录列表
     */
    List<TrainingLogVO> getTrainingLogByUsername(String username);

    /**
     * 根据所属培训记录名称或培训记录名称分页获取培训记录列表
     * @param adminId 用户ID
     * @param pageSize 页大小
     * @param pageNum 页码
     * @return Page<TrainingLogVO> 培训记录页面
     */
    Page<TrainingLogVO> getPage(Long adminId, Integer pageSize, Integer pageNum);

    /**
     * 新增培训记录
     * @param username 用户名
     * @param trainingLogParam 培训记录校验参数
     * @return int 成功标志
     */
    int addTrainingLog(String username, TblTrainingLogParam trainingLogParam);

    /**
     * 根据培训记录ID删除培训记录
     * @param materialId 培训资料ID
     * @return int 成功标志
     */
    int deleteTrainingLogById(Long materialId);

    /**
     * 根据用户名和培训记录ID删除培训记录
     * @param username 用户名
     * @param materialId 培训资料ID
     * @return int 成功标志
     */
    int deleteTrainingLogByUsername(String username, Long materialId);

    /**
     * 修改培训记录
     * @param trainingLog 培训记录
     * @return int 成功标志
     */
    int updateTrainingLog(TblTrainingLog trainingLog);

}
