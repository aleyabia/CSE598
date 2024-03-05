public class PersonnelFactory {
    public Person createPersonnel( String type,  String lastN, String firstN, String middleN, int id, double salary) {
        if (type.equalsIgnoreCase("Employee")) {
            return new Employee(lastN, firstN, middleN, id, salary);
        } else if (type.equalsIgnoreCase("Person")) {
            return new Person(lastN, firstN, middleN);
        } else if (type.equalsIgnoreCase("Executive")) {
        	return new Executive(lastN, firstN, middleN, id, salary);
        } else if (type.equalsIgnoreCase("Security")) {
        	return new Security(lastN, firstN, middleN, id, salary);
        } else if (type.equalsIgnoreCase("Volunteer")) {
        	return new Volunteer(lastN, firstN, middleN, id);
        }
        // Handle other types as needed or return null for unsupported types
        return null;
    }


}
