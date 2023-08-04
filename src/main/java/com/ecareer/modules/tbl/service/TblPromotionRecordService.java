package com.ecareer.modules.tbl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ecareer.modules.tbl.dto.TblPromotionRecordParam;
import com.ecareer.modules.tbl.model.TblPromotionRecord;
import com.ecareer.modules.tbl.vo.PromotionRecordVO;

import java.util.List;

/**
 * <p>
 * 晋升记录表 服务类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
public interface TblPromotionRecordService extends IService<TblPromotionRecord> {

    /**
     * 根据用户ID获取晋升记录
     * @param id 用户ID
     * @return TblPromotionRecord 晋升记录信息
     */
    List<PromotionRecordVO> getPromotionRecordByUserId(Long id);

    /**
     * 根据用户名获取晋升记录
     * @param username 用户名
     * @return TblPromotionRecord 晋升记录信息
     */
    List<PromotionRecordVO> getPromotionRecordByUsername(String username);

    /**
     * 根据所属晋升记录名称或晋升记录名称分页获取晋升记录列表
     * @param adminId 用户ID
     * @param pageSize 页大小
     * @param pageNum 页码
     * @return Page<TblPromotionRecord> 晋升记录页面
     */
    Page<PromotionRecordVO> getPage(Long adminId, Integer pageSize, Integer pageNum);

    /**
     * 新增晋升记录
     * @param username 用户名
     * @param promotionRecordParam 晋升记录校验参数
     * @return int 成功标志
     */
    int addPromotionRecord(String username, TblPromotionRecordParam promotionRecordParam);

    /**
     * 根据晋升记录ID删除晋升记录
     * @param id 晋升记录ID
     * @return int 成功标志
     */
    int deletePromotionRecordById(Long id);

    /**
     * 根据用户名和晋升记录ID删除晋升记录
     * @param username 用户名
     * @param id 晋升记录ID
     * @return int 成功标志
     */
    int deletePromotionRecordByUsername(String username, Long id);

    /**
     * 修改晋升记录
     * @param promotionRecord 晋升记录
     * @return int 成功标志
     */
    int updatePromotionRecord(TblPromotionRecord promotionRecord);

}
