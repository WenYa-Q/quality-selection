package com.wenya.quality;

import com.wenya.quality.doamin.system.SysUser;
import com.wenya.quality.doamin.user.UserInfo;

/**
 * 线程上下文工具类
 *
 * @author wenya
 */
public class AuthContextUtil {

    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

    private static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>() ;

    /**
     * 设置线程上下文
     *
     * @param user 用户
     */
    public static void setAuthContext(SysUser user) {
        threadLocal.set(user);
    }

    /**
     * 获取线程上下文
     *
     * @return {@link SysUser }
     */
    public static SysUser getAuthContext() {
        return (SysUser) threadLocal.get();
    }

    /**
     * 清除线程上下文
     */
    public static void clearAuthContext() {
        threadLocal.remove();
    }

    // 定义存储数据的静态方法
    public static void setUserInfo(UserInfo userInfo) {
        userInfoThreadLocal.set(userInfo);
    }

    // 定义获取数据的方法
    public static UserInfo getUserInfo() {
        return userInfoThreadLocal.get() ;
    }

    // 删除数据的方法
    public static void removeUserInfo() {
        userInfoThreadLocal.remove();
    }
}
