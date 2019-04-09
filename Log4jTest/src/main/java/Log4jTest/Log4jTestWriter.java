package Log4jTest;

import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.WriterAppender;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


/**
 * java log4j使用WriterAppender
 */
public class Log4jTestWriter {

    static Logger logger = Logger.getLogger(Log4jTestWriter.class);


    public static void main(String[] args) {
        HTMLLayout htmlLayout = new HTMLLayout();

        WriterAppender appender = null;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/outLog1.html");
            appender = new WriterAppender(htmlLayout,fileOutputStream);
            logger.addAppender(appender);
            logger.setLevel(Level.DEBUG);

            logger.debug("这些是一些debug东西！");
            logger.info("这些是一些info东西！！");
            logger.warn("这些是一些warn东西！！！");
            logger.error("这些是一些error东西！！！！");
            logger.fatal("这些是一些fatal东西！！！！！");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
