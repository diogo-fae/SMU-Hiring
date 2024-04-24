package com.webappgroupg.SMUHiring.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class JobQualifications {
    private String jobId;
    private String company;
    private String category;
    private String keyword;
    // Dictionary with category as key and list of qualifications as value
    private Map<String, List<String>> qualifications;

    public JobQualifications(String jobId, String company, Map<String, List<String>> qualifications) {
        this.jobId = jobId;
        this.company = company;
        this.qualifications = qualifications;
    }
    public JobQualifications(String jobId, String company) {
        this.jobId = jobId;
        this.company = company;
        this.qualifications = new HashMap<>();
    }

    // Functions to edit the qualifications dictionary
    public void addQualification(String category, String qualification) {
        // If the category already exists, add the qualification to the list
        if (qualifications.containsKey(category)) {
            qualifications.get(category).add(qualification);
        } else {
            // If the category does not exist, add the category and create a new list with the qualification
            qualifications.put(category, new ArrayList<String>());
            qualifications.get(category).add(qualification);
        }
    }
    public void deleteQualification(String category, String qualification) {
        // If the category exists, remove the qualification from the list
        if (qualifications.containsKey(category)) {
            qualifications.get(category).remove(qualification);
            // If the category is empty, remove the category
            if (qualifications.get(category).isEmpty()) {
                qualifications.remove(category);
            }
        }
        else {
            // If the category does not exist, do nothing
            System.out.println("Category does not exist");
        }
    }
}
