package main.java.com.zs.HobbiesProject.Model;

import java.util.Date;

/**
 * This class is created for the Travelling hobby.
 */
public class Travel extends Hobby {
    private float distance;
    private String startingPoint;
    private String endPoint;


    @Override
    public void setTickDate(Date tickDate) {
        super.setTickDate(tickDate);
    }

    @Override
    public Date getTickDate() {
        return super.getTickDate();
    }

    @Override
    public void setEndTime(Date endTime) {
        super.setEndTime(endTime);
    }

    @Override
    public void setStartTime(Date startTime) {
        super.setStartTime(startTime);
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    /**
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
