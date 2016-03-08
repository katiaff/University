package lab5;

import java.util.ArrayList;

/**
 * Created by Carla on 08/03/2016.
 */
public class People {
    ArrayList<Person> people;
    ArrayList<Payment> payments;

    public People(ArrayList<Person> people, ArrayList<Payment> payment) {
        this.people = people;
        this.payments = payments;
    }

    public void createPeople() {
        for (Payment pay : payments) {
            if (!people.contains(pay.getSource())) {
                people.add(new Person(pay.getSource(), pay.getValue(), 0, pay.getValue()));
            } else {

            }
        }
    }

    public ArrayList<Person> getPeople() {
        return people;
    }


}
