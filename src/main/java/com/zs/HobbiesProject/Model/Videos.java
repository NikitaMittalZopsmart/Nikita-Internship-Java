package main.java.com.zs.HobbiesProject.Model;

import java.util.Date;

/**
 * This class is created for the  hobby of video watching.
 */
public class Videos extends Hobby {
    private String title;
    @Override
    public void setTickDate(Date tickDate) {
        super.setTickDate(tickDate);
    }
    @Override
    public Date getTickDate() {
        return super.getTickDate();
    }
    @Override
    public void setStartTime(Date startTime) {
        super.setStartTime(startTime);
    }
    @Override
    public void setEndTime(Date endTime) {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Returning title of video.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return Returning start time.
     */
    public Date getStartTime() {
        return (super.getStartTime());
    }

    /**
     * @return Returning end time.
     */
    public Date getEndTime() {
        return (super.getEndTime());
    }

}
