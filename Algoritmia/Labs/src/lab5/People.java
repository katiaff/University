package lab5;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carla on 08/03/2016.
 */
public class People {
    List<Person> people;
    List<Payment> payments;

    public People(ArrayList<Payment> payments) {
        this.people = new ArrayList<>();
        this.payments = payments;
    }

    public void createPeople() {
        for (Payment pay : payments) {
            Person personSource = getPerson(pay.getSource());
            Person personTarget = getPerson(pay.getTarget());
            if (personSource == null) {
                people.add(new Person(pay.getSource(), pay.getValue(), 0, -pay.getValue()));
            }
            if (personTarget == null) {
                people.add(new Person(pay.getTarget(), 0, pay.getValue(), pay.getValue()));
            }
            if (personSource != null) {
                personSource.setToPay(personSource.getToPay() + pay.getValue());
                personSource.setBalance(personSource.getBalance() - pay.getValue());
            }
            if (personTarget != null) {/*
                personTarget.setToPay(personTarget.getToPay() + pay.getValue());
                personTarget.setBalance(personTarget.getBalance() - pay.getValue());*/
            }

        }
    }

    public List<Person> getPeople() {
        return people;
    }

    public Person getPerson(String name) {
        for (Person person : people) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }


}
