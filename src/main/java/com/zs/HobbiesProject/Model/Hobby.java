package com.zs.HobbiesProject.Model;

import java.util.Date;

/**
 * This is the parent class of all other hobbies class.
 */
public class Hobby {

    private Date startTime = null;
    private Date endTime = null;

    private Date tickDate;


    public void setTickDate(Date tickDate) {
        this.tickDate = tickDate;
    }

    public Date getTickDate() {
        return tickDate;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Hobby() {

    }






    /**
     * @return return start time.
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @return Returning end time.
     */
    public Date getEndTime() {
        return endTime;
    }

}
