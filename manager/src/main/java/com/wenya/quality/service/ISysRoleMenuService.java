package com.wenya.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wenya.quality.doamin.system.SysRoleMenu;
import com.wenya.quality.dto.system.AssginMenuDto;

import java.util.Map;

/**
 * sys角色菜单服务
 * Description：
 *
 * @author wuqiulin
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 按角色id查找角色菜单
     *
     * @param id id
     * @return {@link Map }<{@link String }, {@link Object }>
     */
    Map<String, Object> findSysRoleMenuByRoleId(Long id);

    /**
     * 保存菜单分配
     *
     * @param assginMenuDto 分配菜单
     * @return int
     */
    int doAssign(AssginMenuDto assginMenuDto);
}
