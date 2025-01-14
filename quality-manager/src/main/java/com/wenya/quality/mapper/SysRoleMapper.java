package com.wenya.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.system.SysRole;
import com.wenya.quality.dto.system.SysRoleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色映射器
 * Description：
 *
 * @author wuqiulin
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

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

    /**
     * 更新角色
     *
     * @param sysRole sys角色
     */
    int updateSysRole(SysRole sysRole);

    /**
     * 按id删除 逻辑删除
     *
     * @param id id
     * @return int
     */
    int deleteById(Long id);
}
