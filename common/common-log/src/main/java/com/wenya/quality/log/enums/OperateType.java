package com.wenya.quality.log.enums;

/**
 * 操作类型
 *
 * @author wenya
 */
public enum OperateType {

    QUERY("查询"),
    DETECT("删除"),
    UPDATE("更新"),
    INSERT("插入"),
    EXPORT("导出"),
    IMPORT("导入"),
    LOGIN("登入"),
    LOGOUT("登出");

    /**
     * 类型
     */
    private String type;

    OperateType(String type){
        this.type = type;
    }

    public String getOperateType(){
        return type;
    }
}
