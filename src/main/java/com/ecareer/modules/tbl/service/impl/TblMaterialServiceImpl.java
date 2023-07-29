package com.ecareer.modules.tbl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecareer.modules.tbl.dto.TblMaterialParam;
import com.ecareer.modules.tbl.mapper.TblMaterialMapper;
import com.ecareer.modules.tbl.model.TblMaterial;
import com.ecareer.modules.tbl.model.TblMaterialCourse;
import com.ecareer.modules.tbl.model.TblMaterialSubject;
import com.ecareer.modules.tbl.model.TblMaterialType;
import com.ecareer.modules.tbl.service.TblMaterialCourseService;
import com.ecareer.modules.tbl.service.TblMaterialService;
import com.ecareer.modules.tbl.service.TblMaterialSubjectService;
import com.ecareer.modules.tbl.service.TblMaterialTypeService;
import com.ecareer.modules.ums.model.UmsAdmin;
import com.ecareer.modules.ums.service.UmsAdminService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 培训资料表 服务实现类
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@Service
public class TblMaterialServiceImpl extends ServiceImpl<TblMaterialMapper, TblMaterial> implements TblMaterialService {

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.endpoint}")
    private String endpoint;

    @Autowired
    private TblMaterialTypeService materialTypeService;

    @Autowired
    private TblMaterialCourseService materialCourseService;

    @Autowired
    private TblMaterialSubjectService materialSubjectService;

    @Autowired
    private UmsAdminService adminService;

    @Autowired
    private MinioClient minioClient;

    @Override
    public TblMaterial getMaterialsById(Long id) {
        LambdaQueryWrapper<TblMaterial> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblMaterial::getId, id).eq(TblMaterial::getDeleted, 0);
        return getOne(wrapper);
    }

    @Override
    public Page<TblMaterial> getPage(Long typeId, Long courseId, Long subjectId, Integer pageSize, Integer pageNum) {
        Page<TblMaterial> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<TblMaterial> materialWrapper = new LambdaQueryWrapper<>();
        materialWrapper.eq(TblMaterial::getDeleted, 0);
        if (typeId != null) {
            materialWrapper.eq(TblMaterial::getTypeId, typeId);
        }
        if (courseId != null) {
            materialWrapper.eq(TblMaterial::getCourseId, courseId);
        }
        if (subjectId != null) {
            materialWrapper.eq(TblMaterial::getSubjectId, subjectId);
        }
        return page(page, materialWrapper);
    }

    @Override
    public int addMaterial(TblMaterialParam materialParam, MultipartFile file) {
        TblMaterialType materialType = materialTypeService.getById(materialParam.getTypeId());
        if (materialType == null) {
            return -1;
        }
        TblMaterialCourse materialCourse = materialCourseService.getById(materialParam.getCourseId());
        if (materialCourse == null) {
            return -2;
        }
        TblMaterialSubject materialSubject = materialSubjectService.getById(materialParam.getSubjectId());
        if (materialSubject == null) {
            return -3;
        }
        UmsAdmin admin = adminService.getAdminById(materialParam.getAdminId());
        if (admin == null) {
            return -4;
        }
        String objectName = file.getOriginalFilename();
        LambdaQueryWrapper<TblMaterial> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblMaterial::getName, objectName).eq(TblMaterial::getDeleted, 0);
        if (getOne(wrapper) != null) {
            return -5;
        }
        TblMaterial material = new TblMaterial();
        BeanUtils.copyProperties(materialParam, material);
        try {
            material.setSize(file.getSize());
            material.setName(objectName);
            material.setUrl(String.join("/", endpoint, bucketName, objectName));
            boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!exists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1).contentType(file.getContentType()).build());
        } catch (Exception e) {
            e.printStackTrace();
            return -6;
        }
        material.setUploadTime(new Date());
        material.setDeleted(0);
        save(material);
        return 1;
    }

    @Override
    public int deleteMaterialById(Long id) {
        LambdaQueryWrapper<TblMaterial> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblMaterial::getId, id);
        TblMaterial material = getOne(wrapper);
        if (material == null || material.getDeleted() == 1) {
            return -1;
        }
        material.setDeleted(1);
        save(material);
        return 1;
    }

    @Override
    public int updateMaterial(TblMaterial material) {
        TblMaterial oldMaterial = getById(material.getId());
        if (oldMaterial == null) {
            return -1;
        }
        TblMaterialType materialType = materialTypeService.getById(oldMaterial.getTypeId());
        if (materialType == null) {
            return -2;
        }
        TblMaterialCourse materialCourse = materialCourseService.getById(oldMaterial.getCourseId());
        if (materialCourse == null) {
            return -3;
        }
        TblMaterialSubject materialSubject = materialSubjectService.getById(oldMaterial.getSubjectId());
        if (materialSubject == null) {
            return -4;
        }
        updateById(material);
        return 1;
    }

}
