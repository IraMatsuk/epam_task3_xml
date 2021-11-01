package by.matsukiryna.xmltask.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class PaperErrorHandler implements ErrorHandler {
    private static Logger logger = LogManager.getLogger("by.matsukiryna.validator");

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        logger.warn(getLineColumnNumber(exception) + " - " + exception.getMessage());
        throw new SAXException(getLineColumnNumber(exception) + " - " + exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        logger.error(getLineColumnNumber(exception) + " - " + exception.getMessage());
        throw new SAXException(getLineColumnNumber(exception) + " - " + exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        logger.fatal(getLineColumnNumber(exception) + "-" + exception.getMessage());
        throw new SAXException(getLineColumnNumber(exception) + " - " + exception.getMessage());
    }

    private String getLineColumnNumber(SAXParseException exception) {
        // determine line and position of error
        return exception.getLineNumber() + " : " + exception.getColumnNumber();
    }
}
