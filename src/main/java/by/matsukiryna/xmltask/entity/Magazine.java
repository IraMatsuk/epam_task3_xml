package by.matsukiryna.xmltask.entity;

import java.util.StringJoiner;

public class Magazine extends AbstractPaper {
    private Direction direction;

    public Magazine() {

    }

    public Magazine(String title, String subscriptionIndex, String website, AgeCategory ageCategory,
                    int circulation, PaperProperties paperProperties, Direction direction) {
        super(title, subscriptionIndex, website, ageCategory, circulation, paperProperties);
        this.direction = direction;
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
        if (!(o instanceof Magazine)) return false;
        if (!super.equals(o)) return false;

        Magazine magazine = (Magazine) o;

        return direction == magazine.direction;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (direction != null ? direction.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Magazine.class.getSimpleName() + "[", "]")
               // .add(super.toString())
                .add("direction=" + direction)
                .toString();
    }
}
