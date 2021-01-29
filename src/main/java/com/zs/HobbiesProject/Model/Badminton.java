package com.zs.HobbiesProject.Model;

import java.util.Date;

/**
 * This class is created for the Badminton hobby.
 */
public class Badminton extends Hobby {
    private int numberOfMove;
    private String result;


    /**
     * This function is setting result of the match of badminton.
     * @param result Result of match.
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * This function is setting the value of startTime.
     * @param startTime Start time of match.
     */
    @Override
    public void setStartTime(Date startTime) {
        super.setStartTime(startTime);
    }

    /**
     * This function is setting the end time of match of badminton.
     * @param endTime End time of match.
     */

    @Override
    public void setEndTime(Date endTime) {
        super.setEndTime(endTime);
    }

    /**
     * This function is for ticking the date of match.
     * @param tickDate  Date of match.
     */
    @Override
    public void setTickDate(Date tickDate) {
        super.setTickDate(tickDate);
    }

    /**
     * This function is returning the date of match.
     * @return Returning the date of match.
     */
    @Override
    public Date getTickDate() {
        return super.getTickDate();
    }

    /**
     * This function is setting the number of total moves in match.
     * @param numberOfMove Total number of moves.
     */
    public void setNumberOfMove(int numberOfMove) {
        this.numberOfMove = numberOfMove;
    }

    /**
     * @return Returning the result value.
     */
    public String getResult() {
        return result;
    }

    /**
     * @return Returning number of moves.
     */
    public int getNumberOfMove() {
        return numberOfMove;
    }

    /**
     * @return Returning Start time.
     */
    public Date getStartTime() {
        return (super.getStartTime());
    }

    /**
     * @return Returning End time.
     */
    public Date getEndTime() {
        return (super.getEndTime());
    }

}
