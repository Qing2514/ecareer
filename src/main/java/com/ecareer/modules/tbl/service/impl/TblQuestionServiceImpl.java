package com.ecareer.modules.tbl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecareer.modules.tbl.dto.TblQuestionParam;
import com.ecareer.modules.tbl.mapper.TblChoiceQuestionMapper;
import com.ecareer.modules.tbl.model.TblChoiceQuestion;
import com.ecareer.modules.tbl.model.TblQuestionType;
import com.ecareer.modules.tbl.service.TblQuestionService;
import com.ecareer.modules.tbl.service.TblQuestionTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 职业测评表 服务实现类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@Service
public class TblQuestionServiceImpl extends ServiceImpl<TblChoiceQuestionMapper, TblChoiceQuestion> implements TblQuestionService {

    @Autowired
    private TblQuestionTypeService questionTypeService;

    @Override
    public List<List<TblChoiceQuestion>> getQuestions() {
        List<TblQuestionType> questionTypeList = questionTypeService.list();
        List<List<TblChoiceQuestion>> questionList = questionTypeList.stream().map(questionType -> {
            LambdaQueryWrapper<TblChoiceQuestion> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(TblChoiceQuestion::getTypeId, questionType.getId());
            List<TblChoiceQuestion> questions = list(wrapper);
            Collections.shuffle(questions);
            // 确保最多只返回10道题
            int questionSize = Math.min(10, questions.size());
            return questions.subList(0, questionSize);
        }).collect(Collectors.toList());
        return questionList;
    }

    @Override
    public List<TblChoiceQuestion> getByTypeId(Long typeId) {
        LambdaQueryWrapper<TblChoiceQuestion> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblChoiceQuestion::getTypeId, typeId);
        return list(wrapper);
    }

    @Override
    public Page<TblChoiceQuestion> getPage(Long typeId, Integer pageSize, Integer pageNum) {
        Page<TblChoiceQuestion> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<TblChoiceQuestion> questionWrapper = new LambdaQueryWrapper<>();
        if (typeId != null) {
            questionWrapper.eq(TblChoiceQuestion::getTypeId, typeId);
        }
        return page(page, questionWrapper);
    }

    @Override
    public int addQuestion(TblQuestionParam questionParam) {
        TblQuestionType questionType = questionTypeService.getById(questionParam.getTypeId());
        if (questionType == null) {
            return -1;
        }
        TblChoiceQuestion question = new TblChoiceQuestion();
        BeanUtils.copyProperties(questionParam, question);
        save(question);
        return 1;
    }

    @Override
    public int updateQuestion(TblChoiceQuestion question) {
        TblChoiceQuestion oldQuestion = getById(question.getId());
        if (oldQuestion == null) {
            return -1;
        }
        TblQuestionType questionType = questionTypeService.getById(oldQuestion.getTypeId());
        if (questionType == null) {
            return -2;
        }
        updateById(question);
        return 1;
    }

}
