package by.matsukiryna.xmltask.entity;

public class Booklet extends AbstractPaper {

    public Booklet() {

    }

    public Booklet(String title, String id, AgeCategory ageCategory,
                    int circulation, PaperProperties paperProperties, boolean color, Frequency frequency) {
        super(title, id, ageCategory, circulation, color, paperProperties, frequency);
    }

    @Override
    public String toString() {
        final StringBuilder booklet = new StringBuilder();
        booklet.append(super.toString());
        return booklet.toString();
    }
}
