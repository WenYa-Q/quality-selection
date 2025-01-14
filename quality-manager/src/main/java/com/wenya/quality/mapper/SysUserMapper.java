package com.wenya.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.system.SysUser;
import com.wenya.quality.dto.system.SysUserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * sys用户映射器
 *
 * @author wenya
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 查询一个用户
     *
     * @param sysUser sys用户
     * @return {@link SysUser }
     */
    SysUser selectSysUserOne(SysUser sysUser);
}
