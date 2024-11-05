package com.wenya.quality.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.wenya.quality.sys.service.ISysUserService;
import com.wenya.quality.doamin.system.SysUser;
import com.wenya.quality.dto.system.LoginDto;
import com.wenya.quality.exception.BusinessCustomizeException;
import com.wenya.quality.sys.mapper.SysUserMapper;
import com.wenya.quality.vo.common.ResultCodeEnum;
import com.wenya.quality.vo.system.LoginVo;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 用户业务
 *
 * @author wenya
 */
@Service("sysUserService")
public class ISysUserServiceImpl implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource(type = StringRedisTemplate.class)
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登录
     *
     * @param loginDto 登录dto
     * @return {@link LoginVo }
     */
    @Override
    public LoginVo login(LoginDto loginDto) {
        //根据用户名和密码查询用户
        SysUser sysUserParam = new SysUser();

        //获取用户名
        sysUserParam.setUserName(loginDto.getUserName());

        //获取密码
        String password = loginDto.getPassword();
        //进行MD5加密
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        sysUserParam.setPassword(md5Password);

        //查询用户
        SysUser sysUser = sysUserMapper.selectSysUserOne(sysUserParam);
        if (sysUser == null) {
            throw new BusinessCustomizeException(ResultCodeEnum.LOGIN_ERROR);
        }

        //生成令牌
        String token = UUID.randomUUID().toString().replace("-", "");

        //保存令牌信息
        stringRedisTemplate.opsForValue().set("user:login" + token,
                JSON.toJSONString(sysUser), 30, TimeUnit.HOURS);

        //构建返回对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("");

        return loginVo;
    }
}
