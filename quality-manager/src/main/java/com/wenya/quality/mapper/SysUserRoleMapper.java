package com.wenya.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.system.SysRoleUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色映射器
 * Description：
 *
 * @author wuqiulin
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysRoleUser> {
}
