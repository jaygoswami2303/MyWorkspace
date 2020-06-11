import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

class DataRead {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(new File("in1"));
        int numEmployees = Integer.parseInt(s.nextLine());
        ArrayList<Employee> employeesHQ = new ArrayList<Employee>(numEmployees);
        for (int i = 0; i < numEmployees; i++) {
            String[] employeeData = s.nextLine().split(",");
            employeesHQ.add(new Employee(Integer.parseInt(employeeData[0]), employeeData[1], 
                                Integer.parseInt(employeeData[2]), Integer.parseInt(employeeData[3])));
        }
        int m = s.nextInt();
        int k = s.nextInt();
        s.nextLine(); // skip the '\n'
        ArrayList<Employee> optedEmployee = new ArrayList<Employee>(m);
        for (int i = 0; i < m; i++)
            optedEmployee.add(searchEmployees(employeesHQ, s.nextInt()));
        // Done with the input part

        // Now, we want the first min(m, k) employees from the m employees based on tenure, and their salary
        // A naive approach is to find the (maximum tenure, maximum salary) k times
        ArrayList<Employee> employeesNEW = new ArrayList<Employee>(); 
        for (int i = 0; i < Math.min(m, k); i++) {
            Employee best = optedEmployee.get(0);
            int size = optedEmployee.size();
            for (int j = 1; j < size; j++) {
                if (optedEmployee.get(j).getTenure() > best.getTenure() || ((optedEmployee.get(j).getTenure() 
                       == best.getTenure()) && (optedEmployee.get(j).getSalary() > best.getSalary()))) {
                    best = optedEmployee.get(j);
                }
            }
            employeesNEW.add(best);
            optedEmployee.remove(best);
        } // Final employees for the new office
        for (int i = 0; i < employeesNEW.size(); i++)
             if (employeesHQ.contains(employeesNEW.get(i))) employeesHQ.remove(employeesNEW.get(i));
        // Print the split records
        System.out.println("HQ");
        for (int i = 0; i < employeesHQ.size(); i++)
            System.out.println(employeesHQ.get(i).getId());//+" "+employeesHQ.get(i).getTenure()+" "+employeesHQ.get(i).getSalary());
        System.out.println("NEW");
        for (int i = 0; i < employeesNEW.size(); i++)
            System.out.println(employeesNEW.get(i).getId());//+" "+employeesNEW.get(i).getTenure()+" "+employeesNEW.get(i).getSalary());
    }

    // Return the employee with the matching id
    public static Employee searchEmployees(ArrayList<Employee> records, int id) {
        for (int i = 0; i < records.size(); i++) if (records.get(i).getId() == id) return records.get(i);
        return null; // failed to find
    }
}