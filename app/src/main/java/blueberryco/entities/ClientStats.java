package blueberryco.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by boiko on 12/1/2015.
 */
public class ClientStats implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private int clientId;
    private Float weight;
    private Integer maxBackSquat;
    private Integer maxFrontSquat;
    private Integer maxDeadlift;
    private Integer maxBenchpress;
    private Integer maxShoulderspress;
    private Date date;

    public ClientStats(){
        date = new Date();
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public int getClientId(){
        return this.clientId;
    }

    public void setClientId(int clientId){
        this.clientId = clientId;
    }

    public Float getWeight(){
        return this.weight;
    }

    public void setWeight(Float weight){
        this.weight = weight;
    }

    public Integer getMaxBackSquat(){
        return this.maxBackSquat;
    }

    public void setMaxBackSquat(Integer maxBackSquat){
        this.maxBackSquat = maxBackSquat;
    }

    public Integer getMaxFrontSquat(){
        return this.maxFrontSquat;
    }

    public void setMaxFrontSquat(Integer maxFrontSquat){
        this.maxFrontSquat = maxFrontSquat;
    }

    public Integer getMaxDeadlift(){
        return this.maxDeadlift;
    }

    public void setMaxDeadlift(Integer maxDeadlift){
        this.maxDeadlift = maxDeadlift;
    }

    public Integer getMaxBenchpress(){
        return this.maxBenchpress;
    }

    public void setMaxBenchpress(Integer maxBenchpress){
        this.maxBenchpress = maxBenchpress;
    }

    public Integer getMaxShoulderspress(){
        return this.maxShoulderspress;
    }

    public void setMaxShoulderspress(Integer maxShoulderspress){
        this.maxShoulderspress = maxShoulderspress;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
