package com.wenya.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wenya.quality.doamin.system.SysRole;
import com.wenya.quality.dto.system.SysRoleDto;

import java.util.List;

/**
 * 角色服务
 * Description：
 *
 * @author wuqiulin
 */
public interface ISysRoleService extends IService<SysRole> {

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

    /**
     * 根据ID删除角色
     *
     * @param id id
     */
    int deleteById(Long id);
}
