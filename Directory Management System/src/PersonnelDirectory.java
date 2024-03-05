import java.util.Scanner;

public class PersonnelDirectory {
    public static void main(String[] args) {
        Personnel personnel = new Personnel();
        totalObjects total = new totalObjects();
        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            System.out.println("Welcome to the Personnel Directory Management System");
            System.out.println("====================================================");
            System.out.println("\n\n\t 1. Add Personnel");
            System.out.println("\n\t 2. Find Personnel");
            System.out.println("\n\t 3. Print Names");
            System.out.println("\n\t 4. Number of Entries in the Directory");
            System.out.println("\n\t Select one of the options above (1, 2, 3, 4)");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addPersonnelHere(scanner, personnel, total);
                    break;
                case 2:
                    findPersonnel(scanner, personnel, total);
                    break;
                case 3:
                    printNames(scanner, personnel);
                    break;
                case 4:
                    System.out.println("Total Entries: " + total.getTotalObjects());
                    break;
            }
        } while (true);
    }

    private static void addPersonnelHere(Scanner scanner, Personnel personnel, totalObjects total) {
        PersonnelFactory factory = new PersonnelFactory();
        
    	System.out.println("Enter first name: ");
        String firstN = scanner.nextLine();
        System.out.println("Enter last name: ");
        String lastN = scanner.nextLine();
        System.out.println("Enter middle name: ");
        String middleN = scanner.nextLine();
        System.out.println("Enter type of Personnel: ");
        String type = scanner.nextLine();
        System.out.println("Enter employee id: ");
        int empID = scanner.nextInt();
        System.out.println("Enter base salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();


        Person p = factory.createPersonnel(type, lastN, firstN, middleN, empID, salary);
        personnel.addPersonnel(p);
        
        total.objectAdded();
    }

    private static void findPersonnel(Scanner scanner, Personnel personnel, totalObjects total) {
        System.out.println("Enter first name: ");
        String firstN = scanner.nextLine();
        System.out.println("Enter last name: ");
        String lastN = scanner.nextLine();

        boolean found = false;
        for (Person person : personnel.getPersonList()) {
            if (person.getFirst().equals(firstN) && person.getLast().equals(lastN)) {
                found = true;
                System.out.println("Found");
                person.printName(0);
            }
        }

        if (!found) {
            System.out.println("Not found");
            Person p1 = new Person(lastN, firstN, " ");
            personnel.addPersonnel(p1);
            total.objectAdded();
        }
    }

    private static void printNames(Scanner scanner, Personnel personnel) {
        System.out.println("Enter the order (0: first, middle, last; 1: first, last, middle; 2: last, first, middle): ");
        int order = scanner.nextInt();
        scanner.nextLine();

        for (Person person : personnel.getPersonList()) {
            person.printName(order);
        }
    }
}
