package logExpense;

import java.util.Date;

/**
 * Created by flavazelli on 2017-02-12.
 */

public class MethodOfPayment {

    private String nickname;
    private int methodOfPaymentTypeId;
    private long date;

    public MethodOfPayment(String nickname, int methodOfPaymentTypeId, long date) {
        this.nickname = nickname;
        this.methodOfPaymentTypeId = methodOfPaymentTypeId;
        this.date = date;
    }

    public MethodOfPayment(String nickname, int methodOfPaymentTypeId) {
        this.nickname = nickname;
        this.methodOfPaymentTypeId = methodOfPaymentTypeId;
        this.date = new Date().getTime(); //defaults to current time in milliseconds
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    //TODO get methodtype

    /*public MethodOfPaymentType getMethodOfPaymentType() {

        return null;
    } */

    public void setMethodOfPaymentTypeId(int methodOfPaymentTypeId) {
        this.methodOfPaymentTypeId = methodOfPaymentTypeId;
    }

    public Date getDate() {

        Date date = new Date(this.date);
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
