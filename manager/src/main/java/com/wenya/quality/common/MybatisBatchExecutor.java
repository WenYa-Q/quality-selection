package com.wenya.quality.common;

import jakarta.annotation.Resource;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * 启动mybatis的Batch模式的批量新增、更新
 * Description：
 *
 * @author wuqiulin
 */
@Component
public class MybatisBatchExecutor {

    /**
     * 拼写成SQL的最大数据量
     * 比如： 如果insert，把batchCount条数的数据拼写成一个大SQL
     * 如果update，把batchCount条数的数据拼写成case when方式的大SQL
     */
    private static final int BATCH_COUNT_TOSQL = 100;

    /**
     * 每多少次时开始commit
     */
    private static final int BATCH_COUNT_TO_SUBMIT = 100;

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;


    /**
     * 批量更新、新增
     *
     * @param dbList      数据集
     * @param mapperClass mapper
     * @param func 插入方法
     */
    public <T, M> void insertOrUpdateBatch(List<T> dbList, Class<M> mapperClass, BiConsumer<M, List<T>> func) {
        int batchLastIndex = BATCH_COUNT_TOSQL;
        int batchLastIndexToSubmit = 0;
        int total = dbList.size();

        SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        M modelMapper = sqlSession.getMapper(mapperClass);
        try {
            if (total > BATCH_COUNT_TOSQL) {
                for (int index = 0; index < total; ) {
                    if (batchLastIndex > total) {
                        List<T> list = dbList.subList(index, total);

                        func.accept(modelMapper, list);

                        sqlSession.flushStatements();
                        sqlSession.commit();
                        break;
                    } else {
                        List<T> list = dbList.subList(index, batchLastIndex);
                        func.accept(modelMapper, list);

                        if (batchLastIndexToSubmit++ >= BATCH_COUNT_TO_SUBMIT) {
                            //如果可以批量提交，则提交
                            sqlSession.flushStatements();
                            sqlSession.commit();
                            batchLastIndexToSubmit = 0;
                        }
                        // 设置下一批下标
                        index = batchLastIndex;
                        batchLastIndex = index + (BATCH_COUNT_TOSQL - 1);
                    }
                }
            } else {
                func.accept(modelMapper, dbList);
                sqlSession.commit();
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            SqlSessionUtils.closeSqlSession(sqlSession, sqlSessionFactory);
        }
    }
}
