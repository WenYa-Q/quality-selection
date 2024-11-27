package com.wenya.quality.doamin.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wenya.quality.doamin.base.BaseEntity;
import lombok.Data;

@Data
@TableName("sys_user_role")
public class SysRoleUser extends BaseEntity {

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Long roleId;
    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

}
