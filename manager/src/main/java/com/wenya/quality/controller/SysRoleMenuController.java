package com.wenya.quality.controller;

import com.wenya.quality.dto.system.AssginMenuDto;
import com.wenya.quality.dto.system.AssginRoleDto;
import com.wenya.quality.service.ISysRoleMenuService;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * sys角色菜单控制器
 * Description：
 *
 * @author wuqiulin
 */
@RestController
@RequestMapping("/system/sysRoleMenu")
public class SysRoleMenuController extends BaseController {

    @Resource
    private ISysRoleMenuService sysRoleMenuService;

    /**
     * 按角色id查找角色菜单
     *
     * @param id id
     * @return {@link AjaxResult }
     */
    @GetMapping("/findSysRoleMenuByRoleId/{id}")
    public AjaxResult findSysRoleMenuByRoleId(@PathVariable Long id) {
        return success(sysRoleMenuService.findSysRoleMenuByRoleId(id));
    }

    /**
     * 保存菜单分配
     *
     * @param assginMenuDto 角色菜单
     * @return {@link AjaxResult }
     */
    @PostMapping("/doAssign")
    public AjaxResult doAssign(@RequestBody AssginMenuDto assginMenuDto) {
        return success(sysRoleMenuService.doAssign(assginMenuDto));
    }
}
