package com.ecareer.modules.tbl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecareer.modules.tbl.dto.TblPromotionRecordParam;
import com.ecareer.modules.tbl.mapper.TblPromotionRecordMapper;
import com.ecareer.modules.tbl.model.TblPromotionRecord;
import com.ecareer.modules.tbl.service.TblPromotionRecordService;
import com.ecareer.modules.tbl.vo.PromotionRecordVO;
import com.ecareer.modules.ums.model.UmsAdmin;
import com.ecareer.modules.ums.model.UmsPost;
import com.ecareer.modules.ums.service.UmsAdminPostRelationService;
import com.ecareer.modules.ums.service.UmsAdminService;
import com.ecareer.modules.ums.service.UmsPostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 晋升记录表 服务实现类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@Service
public class TblPromotionRecordServiceImpl extends ServiceImpl<TblPromotionRecordMapper, TblPromotionRecord> implements TblPromotionRecordService {

    @Autowired
    private UmsAdminService adminService;

    @Autowired
    private UmsPostService postService;

    @Autowired
    private UmsAdminPostRelationService adminPostRelationService;

    @Override
    public List<PromotionRecordVO> getPromotionRecordByUserId(Long id) {
        LambdaQueryWrapper<TblPromotionRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblPromotionRecord::getAdminId, id);
        List<TblPromotionRecord> promotionRecordList = list(wrapper);
        return promotionRecordList.stream().map(promotionRecord -> {
            UmsPost postBefore = postService.getPostById(promotionRecord.getPostBeforeId());
            UmsPost postAfter = postService.getPostById(promotionRecord.getPostAfterId());
            return new PromotionRecordVO(promotionRecord, postBefore, postAfter);
        }).collect(Collectors.toList());
    }

    @Override
    public List<PromotionRecordVO> getPromotionRecordByUsername(String username) {
        UmsAdmin admin = adminService.getAdminByUsername(username);
        return getPromotionRecordByUserId(admin.getId());
    }

    @Override
    public Page<PromotionRecordVO> getPage(Long adminId, Integer pageSize, Integer pageNum) {
        Page<PromotionRecordVO> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<TblPromotionRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblPromotionRecord::getAdminId, adminId);
        List<TblPromotionRecord> promotionRecordList = list(wrapper);
        List<PromotionRecordVO> promotionRecordVOList = promotionRecordList.stream().map(promotionRecord -> {
            UmsPost postBefore = postService.getPostById(promotionRecord.getPostBeforeId());
            UmsPost postAfter = postService.getPostById(promotionRecord.getPostAfterId());
            return new PromotionRecordVO(promotionRecord, postBefore, postAfter);
        }).collect(Collectors.toList());
        page.setRecords(promotionRecordVOList);
        return page;
    }

    @Override
    public int addPromotionRecord(String username, TblPromotionRecordParam promotionRecordParam) {
        UmsAdmin user = adminService.getAdminByUsername(username);
        if (user == null) {
            return -1;
        }
        UmsPost postBefore = postService.getPostById(promotionRecordParam.getPostBeforeId());
        if (postBefore == null) {
            return -2;
        }
        UmsPost postAfter = postService.getPostById(promotionRecordParam.getPostAfterId());
        if (postAfter == null) {
            return -3;
        }
        LambdaQueryWrapper<TblPromotionRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblPromotionRecord::getAdminId, promotionRecordParam.getAdminId())
                .eq(TblPromotionRecord::getPostBeforeId, promotionRecordParam.getPostBeforeId())
                .eq(TblPromotionRecord::getPostAfterId, promotionRecordParam.getPostAfterId());
        if (getOne(wrapper) != null) {
            return -4;
        }
        TblPromotionRecord promotionRecord = new TblPromotionRecord();
        BeanUtils.copyProperties(promotionRecordParam, promotionRecord);
        promotionRecord.setPromotionTime(new Date());
        save(promotionRecord);
        // 晋升前岗位人数减一，晋升后岗位人数加一
        postBefore.setAdminNumber(postBefore.getAdminNumber() - 1);
        postService.updatePost(postBefore);
        postAfter.setAdminNumber(postAfter.getAdminNumber() + 1);
        postService.updatePost(postAfter);
        // 用户岗位关联表插入数据
        adminPostRelationService.addAdminPost(promotionRecordParam.getAdminId(), promotionRecordParam.getPostAfterId());
        return 1;
    }

    @Override
    public int deletePromotionRecordById(Long id) {
        TblPromotionRecord promotionRecord = getById(id);
        if (promotionRecord == null) {
            return -1;
        }
        removeById(id);
        return 1;
    }

    @Override
    public int deletePromotionRecordByUsername(String username, Long id) {
        UmsAdmin admin = adminService.getAdminByUsername(username);
        TblPromotionRecord promotionRecord = getById(id);
        if (promotionRecord == null) {
            return -1;
        }
        if (!promotionRecord.getAdminId().equals(admin.getId())) {
            return -2;
        }
        removeById(id);
        return 1;
    }

    @Override
    public int updatePromotionRecord(TblPromotionRecord promotionRecord) {
        TblPromotionRecord oldPromotionRecord = getById(promotionRecord.getId());
        if (oldPromotionRecord == null) {
            return -1;
        }
        UmsAdmin user = adminService.getAdminById(promotionRecord.getAdminId());
        if (user == null) {
            return -2;
        }
        UmsPost postBefore = postService.getPostById(promotionRecord.getPostBeforeId());
        if (postBefore == null) {
            return -3;
        }
        UmsPost postAfter = postService.getPostById(promotionRecord.getPostAfterId());
        if (postAfter == null) {
            return -4;
        }
        updateById(promotionRecord);
        return 1;
    }

}
