package com.taikang.utility;

import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * 1  note:字符处理类
 * 2 * @Author: Lanvo
 * 3 * @Date: 2020/8/1 12:14
 * 4
 */
public class StringUtils {

    public static final String BLANK = "";

    public static final String LINE_SEPARATOR = "\r\n";

    public static final String LINE_SEPARATOR_JSP = "<br>";

    public static final String EMPTY = "";

    /**
     * 判断String是否为空
     *
     * @param str
     * @return 空为true
     */
    public static Boolean isNullString(String str) {
        if (str == null || "".equals(str)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 将null转化为""
     *
     * @param str
     * @return
     */
    public static String changeNullString(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * 将首字母转换为大写
     *
     * @param str
     * @return
     */
    public static String toUpperCaseFirstOne(String str) {
        if (Character.isUpperCase(str.charAt(0))) {
            return str;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1))
                    .toString();
        }
    }

    /**
     * 将首字母转换为小写
     *
     * @param str
     * @return
     */
    public static String toLowerCaseFirstOne(String str) {
        if (Character.isLowerCase(str.charAt(0))) {
            return str;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1))
                    .toString();
        }
    }

    public static String nvl(String data) {
        if (data == null) {
            return BLANK;
        } else {
            return data;
        }
    }

    public static String getThrowableInfo(Throwable t) {
        StringBuffer sb = new StringBuffer();

        if (t == null) {
            return "";
        }
        Throwable t2 = t;
        if (t instanceof RemoteException) {
            t2 = getDetailOfRemoteEx((RemoteException) t);
        }
        sb.append(t.getLocalizedMessage());
        sb.append(StringUtils.LINE_SEPARATOR);
        sb.append('[');
        sb.append(t2.getClass().getName());
        sb.append(' ');
        if (t2 instanceof SQLException) {
            sb.append(((SQLException) t2).getErrorCode());
            sb.append(' ');
            sb.append(((SQLException) t2).getMessage());
            sb.append(' ');
        }
        sb.append(StringUtils.LINE_SEPARATOR);

        StackTraceElement[] stes = null;
        Throwable ttmp = t;
        int count = 0;
        String throwname = null;
        String msg = null;
        StringBuffer causesb = new StringBuffer();
        causesb.append(StringUtils.LINE_SEPARATOR);
        while (null != ttmp) {
            stes = ttmp.getStackTrace();
            throwname = ttmp.getClass().getName();
            msg = ttmp.getLocalizedMessage();
            if (null == msg) {
                msg = ttmp.getMessage();
            }

            if (0 != count && null != msg && !"".equals(msg)) {
                causesb.append("Caused By: " + throwname + ": " + msg);
                causesb.append(StringUtils.LINE_SEPARATOR);
            } else if (1 <= count) {
                causesb.append("Caused By: " + throwname);
                causesb.append(StringUtils.LINE_SEPARATOR);
            }

            for (int i = 0; i < stes.length; i++) {
                count++;
                sb.append(" at ");
                sb.append(stes[i].toString());
                sb.append(StringUtils.LINE_SEPARATOR);
            }

            ttmp = ttmp.getCause();
        }

        sb.append(']');

        return causesb.append(sb).toString();
    }

    private static Throwable getDetailOfRemoteEx(RemoteException re) {
        Throwable ret = null;
        ret = re.detail;
        while (ret instanceof RemoteException) {
            ret = ((RemoteException) ret).detail;
        }
        return ret;
    }

    /**
     * 一次性判断多个或单个对象为空。
     *
     * @param objects
     * @author cai niao
     * @return 只要有一个元素为Blank，则返回true
     */
    public static boolean isBlank(Object... objects) {
        Boolean result = false;
        for (Object object : objects) {
            if (object == null || "".equals(object.toString().trim()) || "null".equals(object.toString().trim())
                    || "[null]".equals(object.toString().trim()) || "[]".equals(object.toString().trim())) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 一次性判断多个或单个对象不为空。
     *
     * @param objects
     * @author zhou-baicheng
     * @return 只要有一个元素不为Blank，则返回true
     */
    public static boolean isNotBlank(Object... objects) {
        return !isBlank(objects);
    }

    public static String checkNullToConvert(Object obj) {
        return StringUtils.isBlank(obj) ? "" : obj.toString();
    }

    /**
     * 判断一个字符串在数组中存在几个
     *
     * @param baseStr
     * @param strings
     * @return
     */
    public static int indexOf(String baseStr, String[] strings) {

        if (null == baseStr || baseStr.length() == 0 || null == strings)
            return 0;

        int i = 0;
        for (String string : strings) {
            boolean result = baseStr.equals(string);
            i = result ? ++i : i;
        }
        return i;
    }

    public static String trimToEmpty(Object str) {
        return (isBlank(str) ? "" : str.toString().trim());
    }

    /**
     * 转换成Unicode
     *
     * @param str
     * @return
     */
    public static String toUnicode(String str) {
        String as[] = new String[str.length()];
        String s1 = "";
        for (int i = 0; i < str.length(); i++) {
            as[i] = Integer.toHexString(str.charAt(i) & 0xffff);
            s1 = s1 + "\\u" + as[i];
        }
        return s1;
    }

    /**
     * 替换字符串
     *
     * @param str
     * @param nowStr
     * @param replaceStr
     * @return
     */
    public static String replaceString(String str, String nowStr, String replaceStr) {

        nowStr = StringUtils.isBlank(nowStr) ? "" : nowStr;
        replaceStr = StringUtils.isBlank(replaceStr) ? "" : replaceStr;

        if (StringUtils.isNotBlank(str)) {

            return str.replaceAll(nowStr, replaceStr);
        }
        return "";
    }

    public static  boolean  isEqule(Object a,Object b) {
        if (StringUtils.isBlank(a)) {
            return false;
        }
        if (StringUtils.isBlank(b)) {
            return false;
        }
        if(a.toString()==b.toString()||a.toString().equals(b.toString())) {
            return true;
        }
        return false;
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static String trimToNull(String str) {
        String st = trim(str);
        return isNullString(st) ? null : st;
    }

    public static String trimToEmpty(String str) {
        return str == null ? EMPTY : str.trim();
    }
}
