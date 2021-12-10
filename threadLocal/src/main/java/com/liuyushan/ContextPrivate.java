package com.liuyushan;

/**
 * @author lys
 * 2021/10/11
 */
public class ContextPrivate {
    public static final ThreadLocal<String> context = new ThreadLocal();

    public static void setContext(String s) {
        context.set(s);
    }

    public static String getContext() {
        return context.get();
    }

    public static void resetContext() {
        context.remove();
    }

}
