package by.matsukiryna.xmltask.builder;

import by.matsukiryna.xmltask.entity.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class XmlExpectedDataTest {
    public Set<AbstractPaper> papers = new HashSet<>();
    private PaperProperties paperProperties = new PaperProperties(1503, 100, false,3.50,
            LocalDate.of(2021, 06, 16));

    XmlExpectedDataTest() {
        papers.add(new Newspaper("Аиф", "P01", AgeCategory.ALL, 120000, paperProperties, " i13137", true, Frequency.DAILY));

        papers.add(new Newspaper("СБ", "P02", AgeCategory.ADULT, 25000, paperProperties, "i12032", false, Frequency.WEEKLY));

        papers.add(new Magazine("Doctor", "P03", AgeCategory.ALL, 43000, paperProperties, "i20056", true, Direction.MEDICINE, Frequency.MONTHLY));
    }
}
