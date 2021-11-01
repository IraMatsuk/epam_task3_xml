package by.matsukiryna.xmltask.parser;

import by.matsukiryna.xmltask.entity.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class XmlExpectedDataTest {
    public Set<AbstractPaper> papers = new HashSet<>();
    private PaperProperties paperProperties = new PaperProperties(1503, 100, 3.50,
            LocalDate.of(2021, 06, 16));

    XmlExpectedDataTest() {
        papers.add(new Newspaper("Аиф", "i13137", "www.aif.by", AgeCategory.ALL,
                60000, paperProperties, true, Frequency.DAILY));

        papers.add(new Newspaper("СБ", "i00001", null, AgeCategory.ADULT, 25000,
                paperProperties, false, Frequency.WEEKLY));

        papers.add(new Magazine("Doctor", "i20056", null, AgeCategory.ALL,
                43000, paperProperties, Direction.MEDICINE));
    }
}
