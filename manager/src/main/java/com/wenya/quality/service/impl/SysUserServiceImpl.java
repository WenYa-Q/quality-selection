package com.wenya.quality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.quality.doamin.system.SysUser;
import com.wenya.quality.dto.system.SysUserDto;
import com.wenya.quality.mapper.SysUserMapper;
import com.wenya.quality.service.ISysUserService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 用户业务
 *
 * @author wenya
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 查询所有用户
     *
     * @param sysUserDto 查询条件
     * @return {@link List }<{@link SysUser }>
     */
    @Override
    public List<SysUser> selectSysUser(SysUserDto sysUserDto) {
        //构建查询条件
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();

        //格式化时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //判断是否查询所有用户
        if (StringUtils.isNotEmpty(sysUserDto.getKeyword()) && StringUtils.isNotBlank(sysUserDto.getKeyword())) {
            queryWrapper.eq("username", sysUserDto.getKeyword());
        }

        if (StringUtils.isNotEmpty(sysUserDto.getCreateTimeBegin()) && StringUtils.isNotBlank(sysUserDto.getCreateTimeBegin())) {
            try {
                queryWrapper.ge("create_time", simpleDateFormat.parse(sysUserDto.getCreateTimeBegin()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        if (StringUtils.isNotEmpty(sysUserDto.getCreateTimeEnd()) && StringUtils.isNotBlank(sysUserDto.getCreateTimeEnd())) {
            try {
                queryWrapper.le("create_time", simpleDateFormat.parse(sysUserDto.getCreateTimeEnd()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return sysUserMapper.selectList(queryWrapper);
    }
}
