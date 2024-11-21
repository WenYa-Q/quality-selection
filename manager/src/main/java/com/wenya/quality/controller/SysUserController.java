package com.wenya.quality.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wenya.quality.doamin.system.SysUser;
import com.wenya.quality.dto.system.SysUserDto;
import com.wenya.quality.service.ISysUserService;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * sys用户控制器
 *
 * @author wenya
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("/system/sysUser")
public class SysUserController extends BaseController {

    @Resource
    private ISysUserService sysUserService;

    /**
     * 分页查询用户
     *
     * @param sysUserDto 查询参数
     * @return {@link PageInfo }<{@link SysUser }>
     */
    @GetMapping("/findByPage/{pageNum}/{pageSize}")
    @Operation(summary = "分页查询用户")
    public PageInfo<SysUser> findByPage(SysUserDto sysUserDto,
                                        @PathVariable Integer pageNum,
                                        @PathVariable Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> sysUserList = sysUserService.selectSysUser(sysUserDto);
        return new PageInfo<>(sysUserList);
    }

    /**
     * 保存用户
     *
     * @param sysUser sys用户
     * @return {@link AjaxResult }
     */
    @PostMapping("/saveSysUser")
    @Operation(summary = "新增用户")
    public AjaxResult saveSysUser(@RequestBody SysUser sysUser) {
        return toAjax(sysUserService.save(sysUser));
    }

    /**
     * 更新用户
     *
     * @param sysUser sys用户
     * @return {@link AjaxResult }
     */
    @PutMapping("/updateSysUser")
    @Operation(summary = "更新用户")
    public AjaxResult updateSysUser(@RequestBody SysUser sysUser) {
        return toAjax(sysUserService.updateById(sysUser));
    }

    /**
     * 按id删除
     *
     * @param id id
     * @return {@link AjaxResult }
     */
    @DeleteMapping("/deleteById/{id}")
    @Operation(summary = "删除用户")
    public AjaxResult deleteById(@PathVariable Long id) {
        return toAjax(sysUserService.removeById(id));
    }
}
