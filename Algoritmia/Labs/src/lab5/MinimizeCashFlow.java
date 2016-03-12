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
     * payments.
     */
    public void calculate() {
        People p = new People(payments);
        this.people = p.getPeople();
        printBeforeMsg();

        do {
            Person paysMore = findMin();
            Person receivesMore = findMax();
            if (-paysMore.getBalance() <= receivesMore.getBalance()) {
                results.add(new Payment(paysMore.getName(), receivesMore.getName(), -paysMore.getBalance()));
                receivesMore.setBalance(receivesMore.getBalance() + paysMore.getBalance());
                paysMore.setBalance(0);
            } else {
                results.add(new Payment(paysMore.getName(), receivesMore.getName(), receivesMore.getBalance()));
                paysMore.setBalance(paysMore.getBalance() + receivesMore.getBalance());
                receivesMore.setBalance(0);
            }
        }
        while (!allBalanced());

        printAfterMsg();


    }

    private void printAfterMsg() {
        System.out.println("Final results: \n");
        results.forEach(payment -> System.out.println(payment.getSource() + " has to pay " +
                payment.getValue() + " to " +
                payment.getTarget()));
        System.out.println("---------------------------------------------\n");
    }

    private void printBeforeMsg() {
        System.out.println("Calculating balance for: \n");
        payments.forEach(payment -> System.out.println(payment.getSource() + " has to pay " +
                payment.getValue() + " to " +
                payment.getTarget()));
        System.out.println("\n");
    }

    /**
     * Finds the maximum balance among the people
     *
     * @return maximum balance (person who receives more)
     */
    private Person findMax() {
        int max = people.get(people.size() - 1).getBalance();
        for (int i = people.size() - 2; i >= 0; i--) {
            int balance = people.get(i).getBalance();

            if (balance > max) {
                return people.get(i);
            }

        }
        return people.get(people.size() - 1);
    }

    /**
     * Finds the minimum balance among the people
     *
     * @return minimum balance (person who pays more)
     */
    private Person findMin() {
        int min = people.get(0).getBalance();
        for (int i = 1; i < people.size(); i++) {
            int balance = people.get(i).getBalance();

            if (balance < min) {
                return people.get(i);
            }

        }
        return people.get(0);
    }

    /**
     * Checks if everyone in the people array has fulfilled
     * their payments
     *
     * @return true if everything is balanced; false, otherwise
     */
    private boolean allBalanced() {
        for (Person p : people) {
            if (p.getBalance() != 0) {
                return false;
            }
        }
        return true;
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

