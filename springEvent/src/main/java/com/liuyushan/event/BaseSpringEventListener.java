package com.liuyushan.event;


import com.liuyushan.json.JsonBase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @param <T> 事件类型
 * @param <S> 数据类型
 * @author lanhuan
 * Date: 2021/8/9
 */
public interface BaseSpringEventListener<T extends BaseSpringEvent<S>, S extends JsonBase> {
    /**
     * 监听事件
     *
     * @param event 事件
     * @throws Exception 异常
     */
    @TransactionalEventListener(fallbackExecution = true)
    void onEvent(T event) throws Exception;
}
