public class Security extends Person {
    private int secID;
    private double baseSalary;

    public Security(String last, String first, String middle, int id, double sal) {
        super(last, first, middle);
        secID = id;
        baseSalary = sal;
    }

    public int getEmpID() {
        return secID;
    }

    public double getBaseSalary() {
        return baseSalary;
    }
}
