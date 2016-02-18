package blueberryco.entities;

/**
 * Created by boiko on 25-Jan-16.
 */
public class CategoryInfo {

    private int id;
    private String bgTranslation;
    private String enTranslation;


    public CategoryInfo(int id, String bgTranslation, String enTranslation){
        this.id = id;
        this.bgTranslation = bgTranslation;
        this.enTranslation = enTranslation;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
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
