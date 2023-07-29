package com.ecareer.modules.tbl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ecareer.modules.tbl.dto.TblQuestionParam;
import com.ecareer.modules.tbl.model.TblChoiceQuestion;

import java.util.List;

/**
 * <p>
 * 职业测评表 服务类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
public interface TblQuestionService extends IService<TblChoiceQuestion> {

    /**
     * 从题库随机抽选题目发放给用户
     * @return List<List<TblChoiceQuestion>> 试题列表
     */
    List<List<TblChoiceQuestion>> getQuestions();

    /**
     * 根据试题类型ID获取测试题目
     * @param typeId 试题类型ID
     * @return List<TblChoiceQuestion> 试题列表
     */
    List<TblChoiceQuestion> getByTypeId(Long typeId);

    /**
     * 根据试题类型ID分页获取职业测评列表
     * @param typeId 试题类型ID
     * @param pageSize 页大小
     * @param pageNum 页码
     * @return Page<TblChoiceQuestion> 职业测评页面
     */
    Page<TblChoiceQuestion> getPage(Long typeId, Integer pageSize, Integer pageNum);

    /**
     * 新增试题
     * @param questionParam 试题校验参数
     * @return int 成功标志
     */
    int addQuestion(TblQuestionParam questionParam);

    /**
     * 修改试题
     * @param question 试题
     * @return int 成功标志
     */
    int updateQuestion(TblChoiceQuestion question);

}
