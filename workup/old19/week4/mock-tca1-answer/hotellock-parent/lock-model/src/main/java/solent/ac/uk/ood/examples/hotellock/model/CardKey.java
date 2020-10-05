package solent.ac.uk.ood.examples.hotellock.model;

import java.util.Date;

public class CardKey {

    private Date startDate;

    private Date endDate;

    private String roomNumber;

    private int issueNumber;

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the roomNumber
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * @return the issueNumber
     */
    public int getIssueNumber() {
        return issueNumber;
    }

    /**
     * @param issueNumber the issueNumber to set
     */
    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public String toString() {
        return "CardKey{" + "startDate=" + getStartDate() 
                + ", endDate=" + getEndDate() 
                + ", roomNumber=" + getRoomNumber() 
                + ", issueNumber=" + getIssueNumber() + '}';
    }
}
