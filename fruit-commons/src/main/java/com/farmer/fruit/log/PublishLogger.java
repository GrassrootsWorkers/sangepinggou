package com.farmer.fruit.log;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Desc: 记录发布日志
 */
public class PublishLogger
{
    private Logger logger;

    public PublishLogger(String className)
    {
        logger = Logger.getLogger(className);
    }

    public void log(Level level, String detail)
    {
        logger.log(level, detail);
    }

    public void log(String detail)
    {
        logger.log(Level.INFO, detail);
    }
}
