package com.wenya.quality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.quality.doamin.system.SysRoleUser;
import com.wenya.quality.dto.system.AssginRoleDto;
import com.wenya.quality.mapper.SysUserRoleMapper;
import com.wenya.quality.service.ISysUserRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 用户角色服务实施
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysRoleUser> implements ISysUserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 保存用户角色
     *
     * @param assginRoleDto 用户角色配置
     */
    @Transactional
    @Override
    public void doAssign(AssginRoleDto assginRoleDto) {
        //删除该用户之前的所有角色配置
        QueryWrapper<SysRoleUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", assginRoleDto.getUserId());
        sysUserRoleMapper.delete(queryWrapper);

        //插入角色配置

        for (Long roleId : assginRoleDto.getRoleIdList()) {
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setUserId(assginRoleDto.getUserId());
            sysRoleUser.setIsDeleted(0);
            sysRoleUser.setCreateTime(new Date());
            sysRoleUser.setUpdateTime(new Date());
            sysRoleUser.setRoleId(roleId);
            sysUserRoleMapper.insert(sysRoleUser);
        }
    }
}
