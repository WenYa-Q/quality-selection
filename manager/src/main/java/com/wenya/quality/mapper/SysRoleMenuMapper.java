package com.wenya.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.system.SysRoleMenu;
import com.wenya.quality.dto.system.AssginMenuDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * sys角色菜单映射器
 * Description：
 *
 * @author wuqiulin
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    /**
     * 保存菜单分配
     *
     * @param assginMenuDto 分配菜单
     * @return int
     */
    int doAssign(AssginMenuDto assginMenuDto);
}
