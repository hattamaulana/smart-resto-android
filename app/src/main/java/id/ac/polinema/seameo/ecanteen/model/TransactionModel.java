package id.ac.polinema.seameo.ecanteen.model;

public class TransactionModel {
    public static final String NAME = "name";
    public static final String DATE_TIME = "date_time";
    public static final String ITEMS = "items";
    public static final String MONEY = "money";
    public static final String CASHBACK = "cashback";
    public static final String PAYMENT = "payment";

    private String name;
    private String dateTime;
    private String[] items;
    private int money;
    private int cashback;
    private int payment;

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCashback() {
        return cashback;
    }

    public void setCashback(int cashback) {
        this.cashback = cashback;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }
}
