package blueberryco.entities;

/**
 * Created by boiko on 25-Jan-16.
 */
public class BasicExerciseInfo implements Comparable<BasicExerciseInfo> {

    private int id;
    private String bgTranslation;
    private String enTranslation;
    private CategoryInfo category;


    public BasicExerciseInfo(int id, String bgTranslation, String enTranslation, CategoryInfo category){
        this.id = id;
        this.bgTranslation = bgTranslation;
        this.enTranslation = enTranslation;
        this.category = category;
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

    public CategoryInfo getCategory(){
        return this.category;
    }

    public void setCategory(CategoryInfo category){
        this.category = category;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof BasicExerciseInfo){
            if(((BasicExerciseInfo)obj).getId() == this.id){
                return true;
            }
        }

        return false;
    }

    @Override
    public int compareTo(BasicExerciseInfo another){
        if(this.equals(another)){
            return 0;
        }

        if(this.id < another.getId()){
            return -1;
        }

        return 1;
    }
}
