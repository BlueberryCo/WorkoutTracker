package com.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by boiko on 12/1/2015.
 */
public class ClientWorkout implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private int clientId;
    private Date date;
    private boolean isFinished;

    public ClientWorkout(){
        this.date = new Date();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }
}
