package lab5;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution to minimize the cash flow among a group
 * of people.
 */
public class MinimizeCashFlow {
    private List<Payment> payments = new ArrayList<>();
    private List<Payment> results = new ArrayList<>();

    /**
     * Calculates the most efficient cash flow among all the
     * payments
     */
    public void calculate() {
/*       create class Person
         toPay
         toReceive
         balance

         create class People ??

         iterate through all the people, calculate the balance

         */


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

