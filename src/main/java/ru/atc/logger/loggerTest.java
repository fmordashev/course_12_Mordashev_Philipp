package ru.atc.logger;

import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

public class loggerTest {


    public static void main(String[] args) {
        //Так принято
        Logger logger = LoggerFactory.getLogger(loggerTest.class);

        logger.trace("Hello World");
        logger.debug("Hello World");
        logger.info("Hello World");
        logger.warn("Hello World");
        logger.error("Hello World");
    }
}

