package lab5;

import java.util.Comparator;

/**
 * Comparator which orders Person objects ascendingly
 * by their balance (people who owe money first, people
 * who are owed money last.
 */
public class BalanceAscendingComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getBalance() - o2.getBalance();
    }
}
