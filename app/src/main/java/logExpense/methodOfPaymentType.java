package logExpense;

/**
 * Created by flavazelli on 2017-02-12.
 */

public class MethodOfPaymentType {

    private int id;
    private String name;

    //constructor
    public MethodOfPaymentType(int id, String name) {
        this.id = id;
        this.name  = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
