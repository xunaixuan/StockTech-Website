/*
 * 系统名称：新闻发布系统
 * 
 * 类名：Logger
 * 
 * 创建日期：2014-06-18
 */
package org.news.utils;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Log完全可以分装一个类，而不必每个类要log
 * @author weip
 * @time 2006-4-30 17:24:49
 *
 */
public class Logger {

    /**
     * 初始LOG
     */
    private static Log sysLogger = LogFactory.getLog(Logger.class);
    /**
     * 调试
     */
    public static final String DEBUG = "debug";

    /**
     * 信息
     */
    public static final String INFO = "info";

    /**
     * 警告
     */
    public static final String WARN = "warn";

    /**
     * 错误
     */
    public static final String ERROR = "error";

    /**
     * 故障
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
