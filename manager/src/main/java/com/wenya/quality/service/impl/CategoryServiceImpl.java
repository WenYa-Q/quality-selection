package com.wenya.quality.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.quality.doamin.product.Category;
import com.wenya.quality.listener.ExcelListener;
import com.wenya.quality.mapper.CategoryMapper;
import com.wenya.quality.service.ICategoryService;
import com.wenya.quality.vo.product.CategoryExcelVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 类别服务实施
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ExcelListener<T> excelListener;

    /**
     * 按父id查找
     *
     * @param parentId 父id
     * @return {@link String }
     */
    @Override
    public List<Category> findByParentId(Long parentId) {
        //查找父级为parentId的类别
        List<Category> categoryList = categoryMapper
                .selectList(new LambdaQueryWrapper<Category>()
                        .eq(Category::getParentId, parentId).eq(Category::getIsDeleted, 0));

        if (!CollectionUtils.isEmpty(categoryList)) {
            //判断是否存在子节点
            categoryList.forEach(item -> {
                List<Category> childrenList = categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                        .eq(Category::getId, item.getId()).eq(Category::getIsDeleted, 0));
                item.setHasChildren(! CollectionUtils.isEmpty(childrenList));
            });
        }

        return categoryList;
    }

    /**
     * 导出数据
     *
     * @param response 响应
     */
    @Override
    public void exportData(HttpServletResponse response) {
        //设置响应结果
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        try {
            String fileName = URLEncoder.encode("分类数据", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            //查询数据
            List<Category> categoryList = categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                    .eq(Category::getIsDeleted, 0));

            List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>(categoryList.size());
            //将Category对象转换成CategoryExcelVo对象
            categoryList.forEach(item -> {
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
                BeanUtils.copyProperties(item, categoryExcelVo, CategoryExcelVo.class);

                categoryExcelVoList.add(categoryExcelVo);
            });

            //写出
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class)
                    .sheet("分类数据").doWrite(categoryExcelVoList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 导入数据
     *
     * @param multipartFile 多部分文件
     */
    @Override
    public void importData(MultipartFile multipartFile) {
        try {
            //读取xlsx文件数据
            EasyExcel.read(multipartFile.getInputStream(), Category.class, excelListener).sheet().doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
