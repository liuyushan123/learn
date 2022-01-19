package com.liuyushan.fp.test;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @author lys
 * 2022/1/2
 */
public class Test01iterator {

    @Test
    public void test01() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }

        for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
            String s = it.next();
            it.remove();
            System.out.println(s);
        }
        Iterator<String> iterator = list.iterator();
        System.out.println(iterator);
    }

    /**
     * 获取时间
     */
    @Test
    public void test02() {
        LocalDate start = LocalDate.parse("2021-12-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2021-12-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ArrayList<LocalDate> list = new ArrayList<>();
        List<Integer> timeList = Arrays.asList(2, 3, 4, 5, 6);
        long days = ChronoUnit.DAYS.between(start, end);
        Calendar startCalender = GregorianCalendar.from(start.atStartOfDay(ZoneId.systemDefault()));
        for (int i = 0; i <= days; i++) {
            if (timeList.contains(startCalender.get(Calendar.DAY_OF_WEEK))) {// 1代表周日，7代表周六
                list.add(
                        LocalDateTime.ofInstant(
                                startCalender.toInstant(),
                                ZoneOffset.systemDefault()
                        ).toLocalDate()
                );
            }
            startCalender.add(Calendar.DATE, 1);
        }
        System.out.println(list);
    }

    /**
     * 生成时间
     */
    @Test
    public void test03() {
        int between = 600;
        int start = 3600 * 7;
        String time = time(start, between);
        System.out.println(time);
    }

    private String time(int start, int between) {
        Random random = new Random();
        int i = random.nextInt(between + 1) + start;
        System.out.println(i);
        String hour = i / 3600 < 10 ? "0" + i / 3600 : String.valueOf(i / 3600);
        String minter = i % 3600 / 60 < 10 ? "0" + i % 3600 / 60 : String.valueOf(i % 3600 / 60);
        String seconds = i % 60 < 10 ? "0" + i % 60 : String.valueOf(i % 60);
        return hour + ":" + minter + ":" + seconds;
    }
}
