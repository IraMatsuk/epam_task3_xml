package by.matsukiryna.xmltask.handler;

import by.matsukiryna.xmltask.entity.AbstractPaper;
import by.matsukiryna.xmltask.entity.Booklet;
import by.matsukiryna.xmltask.entity.Magazine;
import by.matsukiryna.xmltask.entity.Newspaper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static by.matsukiryna.xmltask.handler.PaperXmlTag.*;

public class PaperHandler extends DefaultHandler {
    private static Logger logger = LogManager.getLogger();
    private Set<AbstractPaper> papers;
    private AbstractPaper currentPaper;
    private PaperXmlTag currentXmlTag;
    private EnumSet<PaperXmlTag> withText;
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";

    public PaperHandler() {
        papers = new HashSet<>();
        withText = EnumSet.range(TITLE, DIRECTION);
    }

    public Set<AbstractPaper> getPapers() {
        return papers;
    }

    @Override
    public void startDocument() {
        logger.log(Level.INFO, "SAX parsing has been started");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (NEWSPAPER.getValue().equals(qName) || MAGAZINE.getValue().equals(qName) || BOOKLET.getValue().equals(qName)) {
            currentXmlTag = valueOf(qName.toUpperCase());

            switch (currentXmlTag) {
                case NEWSPAPER:
                    currentPaper = new Newspaper();
                    break;
                case MAGAZINE:
                    currentPaper = new Magazine();
                    break;
                case BOOKLET:
                    currentPaper = new Booklet();
                    break;
            }
            currentXmlTag = null;
            currentPaper.setId(attributes.getValue(ID.getValue()));
            currentPaper.setAgeCategory(attributes.getValue(AGE_CATEGORY.getValue()));
        } else {
            PaperXmlTag temp = valueOf(qName.toUpperCase().replace(HYPHEN, UNDERSCORE));
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case TITLE:
                    currentPaper.setTitle(data);
                    break;
                case ID:
                    currentPaper.setId(data);
                    break;
                case AGE_CATEGORY:
                    currentPaper.setAgeCategory(data);
                    break;
                case ISSUE:
                    currentPaper.getPaperProperties().setIssue(Integer.parseInt(data));
                    break;
                case PAGES:
                    currentPaper.getPaperProperties().setPages(Integer.parseInt(data));
                    break;
                case GLOSSY:
                    currentPaper.getPaperProperties().setGlossy(Boolean.parseBoolean(data));
                    break;
                case PRICE:
                    currentPaper.getPaperProperties().setPrice(Double.parseDouble(data));
                    break;
                case ISSUE_DATE:
                    currentPaper.getPaperProperties().setIssueDate(parseStringToLocalDate(data));
                    break;
                case CIRCULATION:
                    currentPaper.setCirculation(Integer.parseInt(data));
                    break;
                case COLOR:
                     currentPaper.setColor(Boolean.parseBoolean(data));
                    break;
                case FREQUENCY:
                    currentPaper.setFrequency(data);
                    break;
                case DIRECTION:
                    if (currentXmlTag == MAGAZINE) {
                        ((Magazine) currentPaper).setDirection(data);
                    }
                    break;
                case SUBSCRIPTION_INDEX:
                    if (currentXmlTag == NEWSPAPER) {
                        ((Newspaper) currentPaper).setSubscriptionIndex(data);
                    } else if (currentXmlTag == MAGAZINE) {
                        ((Magazine) currentPaper).setSubscriptionIndex(data);
                    }
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }

    @Override
    public void endElement(String uri, String localName, String qTitle) {
        if (NEWSPAPER.getValue().equals(qTitle) || MAGAZINE.getValue().equals(qTitle) || BOOKLET.getValue().equals(qTitle)) {
            papers.add(currentPaper);
        }
    }

    @Override
    public void endDocument() {
        logger.log(Level.INFO, "SAX parsing was successfully");
    }

    private LocalDate parseStringToLocalDate(String data) {
        DateTimeFormatter dateTimeFormatter;
        LocalDate date;
        dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        date = LocalDate.parse(data, dateTimeFormatter);
        return date;
    }
}
