package com.epam.model.entities.enums;

public enum SubscriptionPeriod {
    ONE_MONTH("One month", 1),
    THREE_MONTHS("Three months", 3),
    SIX_MONTHS("Six months", 6),
    ONE_YEAR("One year", 12);

    private int number;
    private String periodDescription;

    SubscriptionPeriod(String periodDescription, int number) {
        this.number = number;
        this.periodDescription = periodDescription;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPeriodDescription() {
        return periodDescription;
    }

    public void setPeriodDescription(String periodDescription) {
        this.periodDescription = periodDescription;
    }

    @Override
    public String toString() {
        return "SubscriptionPeriod{" +
                "number=" + number +
                '}';
    }
}
