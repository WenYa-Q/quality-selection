package com.wenya.service.impl;

import cn.hutool.http.server.HttpServerRequest;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.mapper.UserInfoMapper;
import com.wenya.quality.doamin.user.UserInfo;
import com.wenya.quality.dto.h5.UserLoginDto;
import com.wenya.quality.dto.h5.UserRegisterDto;
import com.wenya.quality.exception.BusinessCustomizeException;
import com.wenya.quality.vo.common.ResultCodeEnum;
import com.wenya.quality.vo.h5.UserInfoVo;
import com.wenya.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 用户信息服务实施
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisTemplate<String , String> redisTemplate;

    /**
     * 注册
     *
     * @param userRegisterDto 用户注册dto
     */
    @Override
    public void register(UserRegisterDto userRegisterDto) {
        // 获取数据
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();

        //校验参数
        if(StringUtils.hasText(username) ||
                StringUtils.hasText(password) ||
                StringUtils.hasText(nickName) ||
                StringUtils.hasText(code)) {
            throw new BusinessCustomizeException(ResultCodeEnum.DATA_ERROR);
        }

        //校验校验验证码
        String codeValueRedis = redisTemplate.opsForValue().get("phone:code:" + username);
        if(!code.equals(codeValueRedis)) {
            throw new BusinessCustomizeException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        UserInfo userInfo = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUsername, username));
        if(null != userInfo) {
            throw new BusinessCustomizeException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        //保存用户信息
        userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setNickName(nickName);
        userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfo.setPhone(username);
        userInfo.setStatus(1);
        userInfo.setSex(0);
        userInfo.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        userInfoMapper.insert(userInfo);

        // 删除Redis中的数据
        redisTemplate.delete("phone:code:" + username) ;
    }

    /**
     * 登录
     *
     * @param userLoginDto 用户登录dto
     * @return {@link String }
     */
    @Override
    public String login(UserLoginDto userLoginDto) {
        // 获取数据
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();
        if (StringUtils.hasText(username) || StringUtils.hasText(password)) {
            throw new BusinessCustomizeException(ResultCodeEnum.DATA_ERROR);
        }

        //获取用户
        UserInfo user = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUsername, username));
        if (null == user) {
            throw new BusinessCustomizeException(ResultCodeEnum.LOGIN_ERROR);
        }

        //校验密码
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            throw new BusinessCustomizeException(ResultCodeEnum.LOGIN_ERROR);
        }

        //校验账号是否被禁用
        if (user.getStatus() == 0) {
            throw new BusinessCustomizeException(ResultCodeEnum.ACCOUNT_STOP);
        }

        //生成token
        String token = DigestUtils.md5DigestAsHex((username + System.currentTimeMillis()).getBytes());
        //保存token
        redisTemplate.opsForValue().set("user:spzx:token:" + token, JSONObject.toJSONString(user), 30, TimeUnit.DAYS);
        return token;
    }

    /**
     * 获取当前用户信息
     *
     * @param request 请求
     * @return {@link UserInfoVo }
     */
    @Override
    public UserInfoVo getCurrentUserInfo(HttpServerRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.hasText(token)) {
            String userJson = redisTemplate.opsForValue().get("user:spzx:token:" + token);
            if (StringUtils.hasText(userJson)) {
                return JSONObject.parseObject(userJson, UserInfoVo.class);
            }
        }
        throw new BusinessCustomizeException(ResultCodeEnum.LOGIN_AUTH);
    }
}
