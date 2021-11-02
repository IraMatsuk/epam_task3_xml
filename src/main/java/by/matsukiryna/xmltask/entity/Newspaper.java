package by.matsukiryna.xmltask.entity;

public class Newspaper extends AbstractPaper {
    private String subscriptionIndex;

    public Newspaper() {
    }

    public Newspaper(String title, String id, AgeCategory ageCategory,
                     int circulation, PaperProperties paperProperties, String subscriptionIndex, boolean color, Frequency frequency) {
        super(title, id, ageCategory, circulation, color, paperProperties, frequency);
        this.subscriptionIndex = subscriptionIndex;
    }

    public String getSubscriptionIndex() {
        return subscriptionIndex;
    }

    public void setSubscriptionIndex(String subscriptionIndex) {
        this.subscriptionIndex = subscriptionIndex;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Newspaper newspaper = (Newspaper) o;
        return subscriptionIndex == newspaper.subscriptionIndex;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (subscriptionIndex != null ? subscriptionIndex.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder newspaper = new StringBuilder();
        newspaper.append(super.toString())
                 .append("\nSubscription index: ").append(subscriptionIndex);
        return newspaper.toString();
    }
}
