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
        this.people.sort(new BalanceAscendingComparator());
        for (int owes = 0; owes < people.size(); owes++) {
            for (int receives = people.size() - 1; receives >= owes; receives--) {
                Person personOwes = people.get(owes);
                Person personReceives = people.get(receives);
                if (-personOwes.getBalance() <= personReceives.getBalance()) {
                    Payment payment = new Payment(personOwes.getName(), personReceives.getName(), -personOwes.getBalance());
                    results.add(payment);
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
        return 0;
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

