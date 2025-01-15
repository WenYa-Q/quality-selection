package com.wenya.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.mapper.BrandMapper;
import com.wenya.quality.doamin.product.Brand;
import com.wenya.service.IBrandService;
import org.springframework.stereotype.Service;

/**
 * 品牌服务实施
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {
}
