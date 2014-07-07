/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������Logger
 * 
 * �������ڣ�2014-06-18
 */
package org.news.utils;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Log��ȫ���Է�װһ���࣬������ÿ����Ҫlog
 * @author weip
 * @time 2006-4-30 17:24:49
 *
 */
public class Logger {

    /**
     * ��ʼLOG
     */
    private static Log sysLogger = LogFactory.getLog(Logger.class);
    /**
     * ����
     */
    public static final String DEBUG = "debug";

    /**
     * ��Ϣ
     */
    public static final String INFO = "info";

    /**
     * ����
     */
    public static final String WARN = "warn";

    /**
     * ����
     */
    public static final String ERROR = "error";

    /**
     * ����
     */
    public static final String FATAL = "fatal";
    
    /**
     * generic method instead of the five methods above
     * 
     * @author weip
     * @time 2006-4-30 16:51:31
     * @param obj
     *            Object
     * @param level
     *            String
     * 
     */
    public static void log(Object obj, String level) {

        Method method;
        try {
            method = sysLogger.getClass().getMethod(level,
                    new Class[] {Object.class });

            method.invoke(sysLogger, new Object[] {obj });
        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("It should be a unreachalbe point");
        }
    }
}
