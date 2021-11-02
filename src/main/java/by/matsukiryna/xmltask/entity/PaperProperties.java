package by.matsukiryna.xmltask.entity;

import java.time.LocalDate;

public class PaperProperties {
    private int issue;
    private int pages;
    private boolean glossy;
    private double price;
    private LocalDate issueDate;

    public PaperProperties() {
    }

    public PaperProperties(int issue, int pages, boolean glossy, double price, LocalDate issueDate) {
        this.issue = issue;
        this.pages = pages;
        this.glossy = glossy;
        this.price = price;
        this.issueDate = issueDate;
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isGlossy() {
        return glossy;
    }

    public void setGlossy(boolean glossy) {
        this.glossy = glossy;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperProperties that = (PaperProperties) o;
        return issue == that.issue && pages == that.pages && glossy == that.glossy && Double.compare(that.price, price) == 0 && issueDate == that.issueDate;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = issue;
        result = 31 * result + pages;
        result = 31 * result + (glossy ? 1 : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder properties = new StringBuilder();
        properties.append("\n\tIssue: ").append(issue)
                  .append("\n\tPages: ").append(pages)
                  .append("\n\tGlossy: ").append(glossy)
                  .append("\n\tPrice: ").append(price)
                  .append("\n\tIssue date: ").append(issueDate);
        return properties.toString();
    }
}
