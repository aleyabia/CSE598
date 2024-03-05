public class Executive extends Person {
    private int execID;
    private double baseSalary;

    public Executive(String last, String first, String middle, int id, double sal) {
        super(last, first, middle);
        execID = id;
        baseSalary = sal;
    }

    public int getEmpID() {
        return execID;
    }

    public double getBaseSalary() {
        return baseSalary;
    }
}
