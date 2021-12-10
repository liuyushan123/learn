package com.liuyushan.executor;

import com.liuyushan.event.dto.FireDto;
import com.liuyushan.event.eventImpl.FireEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author lys
 * 2021/10/19
 */
@Slf4j
@Component
public class EventExecutor implements ApplicationRunner{

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("开始测试，线程{}", Thread.currentThread().getName());
        FireEvent fireEvent = new FireEvent();
        fireEvent.withPayload(new FireDto("广泽"));
        publisher.publishEvent(fireEvent);
    }
}
