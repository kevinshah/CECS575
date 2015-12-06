package com.example.kevin.unisalestorm;

/**
 * Created by Kevin on 12/3/15.
 */
public class Refrigerator extends Electronics {

    String dateOfMAnuFacturing;
    String yearsUsed;

    public Refrigerator(String dateOfMAnuFacturing) {
        this.dateOfMAnuFacturing = dateOfMAnuFacturing;
    }

    public String getDateOfMAnuFacturing() {
        return dateOfMAnuFacturing;
    }

    public void setDateOfMAnuFacturing(String dateOfMAnuFacturing) {
        this.dateOfMAnuFacturing = dateOfMAnuFacturing;
    }

    public String getYearsUsed() {
        return yearsUsed;
    }

    public void setYearsUsed(String yearsUsed) {
        this.yearsUsed = yearsUsed;
    }
}
