public class Volunteer extends Person {
    private int volID;
    

    public Volunteer(String last, String first, String middle, int id) {
        super(last, first, middle);
        volID = id;
        
    }

    public int getEmpID() {
        return volID;
    }


}
