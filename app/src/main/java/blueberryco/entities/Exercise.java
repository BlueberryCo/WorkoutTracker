package blueberryco.entities;

import java.io.Serializable;

/**
 * Created by boiko on 19-Jan-16.
 */
public class Exercise implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String exercise;
    private int clientWorkoutId;
    private String description;

    public Exercise(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public int getClientWorkoutId() {
        return clientWorkoutId;
    }

    public void setClientWorkoutId(int clientWorkoutId) {
        this.clientWorkoutId = clientWorkoutId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
