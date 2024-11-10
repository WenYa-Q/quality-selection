package com.wenya.quality.system.service;

import com.wenya.quality.doamin.system.SysRole;

import java.util.List;

public interface ISysRoleService {

    /**
     * 分页查询
     *
     * @return {@link List }<{@link SysRole }>
     */
    public List<SysRole> findByPage(String roleName);
}
