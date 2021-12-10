package com.liuyushan.event;


import com.liuyushan.json.JsonBase;
import lombok.Data;

/**
 * @param <T> 数据类型
 *            Date: 2021/8/6
 * @author lanhuan
 */
@Data
public abstract class BaseSpringEvent<T extends JsonBase> {

    private Long id;

    private Long userId;

    private T payload;

    /**
     * 绑定数据
     *
     * @param payload 数据
     * @param <S>     s
     * @return 自身
     */
    public final <S extends BaseSpringEvent<T>> S withPayload(T payload) {
        this.payload = payload;
        return (S) this;
    }

}
