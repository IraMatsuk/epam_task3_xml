package by.matsukiryna.xmltask.parser;

import by.matsukiryna.xmltask.entity.AbstractPaper;
import by.matsukiryna.xmltask.entity.Booklet;
import by.matsukiryna.xmltask.entity.Magazine;
import by.matsukiryna.xmltask.entity.Newspaper;
import by.matsukiryna.xmltask.exception.XmlException;
import by.matsukiryna.xmltask.validator.PaperXmlValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static by.matsukiryna.xmltask.handler.PaperXmlTag.*;

public class DOMPaperParser extends AbstractPaperBuilder {
    private static Logger logger = LogManager.getLogger();
    private DocumentBuilder documentBuilder;

    public DOMPaperParser() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, "DocumentBuilder cannot be created which satisfies the configuration requested");
        }
    }

    public void buildSetPapers(String fileName) throws XmlException {
        if (!PaperXmlValidator.validatePaperXml(fileName)) {
            throw new XmlException(String.format("File %s hasn't passed validation!", fileName));
        }

        Document document;
        try {
            logger.log(Level.INFO, "DOM parsing has started");
            document = documentBuilder.parse(fileName);
            Element root = document.getDocumentElement();
            NodeList newspapersList = root.getElementsByTagName(NEWSPAPER.getValue());
            for (int i = 0; i < newspapersList.getLength(); i++) {
                Element newspaperElement = (Element) newspapersList.item(i);
                AbstractPaper newspaper = buildPaper(newspaperElement);
                papers.add(newspaper);
            }

            NodeList magazineList = root.getElementsByTagName(MAGAZINE.getValue());
            for (int i = 0; i < magazineList.getLength(); i++) {
                Element magazineElement = (Element) magazineList.item(i);
                AbstractPaper magazine = buildPaper(magazineElement);
                papers.add(magazine);
            }

            NodeList bookletList = root.getElementsByTagName(BOOKLET.getValue());
            for (int i = 0; i < bookletList.getLength(); i++) {
                Element bookletElement = (Element) bookletList.item(i);
                AbstractPaper booklet = buildPaper(bookletElement);
                papers.add(booklet);
            }

        } catch (IOException | SAXException e) {
            logger.log(Level.ERROR, "Any SAX or IO Exception during parsing {}", fileName);
        }

        logger.log(Level.INFO, "DOM parsing has finished successfully");
    }

    private AbstractPaper buildPaper(Element paperElement) throws XmlException {
        String tempText;
        AbstractPaper paper;

        if (paperElement.getTagName().equals(NEWSPAPER.getValue())) {
            paper = new Newspaper();
            ((Newspaper) paper).setSubscriptionIndex(getElementTextContent(paperElement, SUBSCRIPTION_INDEX.getValue()));
        } else if (paperElement.getTagName().equals(MAGAZINE.getValue())) {
            paper = new Magazine();
            ((Magazine) paper).setSubscriptionIndex(getElementTextContent(paperElement, SUBSCRIPTION_INDEX.getValue()));
            tempText = getElementTextContent(paperElement, DIRECTION.getValue());
            ((Magazine)paper).setDirection(tempText);
        } else if (paperElement.getTagName().equals(BOOKLET.getValue())) {
            paper = new Booklet();
        } else {
            throw new XmlException("Unreachable exception");
        }

        paper.setId(paperElement.getAttribute(ID.getValue()));
        tempText = paperElement.getAttribute(AGE_CATEGORY.getValue());
        if (!tempText.isEmpty()) {
            paper.setAgeCategory(tempText);
        }

        paper.setTitle(getElementTextContent(paperElement, TITLE.getValue()));
        tempText = getElementTextContent(paperElement, ISSUE.getValue());
        paper.getPaperProperties().setIssue(Integer.parseInt(tempText));
        tempText = getElementTextContent(paperElement, PAGES.getValue());
        paper.getPaperProperties().setPages(Integer.parseInt(tempText));
        tempText = getElementTextContent(paperElement, GLOSSY.getValue());
        paper.getPaperProperties().setGlossy(Boolean.parseBoolean(tempText));
        tempText = getElementTextContent(paperElement, PRICE.getValue());
        paper.getPaperProperties().setPrice(Double.parseDouble(tempText));
        tempText = getElementTextContent(paperElement, ISSUE_DATE.getValue());
        paper.getPaperProperties().setIssueDate(parseStringToLocalDate(tempText));
        tempText = getElementTextContent(paperElement, CIRCULATION.getValue());
        paper.setCirculation(Integer.parseInt(tempText));
        tempText = getElementTextContent(paperElement, COLOR.getValue());
        paper.setColor(Boolean.parseBoolean(tempText));
        tempText = getElementTextContent(paperElement, FREQUENCY.getValue());
        paper.setFrequency(tempText);
        return paper;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        String text = "";
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            text = node.getTextContent();
        }
        return text;
    }
}
