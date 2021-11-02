package by.matsukiryna.xmltask.entity;

public class Magazine extends AbstractPaper {
    private String subscriptionIndex;
    private Direction direction;

    public Magazine() {

    }

    public Magazine(String title, String id, AgeCategory ageCategory,
                    int circulation, PaperProperties paperProperties, String subscriptionIndex, boolean color, Direction direction, Frequency frequency) {
        super(title, id, ageCategory, circulation, color, paperProperties, frequency);
        this.subscriptionIndex = subscriptionIndex;
        this.direction = direction;
    }

    public String getSubscriptionIndex() {
        return subscriptionIndex;
    }

    public void setSubscriptionIndex(String subscriptionIndex) {
        this.subscriptionIndex = subscriptionIndex;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setDirection(String direction) {
        this.direction = Direction.valueOf(direction.toUpperCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Magazine magazine = (Magazine) o;
        return subscriptionIndex == magazine.subscriptionIndex && direction == magazine.direction;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (subscriptionIndex != null ? subscriptionIndex.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder magazine = new StringBuilder();
        magazine.append(super.toString());
        magazine.append("\nSubscriptionIndex: ").append(subscriptionIndex);
        magazine.append("\nDirection: ").append(direction);
        return magazine.toString();
    }
}