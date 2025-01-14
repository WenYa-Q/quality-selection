package com.wenya.service;

import com.wenya.quality.vo.h5.IndexVo;

import java.util.List;

/**
 * 首页服务
 * Description：
 *
 * @author wuqiulin
 */
public interface IndexService {
    /**
     * 查找所有数据
     *
     * @return {@link String }
     */
    List<IndexVo> findAllData();
}
