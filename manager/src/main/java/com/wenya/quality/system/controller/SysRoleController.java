package com.wenya.quality.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wenya.quality.doamin.system.SysRole;
import com.wenya.quality.dto.system.SysRoleDto;
import com.wenya.quality.system.service.ISysRoleService;
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
@RequestMapping("/sys/role")
@Tag(name = "角色管理")
public class SysRoleController {

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
}
