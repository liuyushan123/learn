package com.liuyushan.fp.entiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lys
 * 2021/10/26
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {

    Boolean force_add;

    String org_id;

    String region;

    Integer type;

    Integer mode;

    String name;

    String mobile;

    String employee_num;

    String dept_id;

    String title;

    String extId;
}
