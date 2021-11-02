package by.matsukiryna.xmltask.parser;

import by.matsukiryna.xmltask.entity.AbstractPaper;
import by.matsukiryna.xmltask.exception.XmlException;
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
        builder.buildSetPapers("resources/test_data/papers.xml");
        Set<AbstractPaper> actual = builder.getPapers();
        Set<AbstractPaper> expected = new XmlExpectedDataTest().papers;
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = XmlException.class, dataProvider = "parsers")
    public void testBuildSetPapersWhenFileIsEmpty(AbstractPaperBuilder builder) throws XmlException {
        builder.buildSetPapers("resources/test_data/empty.xml");
    }

    @Test(expectedExceptions = XmlException.class, dataProvider = "parsers")
    public void testBuildSetPapersWhenFileIsNull(AbstractPaperBuilder builder) throws XmlException {
        builder.buildSetPapers("resources/test_data/null.xml");
    }

    @Test(expectedExceptions = XmlException.class, dataProvider = "parsers")
    public void testBuildSetPapersWhenFileIsNotWellFormed(AbstractPaperBuilder builder) throws XmlException {
        builder.buildSetPapers("resources/test_data/not_well_formed.xml");
    }
}