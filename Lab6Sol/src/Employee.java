class Employee {
    private int id;
    private String name;
    private int tenure;
    private int salary;

    public Employee(int id, String name, int tenure, int salary) {
        this.id = id;
        this.name = name;
        this.tenure = tenure;
        this.salary = salary;    
    }

    public int getSalary() {return salary;}
    public int getTenure() {return tenure;}
    public int getId() {return id;}
}