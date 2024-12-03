package com.wenya.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * sys菜单映射器
 * Description：
 *
 * @author wuqiulin
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 查找当前用户可见菜单项
     *
     * @param id id
     * @return {@link List }<{@link SysMenu }>
     */
    List<SysMenu> selectListByUserId(Long id);
}
