package com.wenya.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wenya.quality.doamin.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 类别服务
 * Description：
 *
 * @author wuqiulin
 */
public interface ICategoryService extends IService<Category> {
    /**
     * 按父id查找
     *
     * @param parentId 父id
     * @return {@link String }
     */
    List<Category> findByParentId(Long parentId);

    /**
     * 导出数据
     *
     * @param response 响应
     */
    void  exportData(HttpServletResponse response);

    /**
     * 导入数据
     *
     * @param multipartFile 多部分文件
     */
    void importData(MultipartFile multipartFile);
}
