package com.ecareer.modules.tbl.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecareer.common.api.CommonPage;
import com.ecareer.common.api.CommonResult;
import com.ecareer.modules.tbl.dto.TblMaterialParam;
import com.ecareer.modules.tbl.model.TblMaterial;
import com.ecareer.modules.tbl.model.TblMaterialCourse;
import com.ecareer.modules.tbl.model.TblMaterialSubject;
import com.ecareer.modules.tbl.model.TblMaterialType;
import com.ecareer.modules.tbl.service.TblMaterialCourseService;
import com.ecareer.modules.tbl.service.TblMaterialService;
import com.ecareer.modules.tbl.service.TblMaterialSubjectService;
import com.ecareer.modules.tbl.service.TblMaterialTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 培训资料表 前端控制器
 * </p>
 *
 * @author Qing2514
 * @since 2023-07-20
 */
@RestController
@Api(tags = "TblMaterialController")
@Tag(name = "TblMaterialController", description = "培训资料管理")
@RequestMapping("/material")
public class TblMaterialController {

    @Autowired
    private TblMaterialService materialService;

    @Autowired
    private TblMaterialTypeService materialTypeService;

    @Autowired
    private TblMaterialCourseService materialCourseService;

    @Autowired
    private TblMaterialSubjectService materialSubjectService;

    @ApiOperation(value = "根据培训资料ID获取培训资料")
    @GetMapping(value = "/user/{id}")
    public CommonResult<TblMaterial> getMaterialsById(@PathVariable Long id) {
        TblMaterial material = materialService.getMaterialsById(id);
        return CommonResult.success(material);
    }

    @ApiOperation(value = "根据培训资料类型ID、课程ID和学科ID分页获取培训资料列表")
    @GetMapping(value = "/user/getPage")
    public CommonResult<CommonPage<TblMaterial>> getPage(@RequestParam(value = "typeId", required = false) Long typeId,
                                                         @RequestParam(value = "courseId", required = false) Long courseId,
                                                         @RequestParam(value = "subjectId", required = false) Long subjectId,
                                                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<TblMaterial> materialList = materialService.getPage(typeId, courseId, subjectId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(materialList));
    }

    @ApiOperation(value = "分页获取培训资料类型")
    @GetMapping(value = "/user/type/getPage")
    public CommonResult<CommonPage<TblMaterialType>> getTypePage(
            @RequestParam(value = "typeId", required = false) Long typeId,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<TblMaterialType> typeList = materialService.getTypePage(typeId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(typeList));
    }

    @ApiOperation(value = "分页获取培训资料所属课程")
    @GetMapping(value = "/user/course/getPage")
    public CommonResult<CommonPage<TblMaterialCourse>> getCoursePage(
            @RequestParam(value = "courseId", required = false) Long courseId,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<TblMaterialCourse> courseList = materialService.getCoursePage(courseId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(courseList));
    }

    @ApiOperation(value = "分页获取培训资料所属学科")
    @GetMapping(value = "/user/subject/getPage")
    public CommonResult<CommonPage<TblMaterialSubject>> getSubjectPage(
            @RequestParam(value = "subjectId", required = false) Long subjectId,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<TblMaterialSubject> subjectList = materialService.getSubjectPage(subjectId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(subjectList));
    }

    @ApiOperation(value = "上传培训资料")
    @PostMapping(value = "/{adminId}/{typeId}/{courseId}/{subjectId}")
    public CommonResult<Object> upload(@RequestParam("file") MultipartFile file,
                                       @PathVariable("adminId") Long adminId,
                                       @PathVariable("typeId") Long typeId,
                                       @PathVariable("courseId") Long courseId,
                                       @PathVariable("subjectId") Long subjectId) {
        TblMaterialParam materialParam = new TblMaterialParam(adminId, typeId, courseId, subjectId);
        int status = materialService.addMaterial(materialParam, file);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("类型不存在");
        } else if (status == -2) {
            return CommonResult.failed("课程不存在");
        } else if (status == -3) {
            return CommonResult.failed("学科不存在");
        } else if (status == -4) {
            return CommonResult.failed("上传用户不存在");
        } else if (status == -5) {
            return CommonResult.failed("资料已存在");
        } else if (status == -6) {
            return CommonResult.failed("minio服务器异常");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "新增培训资料类型")
    @PostMapping(value = "/type/{name}")
    public CommonResult<Object> addType(@PathVariable String name) {
        int status = materialTypeService.addType(name);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("培训资料类型已存在");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "新增培训资料所属课程")
    @PostMapping(value = "/course/{name}/{subjectId}")
    public CommonResult<Object> addCourse(@PathVariable String name, @PathVariable Long subjectId) {
        int status = materialCourseService.addCourse(name, subjectId);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("资料所属课程已存在");
        } else if (status == -2) {
            return CommonResult.failed("资料所属科目不存在");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "新增培训资料所属学科")
    @PostMapping(value = "/subject/{name}")
    public CommonResult<Object> addSubject(@PathVariable String name) {
        int status = materialSubjectService.addSubject(name);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("培训资料所属学科已存在");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "根据培训资料ID删除培训资料")
    @DeleteMapping(value = "/{id}")
    public CommonResult<Object> delete(@PathVariable Long id) {
        int status = materialService.deleteMaterialById(id);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("培训资料不存在");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改培训资料")
    @PutMapping(value = "")
    public CommonResult<Object> update(@RequestBody TblMaterial material) {
        int status = materialService.updateMaterial(material);
        if (status > 0) {
            return CommonResult.success();
        } else if (status == -1) {
            return CommonResult.failed("培训资料不存在");
        } else if (status == -2) {
            return CommonResult.failed("培训资料类型不存在");
        } else if (status == -3) {
            return CommonResult.failed("培训资料所属课程不存在");
        } else if (status == -4) {
            return CommonResult.failed("培训资料所属学科不存在");
        } else {
            return CommonResult.failed();
        }
    }

}

