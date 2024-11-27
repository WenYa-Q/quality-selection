package com.wenya.quality.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wenya.quality.doamin.system.SysRole;
import com.wenya.quality.dto.system.SysRoleDto;
import com.wenya.quality.service.ISysRoleService;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色权限管理
 *
 * @author wenya
 */
@RestController
@RequestMapping("/system/sysRole")
@Tag(name = "角色管理")
public class SysRoleController extends BaseController {

    @Resource
    private ISysRoleService sysRoleService;

    @GetMapping("/findByPage/{pageNum}/{pageSize}")
    @Operation(summary = "分页查询")
    public PageInfo<SysRole> findByPage(@RequestParam String roleName, @PathVariable(value = "pageNum") Integer pageNum, @PathVariable(value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        //查询角色
        List<SysRole> sysRoleList = sysRoleService.findByPage(roleName);

        return new PageInfo<>(sysRoleList);
    }

    /**
     * 保存角色
     * @return {@link AjaxResult }
     */
    @PostMapping("/saveSysRole")
    @Operation(summary = "添加角色")
    public AjaxResult saveSysRole(@RequestBody SysRoleDto sysRoleDto) {
        return toAjax(sysRoleService.saveSysRole(sysRoleDto));
    }

    /**
     * 更新角色
     *
     * @param sysRole sys角色
     * @return {@link AjaxResult }
     */
    @PutMapping("/updateSysRole")
    @Operation(summary = "修改用户")
    public AjaxResult updateSysRole(@RequestBody SysRole sysRole) {
        return toAjax(sysRoleService.updateSysRole(sysRole));
    }

    /**
     * 根据ID删除角色
     *
     * @param id id
     * @return {@link AjaxResult }
     */
    @DeleteMapping("/deleteById/{id}")
    public AjaxResult deleteById(@PathVariable(value = "id") Long id) {
        return toAjax(sysRoleService.deleteById(id));
    }

    @GetMapping("/findAllRoles")
    @Operation(summary = "查询所有角色")
    public AjaxResult findAllRoles() {
        return AjaxResult.success(sysRoleService.list());
    }
}
