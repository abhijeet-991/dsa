package concurrency;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> logs = Arrays.asList("ERROR: Worm found", "INFO: Water drunk", "ERROR: Anxiety spike", "DEBUG: Code written");
        System.out.println(logs.stream().filter(log -> log.startsWith("ERR")).map(log -> log.substring(7)).toList());
    }

    static class Project {
        List<String> techStack;

        public List<String> getTechStack() {
            return techStack;
        }

        public Project(List<String> techStack) {
            this.techStack = techStack;
        }

        public void setTechStack(List<String> techStack) {
            this.techStack = techStack;
        }
    }

    static class Employee {
        String name;
        String department;
        double salary;

        public Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }
    }

}
