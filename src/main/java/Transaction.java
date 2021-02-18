import java.util.Date;
public class Transaction {

    private int amount;
    private Date date;

    public Transaction(int amount, Date date) {
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", date=" + date +
                '}';
    }
}