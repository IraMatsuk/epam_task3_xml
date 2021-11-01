package by.matsukiryna.xmltask.exception;

public class XmlException extends Exception {
    public XmlException() {
    }

    public XmlException(String message) {
        super(message);
    }

    public XmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlException(Throwable cause) {
        super(cause);
    }
}
