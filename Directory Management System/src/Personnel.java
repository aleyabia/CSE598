import java.util.*;

public class Personnel {
    private List<Person> personList;

    public Personnel() {
        personList = new ArrayList<Person>();
    }

    public void addPersonnel(Person p) {
        personList.add(p);
    }

    public List<Person> getPersonList() {
        return personList;
    }
}
