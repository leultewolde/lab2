package edu.miu.cs.cs489.model;

import java.time.LocalDate;

public record Employee(long employeeId, String firstName, String lastName, LocalDate employmentDate, double yearlySalary, PensionPlan pensionPlan) {

    public String toJSON() {
        String stringValue = String.format("\"employeeId\": %d, \"firstName\": %s, \"lastName\": %s, \"employmentDate\": %s, \"yearlySalary\": %f", employeeId, firstName, lastName, employmentDate, yearlySalary);
        stringValue = stringValue + ", " + pensionPlan;
        return "{" + stringValue + "}";
    }
}
