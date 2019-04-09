package Log4jTest;


import org.apache.log4j.Logger;
import org.junit.Test;

public class ClientTest {

    @Test
    public void testSocketAppender() throws Exception {
        Logger logger = Logger.getLogger("aAaAaAa");
        Logger barLogger = Logger.getLogger("bBbBbB");
        for (int i = 0; i < 10; i++) {
            logger.warn("logger warn");
            logger.debug("logger debug");
            barLogger.info("bar logger info");
            barLogger.debug("bar logger debug long long ");
        }

    }
}
