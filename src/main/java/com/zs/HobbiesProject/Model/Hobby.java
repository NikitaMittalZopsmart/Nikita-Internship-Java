package com.zs.HobbiesProject.Model;

import java.util.Date;

/**
 * This is the parent class of all other hobbies class.
 */
public class Hobby {

    private Date startTime = null;
    private Date endTime = null;
    private Date tickDate;

    /**
     * This function is setting the date of hobby.
     * @param tickDate Date
     */
    public void setTickDate(Date tickDate) {
        this.tickDate = tickDate;
    }

    /**
     * This function is returning the date.
     * @return
     */
    public Date getTickDate() {
        return tickDate;
    }

    /**
     * This function is setting the end time for hobby.
     * @param endTime End time.
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This Function is setting the start time.
     * @param startTime Start Time.
     */
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
