package example;

public class Emp {

    int eid;
    float salary;
    String ename;

    public Emp() {
        System.out.println("Object created!!");
    }

    void display() {
        System.out.println("Employee ID: " + eid);
        System.out.println("Salary: " + salary);
        System.out.println("Employee Name: " + ename);
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    @Override
    public String toString() {
        return "Emp{" + "salary=" + salary + ", ename='" + ename + '\'' + ", eid=" + eid + '}';
    }
}
