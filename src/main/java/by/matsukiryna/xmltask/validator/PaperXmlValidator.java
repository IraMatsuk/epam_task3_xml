package by.matsukiryna.xmltask.validator;

import by.matsukiryna.xmltask.exception.XmlException;
import by.matsukiryna.xmltask.handler.PaperErrorHandler;
import by.matsukiryna.xmltask.util.ResourceFile;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class PaperXmlValidator {
    private static Logger logger = LogManager.getLogger();
    private static final String SCHEMA_NAME = "schema.xsd";

    public static boolean validatePaperXml(String fileName) throws XmlException {
        boolean isXmlRight = false;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        try {
            SchemaFactory factory = SchemaFactory.newInstance(language);
            ResourceFile resourceFile = new ResourceFile();
            String xmlFilePath = resourceFile.getPath(SCHEMA_NAME);
            File schemaLocation = new File(xmlFilePath);
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.setErrorHandler(new PaperErrorHandler());
            validator.validate(source);
            isXmlRight = true;
            logger.log(Level.DEBUG, "File is valid");
        } catch (SAXException e) {
            logger.log(Level.ERROR, "Incorrect file", fileName);
        } catch (IOException e) {
            logger.log(Level.ERROR, "File can't be read", fileName);
        }
        return isXmlRight;
    }
}
