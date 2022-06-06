package test;

import org.apache.log4j.Logger;

public class Test_log4j {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Test_log4j.class);

        logger.info("AAAAA");
        logger.debug("BBBBB");
        logger.warn("CCCCC");
        logger.error("DDDDD");
        logger.fatal("EEEEE");

    }
}
