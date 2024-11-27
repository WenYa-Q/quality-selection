package com.wenya.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wenya.quality.doamin.system.SysRoleUser;
import com.wenya.quality.dto.system.AssginRoleDto;

/**
 * 用户角色服务
 * Description：
 *
 * @author wuqiulin
 */
public interface ISysUserRoleService extends IService<SysRoleUser> {
    /**
     * 保存用户角色
     *
     * @param assginRoleDto assgin角色dto
     * @return int
     */
    void doAssign(AssginRoleDto assginRoleDto);
}
