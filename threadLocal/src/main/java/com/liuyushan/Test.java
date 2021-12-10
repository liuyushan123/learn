package com.liuyushan;

/**
 * @author lys
 * 2021/10/9
 */
public class Test {

    public static ThreadLocal<String> THREAD_LOCAL_1 = new ThreadLocal<>();
    public static ThreadLocal<String> THREAD_LOCAL_2 = new ThreadLocal<>();

    /**
     * 测试一个线程可以有几个threadLocal对象
     */
    public static void main(String[] args) {
        ThreadLocalContext.setThreadLocal1("1111");
        System.out.println(ThreadLocalContext.getThreadLocal1());
        THREAD_LOCAL_1.set("zouyiyi");
        THREAD_LOCAL_2.set("liuyushan");
        new Thread(() -> {
            THREAD_LOCAL_1.set("shangban");
            THREAD_LOCAL_2.set("beijing");
            System.out.println(Thread.currentThread() + " " + ThreadLocalContext.getThreadLocal1());
            System.out.println(Thread.currentThread() + " " + THREAD_LOCAL_2.get());
            System.out.println(Thread.currentThread() + " " + THREAD_LOCAL_1.get());
        }).start();

        System.out.println(Thread.currentThread() + " " + THREAD_LOCAL_2.get());
        System.out.println(Thread.currentThread() + " " + THREAD_LOCAL_1.get());
    }

}
