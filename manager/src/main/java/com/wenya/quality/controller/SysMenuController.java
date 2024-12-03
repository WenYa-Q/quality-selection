package com.wenya.quality.controller;

import com.wenya.quality.doamin.system.SysMenu;
import com.wenya.quality.service.ISysMenuService;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * sys菜单控制器
 * Description：
 *
 * @author wuqiulin
 */
@RestController
@RequestMapping("/system/sysMenu")
@Tag(name = "菜单接口")
public class SysMenuController extends BaseController {
    @Resource
    private ISysMenuService sysMenuService;

    /**
     * 获取树状菜单列表
     *
     * @return {@link AjaxResult }
     */
    @GetMapping("/findNodes")
    public AjaxResult findNodes() {
        List<SysMenu> menuList = sysMenuService.findNodes();
        return success(menuList);
    }

    /**
     * 保存菜单
     *
     * @param sysMenu sys菜单
     * @return {@link AjaxResult }
     */
    @PostMapping("/save")
    public AjaxResult save(@RequestBody SysMenu sysMenu) {
        return success(sysMenuService.save(sysMenu));
    }

    /**
     * 按id更新
     *
     * @param sysMenu sys菜单
     * @return {@link AjaxResult }
     */
    @PutMapping("/updateById")
    public AjaxResult updateById(@RequestBody SysMenu sysMenu) {
        return success(sysMenuService.updateById(sysMenu));
    }

    /**
     * 按id删除
     *
     * @param id id
     * @return {@link AjaxResult }
     */
    @DeleteMapping("/removeById/{id}")
    public AjaxResult removeById(@PathVariable Long id) {
        return success(sysMenuService.removeById(id));
    }
}
