package factory;

import java.util.Date;

/**
 * Created by y28yang on 6/14/2016.
 */
public class SupplyPeriod implements Comparable<SupplyPeriod> {

    private Date startTime;
    private Date endTime;
    private int materialNumber;

    public SupplyPeriod(long startTime, long endTime, int materialNumber) {

        this.startTime = new Date(startTime);
        this.endTime = new Date(endTime);
        this.materialNumber = materialNumber;
    }

    public SupplyPeriod(Date startTime, Date endTime, int materialNumber) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.materialNumber = materialNumber;
    }
    public long getStartTimeMsec() {
        return startTime.getTime();
    }

    public long getEndTimeMsec() {
        return endTime.getTime();
    }

    public Date getStartTime()
        {
            return startTime;
        }

    public Date getEndTime(){
        return endTime;
    }

    public int getMaterialNumber() {
        return materialNumber;
    }

    public int compareTo(SupplyPeriod o)
        {
            return startTime.compareTo(o.getStartTime());
        }
}
