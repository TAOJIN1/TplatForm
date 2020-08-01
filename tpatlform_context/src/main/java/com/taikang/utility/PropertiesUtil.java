package com.taikang.utility;

import com.taikang.encryptionrelated.SecurityHelper;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.*;

/**
 * 1
 * 2 * @Author: Lanvo
 * 3 * @Date: 2020/8/1 12:17
 * 4
 */
public final class PropertiesUtil extends PropertyPlaceholderConfigurer {
    private static final byte[] KEY = { 9, -1, 0, 5, 39, 8, 6, 19 };
    private static Map<String, String> ctxPropertiesMap;
    private List<String> decryptProperties;

    @Override
    protected void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
        ctxPropertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            if (decryptProperties != null && decryptProperties.contains(keyStr)) {
                value = SecurityHelper.decryptDes(value, KEY);
                props.setProperty(keyStr, value);
            }
            ctxPropertiesMap.put(keyStr, value);
        }
    }

    /**
     * @param decryptPropertiesMap
     *            the decryptPropertiesMap to set
     */
    public void setDecryptProperties(List<String> decryptProperties) {
        this.decryptProperties = decryptProperties;
    }

    /**
     * Get a value based on key , if key does not exist , null is returned
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        try {
            return ctxPropertiesMap.get(key);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    /**
     * Get a value based on key , if key does not exist , null is returned
     *
     * @param key
     * @return
     */
    public static String getString(String key, String defaultValue) {
        try {
            String value = ctxPropertiesMap.get(key);
            if (DataConversionUtils.isEmpty(value)) {
                return defaultValue;
            }
            return value;
        } catch (MissingResourceException e) {
            return defaultValue;
        }
    }

    /**
     * 根据key获取值
     *
     * @param key
     * @return
     */
    public static int getInt(String key) {
        return Integer.parseInt(ctxPropertiesMap.get(key));
    }

    /**
     * 根据key获取值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(String key, int defaultValue) {
        String value = ctxPropertiesMap.get(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    /**
     * 根据key获取值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = ctxPropertiesMap.get(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return new Boolean(value);
    }

    public static void main(String[] args) {
        String encrypt = SecurityHelper.encryptDes("buzhidao", KEY);
        System.out.println(encrypt);
        System.out.println(SecurityHelper.decryptDes(encrypt, KEY));
    }
}
