package com.wenya.quality.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileNameUtil;
import com.wenya.quality.properties.MinioProperties;
import com.wenya.quality.service.IFileUploadService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

/**
 * 文件上传服务impl
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class FileUploadServiceImpl implements IFileUploadService {

    @Resource
    private MinioProperties minioProperties;
    /**
     * 文件上传
     *
     * @param multipartFile 文件
     * @return {@link String }
     */
    @Override
    public String fileUpload(MultipartFile multipartFile) {
        //创建minio客户端对象
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();

        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            }

            //设置存储对象文件夹名称
            String dateDir = DateUtil.format(new Date(), "yyyyMMdd");
            //设置存储对象文件名称
            String replace = UUID.randomUUID().toString().replace("-", "");
            String originalFilename = multipartFile.getOriginalFilename();
            String suffix = FileNameUtil.getSuffix(originalFilename);
            String fileName = dateDir + "/" + replace + "." + suffix;

            //上传文件
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(minioProperties.getBucketName())
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                    .object(fileName)
                    .build();

            minioClient.putObject(putObjectArgs);

            return minioProperties.getEndpoint() + "/" + minioProperties.getBucketName() + "/" + fileName;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
