package blueberryco.entities;

/**
 * Created by boiko on 25-Jan-16.
 */
public class CategoryInfo {

    private String bgTranslation;
    private String enTranslation;


    public CategoryInfo(String bgTranslation, String enTranslation){
        this.bgTranslation = bgTranslation;
        this.enTranslation = enTranslation;
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
}
