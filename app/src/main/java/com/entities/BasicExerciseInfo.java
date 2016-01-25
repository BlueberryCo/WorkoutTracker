package com.entities;

/**
 * Created by boiko on 25-Jan-16.
 */
public class BasicExerciseInfo {

    private String bgTranslation;
    private String enTranslation;
    private CategoryInfo category;


    public BasicExerciseInfo(String bgTranslation, String enTranslation, CategoryInfo category){
        this.bgTranslation = bgTranslation;
        this.enTranslation = enTranslation;
        this.category = category;
    }


    public String getBgTranslation() {
        return bgTranslation;
    }

    public void setBgTranslation(String bgTranslation) {
        this.bgTranslation = bgTranslation;
    }

    public String getEnTranslation() {
        return enTranslation;
    }

    public void setEnTranslation(String enTranslation) {
        this.enTranslation = enTranslation;
    }

    public CategoryInfo getCategory(){
        return this.category;
    }

    public void setCategory(CategoryInfo category){
        this.category = category;
    }
}
