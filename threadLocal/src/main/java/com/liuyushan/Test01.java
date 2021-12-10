package com.liuyushan;
/**
 * @author lys
 * 2021/10/11
 */
public class Test01 {

    public static void main(String[] args) {

        ThreadLocalContext.setThreadLocal1("1111");
        ThreadLocalContext.setThreadLocal1("2222");
        String threadLocal1 = ThreadLocalContext.getThreadLocal1();

        ContextPrivate.setContext("s");
        String context = ContextPrivate.getContext();
        System.out.println(threadLocal1);
        System.out.println(context);
    }

}
