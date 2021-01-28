package com.zs.HobbiesProject.Model;

import java.util.Date;

/**
 * This class is created for the Badminton hobby.
 */
public class Badminton extends Hobby {
    private int numberOfMove;
    private String result;



    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public void setStartTime(Date startTime) {
        super.setStartTime(startTime);
    }


    @Override
    public void setEndTime(Date endTime) {
        super.setEndTime(endTime);
    }

    @Override
    public void setTickDate(Date tickDate) {
        super.setTickDate(tickDate);
    }

    @Override
    public Date getTickDate() {
        return super.getTickDate();
    }

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
