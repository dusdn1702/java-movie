package domain;

import java.util.function.Function;

public enum Pay {
    CARD(1, 0.95),
    MONEY(2, 0.98);

    private int flag;
    private double sale;

    Pay(int flag, double sale) {
        this.flag = flag;
        this.sale = sale;
    }

    public static long calculateResult(int inputPay, long price) {
        if(CARD.flag == inputPay){
            return (long) (price * CARD.sale);
        }
        return (long) (price * MONEY.sale);
    }

}
