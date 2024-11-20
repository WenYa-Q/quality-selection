package com.wenya.quality.service;

import com.wenya.quality.doamin.system.SysUser;
import com.wenya.quality.dto.system.SysUserDto;

import java.util.List;

/**
 * 用户业务接口
 *
 * @author wenya
 */
public interface ISysUserService {

    /**
     * 查询所有用户
     *
     * @param sysUserDto 查询条件
     * @return {@link List }<{@link SysUser }>
     */
    List<SysUser> selectSysUser(SysUserDto sysUserDto);
}
