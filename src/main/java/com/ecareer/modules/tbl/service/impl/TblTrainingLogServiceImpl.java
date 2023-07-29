package com.ecareer.modules.tbl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecareer.modules.tbl.dto.TblTrainingLogParam;
import com.ecareer.modules.tbl.mapper.TblTrainingLogMapper;
import com.ecareer.modules.tbl.model.TblTrainingLog;
import com.ecareer.modules.tbl.model.TblMaterial;
import com.ecareer.modules.tbl.service.TblMaterialService;
import com.ecareer.modules.tbl.service.TblTrainingLogService;
import com.ecareer.modules.tbl.vo.TrainingLogVO;
import com.ecareer.modules.ums.model.UmsAdmin;
import com.ecareer.modules.ums.service.UmsAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 培训记录表 服务实现类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@Service
public class TblTrainingLogServiceImpl extends ServiceImpl<TblTrainingLogMapper, TblTrainingLog> implements TblTrainingLogService {

    @Autowired
    private UmsAdminService adminService;

    @Autowired
    private TblMaterialService trainingMaterialsService;

    @Override
    public List<TrainingLogVO> getTrainingLogByUserId(Long id) {
        LambdaQueryWrapper<TblTrainingLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblTrainingLog::getAdminId, id);
        List<TblTrainingLog> trainingLogList = list(wrapper);
        return trainingLogList.stream().map(trainingLog -> {
            TblMaterial trainingMaterials = trainingMaterialsService.getMaterialsById(trainingLog.getMaterialId());
            return new TrainingLogVO(trainingLog, trainingMaterials);
        }).collect(Collectors.toList());
    }

    @Override
    public Page<TrainingLogVO> getPage(Long adminId, Integer pageSize, Integer pageNum) {
        Page<TrainingLogVO> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<TblTrainingLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblTrainingLog::getAdminId, adminId);
        List<TblTrainingLog> trainingLogList = list(wrapper);
        List<TrainingLogVO> trainingLogVOList = trainingLogList.stream().map(trainingLog -> {
            TblMaterial trainingMaterials = trainingMaterialsService.getMaterialsById(trainingLog.getMaterialId());
            return new TrainingLogVO(trainingLog, trainingMaterials);
        }).collect(Collectors.toList());
        page.setRecords(trainingLogVOList);
        return page;
    }

    @Override
    public int addTrainingLog(TblTrainingLogParam trainingLogParam) {
        UmsAdmin user = adminService.getAdminById(trainingLogParam.getAdminId());
        if (user == null) {
            return -1;
        }
        TblMaterial materials = trainingMaterialsService.getMaterialsById(trainingLogParam.getMaterialId());
        if (materials == null) {
            return -2;
        }
        LambdaQueryWrapper<TblTrainingLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblTrainingLog::getAdminId, trainingLogParam.getAdminId()).eq(TblTrainingLog::getMaterialId,
                trainingLogParam.getMaterialId());
        TblTrainingLog trainingLog = getOne(wrapper);
        if (trainingLog != null) {
            trainingLog.setLatestTime(new Date());
            return 1;
        }
        trainingLog = new TblTrainingLog();
        BeanUtils.copyProperties(trainingLogParam, trainingLog);
        trainingLog.setBeginTime(new Date());
        trainingLog.setLatestTime(new Date());
        trainingLog.setRate(0);
        save(trainingLog);
        return 2;
    }

    @Override
    public int deleteTrainingLog(Long adminId, Long materialId) {
        LambdaQueryWrapper<TblTrainingLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblTrainingLog::getAdminId, adminId).eq(TblTrainingLog::getMaterialId, materialId);
        if (list(wrapper) == null) {
            return -1;
        }
        remove(wrapper);
        return 1;
    }

    @Override
    public int updateTrainingLog(TblTrainingLog trainingLog) {
        UmsAdmin user = adminService.getAdminById(trainingLog.getAdminId());
        if (user == null) {
            return -1;
        }
        TblMaterial materials = trainingMaterialsService.getMaterialsById(trainingLog.getMaterialId());
        if (materials == null) {
            return -2;
        }
        LambdaQueryWrapper<TblTrainingLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblTrainingLog::getAdminId, trainingLog.getAdminId());
        List<TblTrainingLog> trainingLogList = list(wrapper);
        if (trainingLogList == null) {
            return -3;
        }
        updateById(trainingLog);
        return 1;
    }

}
