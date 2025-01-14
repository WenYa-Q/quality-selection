package com.wenya.quality.controller;

import com.wenya.quality.service.IFileUploadService;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传控制器
 * Description：
 *
 * @author wuqiulin
 */
@RestController
@RequestMapping("/system")
@Tag(name = "文件上传接口")
public class FileUploadController extends BaseController {

    @Resource
    private IFileUploadService fileUploadService;

    /**
     * 文件上传
     *
     * @return {@link String }
     */
    @PostMapping("/fileUpload")
    @Operation(summary = "文件上传")
    public AjaxResult fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        return AjaxResult.success("文件上传成功", fileUploadService.fileUpload(multipartFile));
    }
}
