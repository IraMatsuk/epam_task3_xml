package by.matsukiryna.xmltask.entity;

public enum AgeCategory {
    CHILD,
    ADULT,
    ALL;

    @Override
    public String toString() {
        char firstLetter = name().charAt(0);
        StringBuilder string = new StringBuilder(name().toLowerCase());
        string.deleteCharAt(0).insert(0, firstLetter);
        return string.toString();
    }
}
