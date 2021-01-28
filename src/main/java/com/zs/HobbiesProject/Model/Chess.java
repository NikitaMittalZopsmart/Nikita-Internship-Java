package com.zs.HobbiesProject.Model;

import java.util.Date;

/**
 * This class is created for the Chess hobby.
 */
public class Chess extends Hobby {
    private int nOfPlayer;
    private String result;




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

    public void setnOfPlayer(int nOfPlayer) {
        this.nOfPlayer = nOfPlayer;
    }

    public void setResult(String result) {
        this.result = result;
    }


    /**
     * @return Returning result of game.
     */
    public String getResult() {
        return result;
    }

    /**
     * @return Returning number of player.
     */
    public int getPlayer() {
        return nOfPlayer;
    }

    /**
     * @return Returning start time of game.
     */
    public Date getStartTime() {
        return (super.getStartTime());
    }

    /**
     * @return Returning end time of game.
     */
    public Date getEndTime() {
        return (super.getEndTime());
    }

}
