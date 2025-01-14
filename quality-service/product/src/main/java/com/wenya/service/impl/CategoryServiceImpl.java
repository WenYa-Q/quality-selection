package com.wenya.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.mapper.CategoryMapper;
import com.wenya.quality.doamin.product.Category;
import com.wenya.service.ICategoryService;
import org.springframework.stereotype.Service;

/**
 * 类别服务实施
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
}
