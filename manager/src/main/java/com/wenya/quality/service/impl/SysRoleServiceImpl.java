package com.wenya.quality.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.quality.doamin.system.SysRole;
import com.wenya.quality.doamin.system.SysUser;
import com.wenya.quality.dto.system.SysRoleDto;
import com.wenya.quality.mapper.SysRoleMapper;
import com.wenya.quality.service.ISysRoleService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.nio.file.LinkOption;
import java.util.List;

/**
 * 角色权限控制
 *
 * @author wenya
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    /**
     * 分页查询
     *
     * @param roleName 查询角色昵称
     * @return {@link List }<{@link SysRole }>
     */
    @Override
    public List<SysRole> findByPage(String roleName) {
        //构建查询对象
        SysRole sysRole = new SysRole();

        //判断是否查询所有角色
        if (StringUtils.isNotEmpty(roleName) && StringUtils.isNotBlank(roleName)) {
            sysRole.setRoleName(roleName);
        }

        return sysRoleMapper.selectSysRoleAll(sysRole);
    }

    /**
     * 保存角色
     *
     * @param sysRoleDto 需要保存的角色
     */
    @Override
    public int saveSysRole(SysRoleDto sysRoleDto) {
        int row = 0;
        //判断角色名称和编码是否为空
        if (StringUtils.isNotBlank(sysRoleDto.getRoleName()) && StringUtils.isNotEmpty(sysRoleDto.getRoleName())) {
            row = sysRoleMapper.saveRole(sysRoleDto);
        }
        return row;
    }

    /**
     * 更新角色
     *
     * @param sysRole sys角色
     * @return int
     */
    @Override
    public int updateSysRole(SysRole sysRole) {
        return sysRoleMapper.updateSysRole(sysRole);
    }

    /**
     * 根据ID删除角色
     *
     * @param id id
     */
    @Override
    public int deleteById(Long id) {
        return sysRoleMapper.deleteById(id);
    }
}
