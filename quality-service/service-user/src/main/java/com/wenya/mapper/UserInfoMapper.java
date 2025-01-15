package com.wenya.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息映射器
 * Description：
 *
 * @author wuqiulin
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
