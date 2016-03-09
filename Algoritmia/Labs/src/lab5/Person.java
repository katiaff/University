package lab5;

/**
 * Created by Carla on 08/03/2016.
 */
public class Person {
    String name;
    int toPay;
    int toReceive;
    int balance;

    public Person(String name, int toPay, int toReceive) {
        this.toPay = toPay;
        this.toReceive = toReceive;
        this.balance = toReceive - toPay;
        this.name = name;
    }

    public int getToPay() {
        return toPay;
    }

    public void setToPay(int toPay) {
        this.toPay = toPay;
    }

    public int getToReceive() {
        return toReceive;
    }

    public void setToReceive(int toReceive) {
        this.toReceive = toReceive;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

}
