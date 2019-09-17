package com.useful_person.web.async;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import lombok.extern.log4j.Log4j2;
import net.bytebuddy.utility.RandomString;

@RestController
@Log4j2
public class AsyncController {

	@Autowired
	private MockQueue mockQueue;

	@Autowired
	private DeferredResultHolder deferredResultHolder;

	@RequestMapping("/order")
	public Callable<String> order() throws Exception {
		log.info("主线程开始");
		Callable<String> callable = new Callable<String>() {

			@Override
			public String call() throws Exception {
				log.info("副线程开始");
				Thread.sleep(1000);
				log.info("副线程返回");
				return "success";
			}
		};
		log.info("主线程返回");
		return callable;
	}
	/**
	 * 可以使用
	 */
	@RequestMapping("/order2")
	public DeferredResult<String> order2() throws Exception {
		log.info("主线程开始");
		String taskNumber = RandomString.make(8);
		mockQueue.setPlaceOrder(taskNumber);
		DeferredResult<String> result = new DeferredResult<>();
		deferredResultHolder.getTaskMap().put(taskNumber, result);
		log.info("主线程返回");
		return result;
	}
}