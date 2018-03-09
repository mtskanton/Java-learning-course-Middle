package app.example.form;

/**
 * Форма трансфера между аккаунтами.
 */
public class TransferForm {

    private int fromId;
    private int toId;
    private double sum;

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
