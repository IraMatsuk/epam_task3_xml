package by.matsukiryna.xmltask.builder;

import by.matsukiryna.xmltask.entity.AbstractPaper;
import by.matsukiryna.xmltask.exception.XmlException;
import by.matsukiryna.xmltask.factory.PaperBuilderFactory;
import by.matsukiryna.xmltask.util.ResourceFile;
import by.matsukiryna.xmltask.validator.PaperXmlValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SAXPaperParserTest {

    @DataProvider(name = "parsers")
    public Object[][] parsers() {
        Object[][] objects = {
                {PaperBuilderFactory.createPaperBuilder("dom")},
                {PaperBuilderFactory.createPaperBuilder("sax")},
                {PaperBuilderFactory.createPaperBuilder("stax")}
        };
        return objects;
    }

    @Test(dataProvider = "parsers")
    public void testBuildSetPapers(AbstractPaperBuilder builder) throws XmlException {
        String xmlFileName = "testdata/papers.xml";
        ResourceFile resourceFile = new ResourceFile();
        String xmlFilePath = resourceFile.getPath(xmlFileName);
        builder.buildSetPapers(xmlFilePath);
        Set<AbstractPaper> actual = builder.getPapers();
        Set<AbstractPaper> expected = new XmlExpectedDataTest().papers;
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = XmlException.class, dataProvider = "parsers")
    public void testBuildSetPapersWhenFileIsEmpty(AbstractPaperBuilder builder) throws XmlException {
        builder.buildSetPapers("testdata/empty.xml");
    }

    @Test(expectedExceptions = XmlException.class, dataProvider = "parsers")
    public void testBuildSetPapersWhenFileIsNull(AbstractPaperBuilder builder) throws XmlException {
        builder.buildSetPapers("testdata/null.xml");
    }

    @Test(expectedExceptions = XmlException.class, dataProvider = "parsers")
    public void testBuildSetPapersWhenFileIsNotWellFormed(AbstractPaperBuilder builder) throws XmlException {
        builder.buildSetPapers("testdata/not_well_formed.xml");
    }
}