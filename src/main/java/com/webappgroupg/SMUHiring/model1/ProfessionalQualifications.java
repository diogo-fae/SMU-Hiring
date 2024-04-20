package com.webappgroupg.SMUHiring.model1;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ProfessionalQualifications {
    private String userId;
    private Map<String, List<String>> qualifications;

    public ProfessionalQualifications(String userId, Map<String, List<String>> qualifications) {
        this.userId = userId;
        this.qualifications = qualifications;
    }

    public ProfessionalQualifications(String userId) {
        this.userId = userId;
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
