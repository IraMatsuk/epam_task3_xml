package by.matsukiryna.xmltask.handler;

public enum PaperXmlTag {
    PAPERS("papers"),
    NEWSPAPER("newspaper"),
    TITLE("title"),
    ID("id"),
    SUBSCRIPTION_INDEX("subscription-index"),
    ISSUE("issue"),
    PAGES("pages"),
    GLOSSY("glossy"),
    PRICE("price"),
    ISSUE_DATE("issue-date"),
    CIRCULATION("circulation"),
    COLOR("color"),
    FREQUENCY("frequency"),
    DIRECTION("direction"),
    PAPER_PROPERTIES("paper-properties"),
    AGE_CATEGORY("age-category"),
    MAGAZINE("magazine"),
    BOOKLET("booklet");

    private String value;

    PaperXmlTag(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
