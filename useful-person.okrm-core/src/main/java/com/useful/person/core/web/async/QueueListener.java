package com.useful.person.core.web.async;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author peter
 *
 */
@Slf4j
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @SuppressWarnings("all")
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(() -> {
            while (true) {
                if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
                    String taskNumber = mockQueue.getCompleteOrder();
                    log.info("返回任务处理结果: [" + taskNumber + "]");
                    deferredResultHolder.getTaskMap().get(taskNumber)
                            .setResult("task order: " + taskNumber + " complete");
                    mockQueue.setCompleteOrder(null);
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        String message = e.getMessage();
                        String localizeMessage = e.getLocalizedMessage();
                        log.info("message: " + message + ", localizeMessage: " + localizeMessage);
                    }
                }
            }
        }).start();
    }
}
