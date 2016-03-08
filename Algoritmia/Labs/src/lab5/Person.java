package lab5;

/**
 * Created by Carla on 08/03/2016.
 */
public class Person {
    String name;
    int toPay;
    int toReceive;
    int balance;

    public Person(String name, int toPay, int toReceive, int balance) {
        this.toPay = toPay;
        this.toReceive = toReceive;
        this.balance = balance;
    }

    public int getToPay() {
        return toPay;
    }

    public int getToReceive() {
        return toReceive;
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public Person getPerson() {
        return this;
    }
}
