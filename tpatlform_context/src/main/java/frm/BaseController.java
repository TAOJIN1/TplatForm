package frm;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import utils.PageUtils;

public class BaseController {

	/**
	 * 用于页面AJAX返回JSON
	 * 
	 * @param request
	 * @param response
	 * @param str
	 */
	protected void responseJson(HttpServletRequest request, HttpServletResponse response, String str) {
		try {
			response.setContentType("text/xml; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			if (str.toString().indexOf("[") == 0) {
				response.getWriter().write(str.toString());
			} else {
				response.getWriter().write("[" + str.toString() + "]");
			}
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 页面列表JSON返回
	 * 
	 * @param request
	 * @param response
	 * @param str
	 */
	protected void pageJson(HttpServletRequest request, HttpServletResponse response, String str) {
		try {
			response.setContentType("text/xml; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			if (!"".equals(str)) {
				str = str.substring(1, str.toString().length() - 1);
			}
			response.getWriter().write(str);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 直接返回字符串内容
	 * 
	 * @param request
	 * @param response
	 * @param str
	 */
	protected void responsePrint(HttpServletRequest request, HttpServletResponse response, String str) {
		try {
			response.setContentType("text/json; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(str.toString());
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取流程信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public Map<String, Object> getProcessData(HttpServletRequest request, HttpServletResponse response) {
		JSONObject processObj = JSONObject.fromObject(request.getParameter("processData"));
		// 流程数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskId", processObj.get("taskId"));
		map.put("processId", processObj.get("processId"));
		map.put("comment", processObj.get("description"));
		map.put("processKey", processObj.get("processKey"));
		map.put("outCome", processObj.get("outCome"));
		map.put("approveperson", processObj.get("approveperson"));
		// 流程变量
		Map<String, Object> vMap = new HashMap<String, Object>();
		vMap.put("operIds", processObj.get("processSelectannuitysersValue"));
		vMap.put("subProcessIds", processObj.get("processSelectedSubProcessValue"));
		map.put("variables", vMap);
		return map;
	}

	/**
	 * 直接返回json类型
	 * 
	 * @param request
	 * @param response
	 * @param jobj
	 */
	protected void responseJsonPrint(HttpServletRequest request, HttpServletResponse response, JSONObject jobj) {
		try {
			jobj.element("httpCode", "OK");// 成功

			response.setContentType("text/json; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jobj.toString());
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 直接返回字符串内容,并且不包含编码格式
	 * 
	 * @param request
	 * @param response
	 * @param str
	 */
	protected void resPrint(HttpServletRequest request, HttpServletResponse response, String str) {
		try {
			response.getWriter().print(str.toString());
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getToken() {
		TokenProcessor tp = TokenProcessor.getInstance();
		String token = tp.generateToken();
		return token;
	}

	public Map getInfos(HttpServletRequest request, Object obj) {
		String data = request.getParameter("data");
		String pagedata = request.getParameter("pdata");
		Map returnmap = new HashMap();
		if (data != null) {
			try {
				JSONObject jasonObject = JSONObject.fromObject(data);
				Map datamap = (Map) jasonObject;
				BeanUtils.populate(obj, datamap);
			} catch (IllegalAccessException e) {
				returnmap.put("data", null);
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				returnmap.put("data", null);
				e.printStackTrace();
			}
			returnmap.put("data", obj);
		} else {
			returnmap.put("data", null);
		}
		if (pagedata != null) {
			JSONObject jasonObject = JSONObject.fromObject(pagedata);
			PageUtils pageutils = new PageUtils();
			pageutils.setPage((int) jasonObject.get("pageNumber"));
			// pageutils.setRows((int) jasonObject.get("pageSize"));
			returnmap.put("pdata", pageutils);
		} else {
			returnmap.put("pdata", null);
		}
		return returnmap;
	}
}

// 设计为单例模式
class TokenProcessor {
	private TokenProcessor() {
	};

	private static final TokenProcessor instance = new TokenProcessor();

	public static TokenProcessor getInstance() {
		return instance;
	}

	public String generateToken() {
		// 获得随机数字符串
		String token = System.currentTimeMillis() + new Random().nextInt() + "";
		// 获得数据摘要
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			String md5Str = ByteUtil.bytes2Hex(md.digest(token.getBytes()));
			return md5Str;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}

class ByteUtil {
	private final static char[] HEX = "0123456789abcdef".toCharArray();

	/**
	 * 将字节数组转成 16 进制的字符串来表示，每个字节采用两个字符表表示
	 * 
	 * @param bs
	 *            需要转换成 16 进制的字节数组
	 * @return
	 */
	public static String bytes2Hex(byte[] bys) {
		char[] chs = new char[bys.length * 2];
		for (int i = 0, offset = 0; i < bys.length; i++) {
			chs[offset++] = HEX[bys[i] >> 4 & 0xf];
			chs[offset++] = HEX[bys[i] & 0xf];
		}
		return new String(chs);
	}

}