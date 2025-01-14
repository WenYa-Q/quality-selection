package com.wenya.quality.doamin.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wenya.quality.doamin.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * sys角色菜单
 * Description：
 *
 * @author wuqiulin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseEntity {

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 菜单id
     */
    @TableField("menu_id")
    private Long menuId;

    @TableField("is_half")
    private Integer isHalf;
}
