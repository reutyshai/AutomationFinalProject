package Report.ReportWriters;

import org.slf4j.LoggerFactory;

/**
 * A Singleton Logger class that wraps around the SLF4J Logger for logging messages
 * at different log levels: error, info, and warn.
 */
public class Logger {

    // Singleton instance of Logger
    private static Logger logger;

    // SLF4J logger instance for logging
    private static org.slf4j.Logger loggerWriter;

    /**
     * Private constructor to initialize the SLF4J logger instance.
     * This constructor is private to prevent direct instantiation.
     */
    private Logger() {
        loggerWriter = LoggerFactory.getLogger(Logger.class);
    }

    /**
     * Retrieves the singleton instance of the Logger.
     * If the instance does not exist, it creates one.
     *
     * @return the singleton Logger instance.
     */
    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    /**
     * Logs an error message at the error level.
     *
     * @param message - The error message to be logged.
     */
    public void error(String message) {
        loggerWriter.error(message);
    }

    /**
     * Logs an informational message at the warn level.
     * (Note: This method logs messages at the warn level rather than the info level.)
     *
     * @param message - The informational message to be logged.
     */
    public void info(String message) {
        loggerWriter.warn(message);
    }

    /**
     * Logs a warning message at the warn level.
     *
     * @param message - The warning message to be logged.
     */
    public void warn(String message) {
        loggerWriter.warn(message);
    }
}
