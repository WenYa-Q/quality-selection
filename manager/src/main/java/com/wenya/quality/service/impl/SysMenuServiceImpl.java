package com.wenya.quality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.quality.AuthContextUtil;
import com.wenya.quality.common.MenuHelper;
import com.wenya.quality.doamin.system.SysMenu;
import com.wenya.quality.doamin.system.SysUser;
import com.wenya.quality.mapper.SysMenuMapper;
import com.wenya.quality.service.ISysMenuService;
import com.wenya.quality.vo.system.SysMenuVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * 系统菜单服务
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    /**
     * 获取树状菜单列表
     *
     * @return {@link List }<{@link SysMenu }>
     */
    @Override
    public List<SysMenu> findNodes() {
        //获取所有菜单列表
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", "0");
        queryWrapper.orderByAsc("sort_value");
        List<SysMenu> menuList = sysMenuMapper.selectList(queryWrapper);

        //构建树状列表
        return MenuHelper.buildTree(menuList);
    }

    /**
     * 查找用户菜单列表
     *
     * @return {@link List }<{@link SysMenuVo }>
     */
    @Override
    public List<SysMenuVo> findUserMenuList() {
        //获取当前登录用户
        SysUser sysUser = AuthContextUtil.getAuthContext();
        Long id = sysUser.getId();

        //查找当前用户可见菜单项
        List<SysMenu> sysMenuList = sysMenuMapper.selectListByUserId(id);
        List<SysMenu> menuList = MenuHelper.buildTree(sysMenuList);

        return buildMenus(menuList);
    }

    /**
     * 生成菜单
     *
     * @param menuList 菜单列表
     * @return {@link List }<{@link SysMenuVo }>
     */
    private List<SysMenuVo> buildMenus(List<SysMenu> menuList) {
        //将List<SysMenu>转换成List<SysMenuVo>
        List<SysMenuVo> menuVoList = new LinkedList<>();
        for (SysMenu menu : menuList) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(menu.getTitle());
            sysMenuVo.setName(menu.getComponent());

            List<SysMenu> children = menu.getChildren();
            if (children != null && !children.isEmpty()){
                sysMenuVo.setChildren(buildMenus(children));
            }

            menuVoList.add(sysMenuVo);
        }

        return menuVoList;
    }
}
