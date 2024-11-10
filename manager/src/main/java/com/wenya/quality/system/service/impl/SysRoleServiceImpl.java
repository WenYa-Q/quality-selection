package com.wenya.quality.system.service.impl;

import com.wenya.quality.doamin.system.SysRole;
import com.wenya.quality.system.mapper.SysRoleMapper;
import com.wenya.quality.system.service.ISysRoleService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色权限控制
 *
 * @author wenya
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements ISysRoleService {

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
}
