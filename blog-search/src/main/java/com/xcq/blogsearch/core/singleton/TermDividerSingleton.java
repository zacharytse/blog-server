package com.xcq.blogsearch.core.singleton;

import com.xcq.blogsearch.core.search.split.divide.TermDivider;

public class TermDividerSingleton {
    /**
     * 使用volatile禁止指令重排，防止返回一个未初始化但分配了空间的对象
     */
    private volatile static TermDivider divider;

    private TermDividerSingleton() {
    }

    public static TermDivider getDivider() {
        if(divider == null) {
            synchronized (TermDivider.class) {
                if(divider == null) {
                    divider = new TermDivider();
                }
            }
        }
        return divider;
    }
}
