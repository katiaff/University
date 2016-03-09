package lab5;

import java.util.ArrayList;

/**
 * Solution to minimize the cash flow among a group
 * of people.
 */
public class MinimizeCashFlow {
    private ArrayList<Payment> payments = new ArrayList<>();
    private ArrayList<Payment> results = new ArrayList<>();
    private ArrayList<Person> people;

    /**
     * Calculates the most efficient cash flow among all the
     * payments
     */
    public void calculate() {
        People p = new People(payments);
        this.people = p.getPeople();
        ArrayList<Person> toPay = new ArrayList<>();
        for (Person person : people) {
            if (person.getBalance() < 0) {
                toPay.add(person);
            }
            if (person.getBalance() > 0) {
                for (Person person2 : toPay) {
                    if (person2.getToPay() == person.getToReceive()) {
                        Payment payment = new Payment(person2.getName(), person.getName(), person.getToPay());
                        results.add(payment);
                    }
                }
            }
        }

    }

    /**
     * Returns the final debt of a payment
     *
     * @param person1 source
     * @param person2 target
     * @return final debt from source to target
     */
    public int getFinalDebt(String person1, String person2) {
        for (Payment pay : results) {
            if (pay.getSource().equals(person1) && pay.getTarget().equals(person2)) {
                return pay.getValue();
            }
        }
        throw new IllegalArgumentException("Source has no payment with Target");
    }

    /**
     * Adds a payment to the list of payments
     *
     * @param payment payment to add
     */
    public void addPayment(Payment payment) {
        payments.add(payment);
    }
}

