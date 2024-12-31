package com.wenya.quality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.quality.doamin.system.SysMenu;
import com.wenya.quality.doamin.system.SysRoleMenu;
import com.wenya.quality.dto.system.AssginMenuDto;
import com.wenya.quality.mapper.SysRoleMenuMapper;
import com.wenya.quality.service.ISysMenuService;
import com.wenya.quality.service.ISysRoleMenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * sys角色菜单服务
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Resource
    private ISysMenuService sysMenuService;


    /**
     * 按角色id查找角色菜单
     *
     * @param id id
     * @return {@link Map }<{@link String }, {@link Object }>
     */
    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long id) {
        //获取所有菜单
        List<SysMenu> menuList = sysMenuService.findNodes();

        //获取当前角色关联的菜单
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getId, id)
                .eq(SysRoleMenu::getIsDeleted, 0)
                .eq(SysRoleMenu::getIsHalf, 0)
        );
        List<Long> roleMenuIds = sysRoleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());

        Map<String, Object> data = new HashMap<>();
        data.put("sysMenuList", menuList);
        data.put("roleMenuIds", roleMenuIds);

        return data;
    }

    /**
     * 保存菜单分配
     *
     * @param assginMenuDto 分配菜单
     * @return int
     */
    @Transactional
    @Override
    public int doAssign(AssginMenuDto assginMenuDto) {
        //删除当前角色已有的数据
        sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, assginMenuDto.getRoleId()));

        List<Map<String, Number>> menuIdList = assginMenuDto.getMenuIdList();
        if (menuIdList == null || menuIdList.isEmpty()) {
            return 0;
        }
        return sysRoleMenuMapper.doAssign(assginMenuDto);
    }
}
