package com.wenya.quality.common;

import com.wenya.quality.doamin.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单助手
 * Description：
 *
 * @author wuqiulin
 */
public class MenuHelper {

    /**
     * 构建树状列表
     *
     * @param sysMenuList sys菜单列表
     * @return {@link List }<{@link SysMenu }>
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        List<SysMenu> menuList = new ArrayList<>();

        for (SysMenu sysMenu : sysMenuList) {
            if (sysMenu.getParentId() == 0) {
                menuList.add(findChildren(sysMenu, sysMenuList));
            }
        }
        return menuList;
    }

    /**
     * 查找子节点
     *
     * @param sysMenu     sys菜单
     * @param sysMenuList sys菜单列表
     * @return {@link SysMenu }
     */
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        sysMenu.setChildren(new ArrayList<>());

        for (SysMenu menu : sysMenuList) {
            if (sysMenu.getId().equals(menu.getParentId())){
                sysMenu.getChildren().add(findChildren(menu, sysMenuList));
            }
        }

        return sysMenu;
    }
}
