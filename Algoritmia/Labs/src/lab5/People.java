package lab5;

import java.util.ArrayList;

/**
 * Creates an ArrayList of people calculating how
 * much each Person owes and gets to receive.
 */
public class People {
    private ArrayList<Person> people;
    private ArrayList<Payment> payments;

    /**
     * Create People object and people list.
     *
     * @param payments All the different payments we have to do.
     */
    public People(ArrayList<Payment> payments) {
        this.people = new ArrayList<>();
        this.payments = payments;
        createPeople();
    }

    /**
     * Calculates the amount of money each Person has to pay,
     * how much they have to receive, and the total balance from those
     * two attributes
     */
    private void createPeople() {
        for (Payment pay : payments) {
            Person personSource = getPerson(pay.getSource());
            Person personTarget = getPerson(pay.getTarget());
            if (personSource == null) {
                people.add(new Person(pay.getSource(), pay.getValue(), 0));
            }
            if (personTarget == null) {
                people.add(new Person(pay.getTarget(), 0, pay.getValue()));
            }
            if (personSource != null) {
                personSource.setToPay(personSource.getToPay() + pay.getValue());
                personSource.setBalance(personSource.getBalance() - pay.getValue());
            }
            if (personTarget != null) {
                personTarget.setToReceive(personTarget.getToReceive() + pay.getValue());
                personTarget.setBalance(personTarget.getBalance() + pay.getValue());
            }

        }
    }

    /**
     * Returns the people list
     * @return people
     */
    public ArrayList<Person> getPeople() {
        people.sort((o1, o2) -> o1.getBalance() - o2.getBalance());
        return people;
    }

    /**
     * Get a person from the list using their name
     * @param name the name of the person
     * @return the person; null if not found
     */
    private Person getPerson(String name) {
        for (Person person : people) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }


}
