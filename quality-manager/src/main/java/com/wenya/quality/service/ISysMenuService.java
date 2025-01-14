package com.wenya.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wenya.quality.doamin.system.SysMenu;
import com.wenya.quality.vo.system.SysMenuVo;

import java.util.List;

/**
 * sys菜单服务
 * Description：
 *
 * @author wuqiulin
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 获取树状菜单列表
     *
     * @return {@link List }<{@link SysMenu }>
     */
    List<SysMenu> findNodes();

    /**
     * 查找用户菜单列表
     *
     * @return {@link String }
     */
    List<SysMenuVo> findUserMenuList();
}
