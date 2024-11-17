package com.wenya.quality.service;

import com.wenya.quality.doamin.system.SysRole;
import com.wenya.quality.dto.system.SysRoleDto;

import java.util.List;

public interface ISysRoleService {

    /**
     * 分页查询
     *
     * @return {@link List }<{@link SysRole }>
     */
    public List<SysRole> findByPage(String roleName);

    /**
     * 保存sys角色
     *
     * @param sysRoleDto 需要保存的角色
     */
    public int saveSysRole(SysRoleDto sysRoleDto);
}
