package com.wenya.quality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.quality.common.MenuHelper;
import com.wenya.quality.doamin.system.SysMenu;
import com.wenya.quality.mapper.SysMenuMapper;
import com.wenya.quality.service.ISysMenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
}
