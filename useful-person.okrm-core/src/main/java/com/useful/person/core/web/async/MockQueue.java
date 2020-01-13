package com.useful.person.core.web.async;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author peter
 *
 */
@Slf4j
@Component
public class MockQueue {

	private String placeOrder;

	private String completeOrder;

	public String getPlaceOrder() {
		return placeOrder;
	}

	@SuppressWarnings("all")
	public void setPlaceOrder(String placeOrder) throws Exception {
		new Thread(() -> {
			log.info("接收离线任务请求: [" + placeOrder + "]");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				String message = e.getMessage();
				String localizeMessage = e.getLocalizedMessage();
				log.info("message: " + message + ", localizeMessage: " + localizeMessage);
			}
			this.completeOrder = placeOrder;
			log.info("离线任务处理完毕: [" + placeOrder + "]");
		}).start();
	}

	public String getCompleteOrder() {
		return completeOrder;
	}

	public void setCompleteOrder(String completeOrder) {
		this.completeOrder = completeOrder;
	}

}
