package com.liuyushan.event.listenerImpl;

import com.liuyushan.event.BaseSpringEventListener;
import com.liuyushan.event.dto.FireDto;
import com.liuyushan.event.eventImpl.FireEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lys
 * 2021/10/19
 */
@Slf4j
@Component
public class FireListener implements BaseSpringEventListener<FireEvent, FireDto> {
    @Override
    public void onEvent(FireEvent event) throws Exception {
        log.info("线程{}", Thread.currentThread().getName());
        FireDto payload = event.getPayload();
        System.out.println(payload.getAddress()+"起火了！！！！！");
    }
}
