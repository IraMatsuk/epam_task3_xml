package by.matsukiryna.xmltask.builder;

import by.matsukiryna.xmltask.entity.AbstractPaper;
import by.matsukiryna.xmltask.entity.Booklet;
import by.matsukiryna.xmltask.entity.Magazine;
import by.matsukiryna.xmltask.entity.Newspaper;
import by.matsukiryna.xmltask.exception.XmlException;
import by.matsukiryna.xmltask.handler.PaperXmlTag;
import by.matsukiryna.xmltask.validator.PaperXmlValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static by.matsukiryna.xmltask.handler.PaperXmlTag.*;

public class PapersStaxBuilder extends AbstractPaperBuilder {
    private static Logger logger = LogManager.getLogger();
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";
    private XMLInputFactory inputFactory;

    public PapersStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public void buildSetPapers(String fileName) throws XmlException {
        AbstractPaper paper = null;

        if (!PaperXmlValidator.validatePaperXml(fileName)) {
            throw new XmlException("File " + fileName + " hasn't passed validation!");
        }
        try {
            logger.log(Level.INFO, "StAX parsing has been started");
            XMLEventReader reader = inputFactory.createXMLEventReader(new FileInputStream(fileName));
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String textElement = startElement.getName().getLocalPart();
                    PaperXmlTag currentXmlTag;
                    currentXmlTag = PaperXmlTag.valueOf(textElement.toUpperCase().replace(HYPHEN, UNDERSCORE));
                    switch (currentXmlTag) {
                        case NEWSPAPER:
                            paper = new Newspaper();
                            Attribute id = startElement.getAttributeByName(new QName(ID.getValue()));
                            paper.setId(id.getValue());
                            Attribute ageCategory = startElement.getAttributeByName(new QName(AGE_CATEGORY.getValue()));
                            if (ageCategory == null) {
                                paper.setAgeCategory(ageCategory.getValue());
                            }
                            break;
                        case MAGAZINE:
                            paper = new Magazine();
                            id = startElement.getAttributeByName(new QName(ID.getValue()));
                            paper.setId(id.getValue());
                            ageCategory = startElement.getAttributeByName(new QName(AGE_CATEGORY.getValue()));
                            if (ageCategory == null) {
                                paper.setAgeCategory(ageCategory.getValue());
                            }
                            break;
                        case BOOKLET:
                            paper = new Booklet();
                            id = startElement.getAttributeByName(new QName(ID.getValue()));
                            paper.setId(id.getValue());
                            ageCategory = startElement.getAttributeByName(new QName(AGE_CATEGORY.getValue()));
                            if (ageCategory == null) {
                                paper.setAgeCategory(ageCategory.getValue());
                            }
                        case TITLE:
                            event = reader.nextEvent();
                            paper.setTitle(event.asCharacters().getData());
                            break;
                        case CIRCULATION:
                            event = reader.nextEvent();
                            paper.setCirculation(Integer.parseInt(event.asCharacters().getData()));
                            break;
                        case SUBSCRIPTION_INDEX:
                            event = reader.nextEvent();
                            if (event.equals(NEWSPAPER)) {
                                ((Newspaper) paper).setSubscriptionIndex(event.asCharacters().getData());
                            } else if (event.equals(MAGAZINE)) {
                                ((Magazine) paper).setSubscriptionIndex(event.asCharacters().getData());
                            }
                            break;
                        case COLOR:
                            event = reader.nextEvent();
                            paper.setColor(Boolean.parseBoolean(event.asCharacters().getData()));
                            break;
                        case FREQUENCY:
                            event = reader.nextEvent();
                            paper.setFrequency(event.asCharacters().getData());
                            break;
                        case DIRECTION:
                            event = reader.nextEvent();
                            ((Magazine) paper).setDirection(event.asCharacters().getData());
                            break;
                        default:
                            if (event.isStartElement()) {
                                StartElement startElementProperty = event.asStartElement();
                                textElement = startElementProperty.getName().getLocalPart();
                                currentXmlTag = PaperXmlTag.valueOf(textElement.toUpperCase()
                                        .replace(HYPHEN, UNDERSCORE));

                                switch (currentXmlTag) {
                                    case ISSUE:
                                        event = reader.nextEvent();
                                        paper.getPaperProperties()
                                                .setIssue(Integer.parseInt(event.asCharacters().getData()));
                                        break;
                                    case PAGES:
                                        event = reader.nextEvent();
                                        paper.getPaperProperties()
                                                .setPages(Integer.parseInt(event.asCharacters().getData()));
                                        break;
                                    case GLOSSY:
                                        event = reader.nextEvent();
                                        paper.getPaperProperties()
                                                .setGlossy(Boolean.parseBoolean(event.asCharacters().getData()));
                                        break;
                                    case PRICE:
                                        event = reader.nextEvent();
                                        paper.getPaperProperties()
                                                .setPrice(Double.parseDouble(event.asCharacters().getData()));
                                        break;
                                    case ISSUE_DATE:
                                        event = reader.nextEvent();
                                        paper.getPaperProperties()
                                                .setIssueDate(parseStringToLocalDate(event.asCharacters().getData()));
                                        break;
                                }
                            }
                    }
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    String textElement = endElement.getName().getLocalPart();
                    if (textElement.equals(NEWSPAPER.getValue()) || textElement.equals(MAGAZINE.getValue())
                            || textElement.equals(BOOKLET.getValue())) {
                        papers.add(paper);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            logger.log(Level.ERROR, "Error with the underlying XML ", fileName);
        }
        logger.log(Level.INFO, "StAX parsing was successfully");
    }
}
