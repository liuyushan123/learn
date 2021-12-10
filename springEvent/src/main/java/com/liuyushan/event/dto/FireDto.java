package com.liuyushan.event.dto;

import com.liuyushan.json.JsonBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lys
 * 2021/10/19
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FireDto extends JsonBase {
    private String address;
}
