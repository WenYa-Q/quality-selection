package com.wenya.quality.mapper;

import com.wenya.quality.doamin.system.SysRole;
import com.wenya.quality.dto.system.SysRoleDto;
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
    List<SysRole> selectSysRoleAll(SysRole sysRole);

    /**
     * 保存角色
     *
     * @param sysRoleDto sys角色dto
     * @return int
     */
    int saveRole(SysRoleDto sysRoleDto);
}
