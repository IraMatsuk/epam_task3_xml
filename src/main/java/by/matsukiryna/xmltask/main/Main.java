package by.matsukiryna.xmltask.main;

import by.matsukiryna.xmltask.exception.XmlException;
import by.matsukiryna.xmltask.parser.*;
import by.matsukiryna.xmltask.util.ResourceFile;
import by.matsukiryna.xmltask.validator.PaperXmlValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        String xmlFileName = "papers.xml";
        String type = "dom";
        try {
            ResourceFile resourceFile = new ResourceFile();
            String xmlFilePath = resourceFile.getPath(xmlFileName);
            PaperXmlValidator.validatePaperXml(xmlFilePath);
            AbstractPaperBuilder builder = PaperBuilderFactory.createPaperBuilder(type);
            builder.buildSetPapers(xmlFilePath);
            logger.log(Level.INFO, builder.getPapers());
            PapersSaxBuilder papersSaxBuilder = new PapersSaxBuilder();
            papersSaxBuilder.buildSetPapers(xmlFilePath);
            logger.log(Level.INFO, builder.getPapers());
            PapersDomBuilder papersDomBuilder = new PapersDomBuilder();
            papersDomBuilder.buildSetPapers(xmlFilePath);
            PapersStaxBuilder papersStaxBuilder = new PapersStaxBuilder();
            papersStaxBuilder.buildSetPapers(xmlFilePath);
            logger.log(Level.INFO, builder.getPapers());
        } catch (XmlException e) {
            e.printStackTrace();
        }
    }
}
