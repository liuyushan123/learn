package com.liuyushan;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author lys
 * 2021/10/19
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class Test {

    @org.junit.jupiter.api.Test
    public void test(){
        System.out.println("hello test");
    }
}
