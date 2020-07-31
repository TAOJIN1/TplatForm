package utils;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据类型
 * @author Lanvo
 * @date 2018-11-26
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	private static String code_ = "code";
	private static String msg_ = "msg";
	
	private static int code_success = 0;
	private static String msg_success = "success";
	
	
	
	public R() {
		put(code_, code_success);
		put(msg_, msg_success);
	}
	
	public static R error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put(code_, code);
		r.put(msg_, msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put(msg_, msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
