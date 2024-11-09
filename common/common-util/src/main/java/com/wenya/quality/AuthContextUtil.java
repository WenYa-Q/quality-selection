package com.wenya.quality;

import com.wenya.quality.doamin.system.SysUser;

/**
 * 线程上下文工具类
 *
 * @author wenya
 */
public class AuthContextUtil {

    private static final ThreadLocal threadLocal = new ThreadLocal();

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
}
