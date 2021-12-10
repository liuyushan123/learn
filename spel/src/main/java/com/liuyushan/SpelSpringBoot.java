package com.liuyushan;
import com.liuyushan.spel.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lys
 * 2021/10/10
 */
@SpringBootApplication
public class SpelSpringBoot {
    @Autowired
    StudentDao studentDao;
    public static void main(String[] args) {
        SpringApplication.run(SpelSpringBoot.class, args);
    }
}
