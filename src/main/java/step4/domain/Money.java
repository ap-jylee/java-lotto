package step4.domain;

public class Money {

    private static final int UNIT_PRICE = 1000;

    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public int calculateLottoPurchaseCount() {
        return money / UNIT_PRICE;
    }

    public double calculateYield(int winnings) {
        return (double) winnings / money;
    }

}
