package com.wenya.quality.service;

import com.wenya.quality.doamin.system.SysRole;
import com.wenya.quality.dto.system.SysRoleDto;

import java.util.List;

public interface ISysRoleService {

    /**
     * 分页查询
     *
     * @return {@link List }<{@link SysRole }> 分页角色数据集
     */
    List<SysRole> findByPage(String roleName);

    /**
     * 保存sys角色
     *
     * @param sysRoleDto 需要保存的角色
     */
    int saveSysRole(SysRoleDto sysRoleDto);

    /**
     * 更新sys角色
     *
     * @param sysRole sys角色
     * @return int
     */
    int updateSysRole(SysRole sysRole);
}
