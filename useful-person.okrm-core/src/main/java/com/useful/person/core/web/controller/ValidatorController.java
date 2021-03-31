package com.useful.person.core.web.controller;

import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.core.constants.ReturnCode;
import com.useful.person.core.utils.IdCardUtil;
import com.useful.person.core.vo.ResponseData;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/utils")
@Api(value = "验证controller", tags = { "验证操作接口" } )
public class ValidatorController {

	@GetMapping("/validator/idcardno")
	public Callable<ResponseData<String>> validatorIdcardNo(@RequestParam(required = true) String idcardno, HttpServletResponse response) {
		Callable<ResponseData<String>> callable = new Callable<>() {
			@Override
			public ResponseData<String> call() throws Exception {
				ResponseData<String> responseData = null;
				boolean flag = IdCardUtil.checkIdCardNo(idcardno);
				if (flag) {
					responseData = new ResponseData<String>(ReturnCode.CORRECT.getCode(), "身份证号正确！", "");
				} else {
					response.setStatus(HttpStatus.BAD_REQUEST.value());
					responseData = new ResponseData<String>(ReturnCode.ERROR.getCode(), "身份证号不正确", "");
				}
				return responseData;
			}
		};
		return callable;
	}
}
