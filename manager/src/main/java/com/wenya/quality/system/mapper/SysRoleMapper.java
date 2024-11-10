package com.wenya.quality.system.mapper;

import com.wenya.quality.doamin.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {

    /**
     * 查询所有的角色
     *
     * @param sysRole sys角色
     * @return {@link List }<{@link SysRole }>
     */
    public List<SysRole> selectSysRoleAll(SysRole sysRole);
}
