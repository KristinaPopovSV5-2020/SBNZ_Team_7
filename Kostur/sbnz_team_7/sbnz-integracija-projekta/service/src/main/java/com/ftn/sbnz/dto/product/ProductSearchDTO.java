package com.ftn.sbnz.dto.product;

public class ProductSearchDTO {
    private String typeCategory;
    private String ageCategory;
    private String usageCategory;


    public String getTypeCategory() {
        return typeCategory;
    }

    public void setTypeCategory(String typeCategory) {
        this.typeCategory = typeCategory;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
    }

    public String getUsageCategory() {
        return usageCategory;
    }

    public void setUsageCategory(String usageCategory) {
        this.usageCategory = usageCategory;
    }

    public ProductSearchDTO(String typeCategory, String ageCategory, String usageCategory) {
        this.typeCategory = typeCategory;
        this.ageCategory = ageCategory;
        this.usageCategory = usageCategory;
    }
    public ProductSearchDTO() {
    }

}
