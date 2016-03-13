package lab5;

/**
 * Models a person who has money to pay and to receive
 */
public class Person {
    private int toReceive;
    private int toPay;
    private String name;
    private int balance;

    public Person(String name, int toPay, int toReceive) {
        this.toPay = toPay;
        this.toReceive = toReceive;
        this.balance = toReceive - toPay;
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    protected void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getToReceive() {
        return toReceive;
    }

    protected void setToReceive(int toReceive) {
        this.toReceive = toReceive;
    }

    public int getToPay() {
        return toPay;
    }

    protected void setToPay(int toPay) {
        this.toPay = toPay;
    }
}
