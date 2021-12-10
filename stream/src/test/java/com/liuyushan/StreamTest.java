package com.liuyushan;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 * @author lys
 * 2021/11/6
 */
@SpringBootTest
public class StreamTest {


    int j;

    /**
     * flatMap里的Function返回值必须为Stream
     */
    @Test
    public void flatMap() {
        List<Integer> collect = Stream.of(asList(1, 2), asList(3, 4)).flatMap(s -> s.stream()).collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * map
     */
    @Test
    public void map() {
        List<Integer> collect = Stream.of(1, 2, 3).map(s -> j = s + 1).collect(Collectors.toList());
        System.out.println(collect);
        Student student = new Student();
    }

    @Test
    public void min() {
        int i = 0;
        Integer integer = Stream.of(1, 2, 3).min(Comparator.comparing(t -> j = t)).get();
        System.out.println(integer);
        Student student = new Student();
    }


    @Data
    static class Student implements Comparable<Student> {
        String name;
        Integer age;

        @Override
        public int compareTo(Student o) {
            return this.age - o.getAge();
        }
    }

@Test
    public void t() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -32);
        System.out.println(c.getTime());
    }
}
