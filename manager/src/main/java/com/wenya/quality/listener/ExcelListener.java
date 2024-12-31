package com.wenya.quality.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.wenya.quality.common.MybatisBatchExecutor;
import com.wenya.quality.doamin.product.Category;
import com.wenya.quality.mapper.CategoryMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * excel侦听器
 * Description：
 *
 * @author wuqiulin
 */

@Slf4j
@Component
public class ExcelListener<T> extends AnalysisEventListener<T> {

    private static final int BATCH_COUNT = 100;

    @Resource
    private MybatisBatchExecutor mybatisBatchExecutor;

    private List<Category> categoryList = new ArrayList<>();
    /**
     * 调用
     *
     * @param t               文件
     * @param analysisContext 分析上下文
     */
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(t));
        Category category = (Category) t;

        categoryList.add(category);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (categoryList.size() >= BATCH_COUNT) {
            mybatisBatchExecutor.insertOrUpdateBatch(categoryList, CategoryMapper.class, CategoryMapper::insertCategoryByList);
            // 存储完成清理 list
            categoryList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * excel解析完毕之后执行
     *
     * @param analysisContext 分析上下文
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        mybatisBatchExecutor.insertOrUpdateBatch(categoryList, CategoryMapper.class, CategoryMapper::insertCategoryByList);

        System.out.println("excel解析完毕");
    }
}
