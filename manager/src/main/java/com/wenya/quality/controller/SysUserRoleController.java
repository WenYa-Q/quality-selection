package com.wenya.quality.controller;

import com.wenya.quality.dto.system.AssginRoleDto;
import com.wenya.quality.service.ISysUserRoleService;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户角色控制器
 * Description：
 *
 * @author wuqiulin
 */
@RestController
@RequestMapping("/system/sysUserRole")
@Tag(name = "用户配置角色接口")
public class SysUserRoleController extends BaseController {

    @Resource
    private ISysUserRoleService sysUserRoleService;

    @PostMapping("/doAssign")
    public AjaxResult doAssign(@RequestBody AssginRoleDto assginRoleDto) {
        sysUserRoleService.doAssign(assginRoleDto);
        return success();
    }
}
