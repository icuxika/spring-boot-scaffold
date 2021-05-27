package com.icuxika.scaffold.module.auth.entity;

/**
 * 认证类型
 */
public enum AuthType {

    /**
     * 本地认证：密码登录、短信登录；
     */
    LOCAL(1),

    /**
     * 第三方登录：GitHub登录、码云登录；
     */
    THIRD(2);

    private int index;

    AuthType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
