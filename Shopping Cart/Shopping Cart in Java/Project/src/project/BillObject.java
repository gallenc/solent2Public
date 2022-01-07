
package project;


public class BillObject {
    private int id;
    private String uname;
    private int bill;
    private String date;

    public BillObject(int id, String uname, int bill, String date) {
        this.id = id;
        this.uname = uname;
        this.bill = bill;
        this.date = date;
    }
    
 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }    
    
}
