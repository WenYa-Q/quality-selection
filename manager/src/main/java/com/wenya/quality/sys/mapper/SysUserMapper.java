package com.wenya.quality.sys.mapper;

import com.wenya.quality.doamin.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * sys用户映射器
 *
 * @author wenya
 */
@Mapper
public interface SysUserMapper {

    /**
     * 查询一个用户
     *
     * @param sysUser sys用户
     * @return {@link SysUser }
     */
    public SysUser selectSysUserOne(SysUser sysUser);
}
