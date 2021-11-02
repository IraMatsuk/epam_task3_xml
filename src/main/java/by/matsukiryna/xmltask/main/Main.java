package by.matsukiryna.xmltask.main;

import by.matsukiryna.xmltask.exception.XmlException;
import by.matsukiryna.xmltask.parser.*;
import by.matsukiryna.xmltask.validator.PaperXmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        String xmlFile = "src/main/resources/data/papers.xml";
        String type = "dom";
        try {
            PaperXmlValidator.validatePaperXml(xmlFile);
            AbstractPaperBuilder builder = PaperBuilderFactory.createPaperBuilder(type);
            builder.buildSetPapers(xmlFile);
            logger.info(builder.getPapers());

            SAXPaperParser saxPaperParser = new SAXPaperParser();
            saxPaperParser.buildSetPapers(xmlFile);
            DOMPaperParser domPaperParser = new DOMPaperParser();
            domPaperParser.buildSetPapers(xmlFile);
            StAXPaperParser stAXPaperParser = new StAXPaperParser();
            stAXPaperParser.buildSetPapers(xmlFile);
        } catch (XmlException e) {
            e.printStackTrace();
        }
    }
}
