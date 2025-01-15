package com.wenya.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wenya.mapper.CategoryMapper;
import com.wenya.mapper.ProductSkuMapper;
import com.wenya.quality.doamin.product.Category;
import com.wenya.quality.doamin.product.ProductSku;
import com.wenya.quality.vo.h5.IndexVo;
import com.wenya.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 首页服务实施
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查找所有数据
     *
     * @return {@link List }<{@link IndexVo }>
     */
    @Override
    public List<IndexVo> findAllData() {
        List<Category> categoryList = categoryMapper.selectList(new LambdaQueryWrapper<Category>().eq(Category::getParentId, 0)
                .eq(Category::getStatus, 1)
                .eq(Category::getIsDeleted, 0)
                .orderByAsc(Category::getOrderNum));

        List<ProductSku> productSkuList = productSkuMapper.findProductSkuBySale();

        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkuList);

        return Collections.singletonList(indexVo);
    }
}
