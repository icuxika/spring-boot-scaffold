package com.icuxika.scaffold.module.auth.entity;

/**
 * 第三方登录类型
 */
public enum LoginType {

    /**
     * GitHub登录
     */
    GITHUB(1),

    /**
     * 码云登录
     */
    GITEE(2),

    /**
     * WeChat登录
     */
    WECHAT(3);

    private int index;

    LoginType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
