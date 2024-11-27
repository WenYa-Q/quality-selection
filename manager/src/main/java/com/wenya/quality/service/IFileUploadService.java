package com.wenya.quality.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传服务
 * Description：
 *
 * @author wuqiulin
 */
public interface IFileUploadService {

    /**
     * 文件上传
     *
     * @param multipartFile 文件
     * @return {@link String }
     */
    String fileUpload(MultipartFile multipartFile);
}
