package com.liuyushan.spel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lys
 * 2021/10/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventor {

    String name;

    Date birthday;

    String nationality;
}
