package Report.ReportWriters;

import org.slf4j.LoggerFactory;

public class Logger {
    private static Logger logger;
    private static org.slf4j.Logger loggerWriter;

    private Logger() {
        loggerWriter = LoggerFactory.getLogger(Logger.class);
    }

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void error(String massage) {
        loggerWriter.error(massage);
    }

    public void info(String massage) {
        loggerWriter.warn(massage);

    }

    public void warn(String massage) {
        loggerWriter.warn(massage);
    }

}
