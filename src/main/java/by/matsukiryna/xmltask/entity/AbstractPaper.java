package by.matsukiryna.xmltask.entity;

public abstract class AbstractPaper {
    private String title;
    private String id;
    private AgeCategory ageCategory;
    private int circulation;
    private boolean color;
    private PaperProperties paperProperties = new PaperProperties();
    private Frequency frequency;

    public AbstractPaper() {
    }

    public AbstractPaper(String title, String id, AgeCategory ageCategory,
                         int circulation, boolean color, PaperProperties paperProperties, Frequency frequency) {
        this.title = title;
        this.id = id;
        this.ageCategory = ageCategory;
        this.circulation = circulation;
        this.color = color;
        this.paperProperties = paperProperties;
        this.frequency = frequency;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }

    public void setAgeCategory(String ageCategory) {
        this.ageCategory = AgeCategory.valueOf(ageCategory.toUpperCase());
    }

    public int getCirculation() {
        return circulation;
    }

    public void setCirculation(int circulation) {
        this.circulation = circulation;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public PaperProperties getPaperProperties() {
        return paperProperties;
    }

    public void setPaperProperties(PaperProperties paperProperties) {
        this.paperProperties = paperProperties;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = Frequency.valueOf(frequency.toUpperCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPaper that = (AbstractPaper) o;
        if (circulation != that.circulation) return false;
        if (color != that.color) return false;
        if (title != title) return false;
        if (id != that.id) return false;
        if (ageCategory != that.ageCategory) return false;
        if (frequency != that.frequency) return false;
        return paperProperties == that.paperProperties;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (ageCategory != null ? ageCategory.hashCode() : 0);
        result = 31 * result + circulation;
        result = 31 * result + (color ? 1 : 0);
        result = 31 * result + (paperProperties != null ? paperProperties.hashCode() : 0);
        result = 31 * result + (frequency != null ? frequency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nTitle: ").append(title)
                .append("\nID: ").append(id)
                .append("\nAgeCategory: ").append(ageCategory)
                .append("\nCirculation: ").append(circulation);
        if (color) {
            sb.append("\nColor: Yes");
        } else {
            sb.append("\nColor: No");
        }
        sb.append("\nPaperProperties: ").append(paperProperties)
                .append("\nFrequency: ").append(frequency);
        return sb.toString();
    }
}
