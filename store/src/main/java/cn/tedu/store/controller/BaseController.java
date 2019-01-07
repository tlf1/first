package cn.tedu.store.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.service.exception.DuplicateKeyException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.PasswordNotMatchException;
import cn.tedu.store.service.exception.ServiceException;
import cn.tedu.store.service.exception.UserNotFoundException;
import cn.tedu.store.util.ResponseResult;

/**
 * 当前项目中所有控制器类的基类
 */
public abstract class BaseController {
	
	/**
	 * 正确响应时的代号
	 */
	public static final Integer SUCCESS = 200;

	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public ResponseResult<Void> handleException(
			Exception e) {
		if (e instanceof DuplicateKeyException) {
			// 400-违反了Unique约束的异常
			return new ResponseResult<>(400, e);
		} else if (e instanceof UserNotFoundException) {
			// 401-用户数据不存在
			return new ResponseResult<>(401, e);
		} else if (e instanceof PasswordNotMatchException) {
			// 402-密码错误
			return new ResponseResult<>(402, e);
		} else if (e instanceof InsertException) {
			// 500-插入数据异常
			return new ResponseResult<>(500, e);
		}
		
		return null;
	}
	
}




