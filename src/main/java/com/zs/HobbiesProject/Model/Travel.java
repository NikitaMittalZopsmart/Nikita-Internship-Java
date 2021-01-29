package com.zs.HobbiesProject.Model;

import java.util.Date;

/**
 * This class is created for the Travelling hobby.
 */
public class Travel extends Hobby {
    private float distance;
    private String startingPoint;
    private String endPoint;

    /**
     * This function is setting the tick date of journey.
     * @param tickDate Date Date.
     */
    @Override
    public void setTickDate(Date tickDate) {
        super.setTickDate(tickDate);
    }

    /**
     * This function is returning the tick date of journey.
     * @return Returning the tick date.
     */
    @Override
    public Date getTickDate() {
        return super.getTickDate();
    }

    /**
     * This function is setting the end time of journey.
     * @param endTime End time.
     */
    @Override
    public void setEndTime(Date endTime) {
        super.setEndTime(endTime);
    }

    /**
     * This function is setting the start time of journey.
     * @param startTime Start Time.
     */
    @Override
    public void setStartTime(Date startTime) {
        super.setStartTime(startTime);
    }

    /**
     * This function is setting the distance travelled in journey.
     * @param distance Distance travelled in journey.
     */
    public void setDistance(float distance) {
        this.distance = distance;
    }

    /**
     * This function is setting the end point of journey.
     * @param endPoint End point of journey.
     */
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * This Function is setting the start point of journey.
     * @param startingPoint  Start point of journey.
     */
    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    /**
     * This function is returning the distance travelled.
     * @return Returning travelled distance.
     */
    public float getDistance() {
        return distance;
    }

    /**
     * @return Starting point of journey.
     */
    public String getStartingPoint() {
        return startingPoint;
    }

    /**
     * @return Returning end point of journey.
     */
    public String getEndPoint() {
        return endPoint;
    }

    /**
     * @return Returning the start time of journey.
     */
    public Date getStartTime() {
        return (super.getStartTime());
    }

    /**
     * @return Returning end time of journey.
     */
    public Date getEndTime() {
        return (super.getEndTime());
    }
}
