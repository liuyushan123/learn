package com.liuyushan;

/**
 * @author lys
 * 2021/10/9
 */
public class ThreadLocalContext {
    public static final ThreadLocal<String> THREAD_LOCAL_1 = new ThreadLocal<>();

    public static void setThreadLocal1(String value) {
        THREAD_LOCAL_1.set(value);
    }

    public static String getThreadLocal1() {
        return THREAD_LOCAL_1.get();
    }
}
