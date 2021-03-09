package Commodity;

public class Item {
    private String activity;
    private double cost;

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "项目：'" + activity + '\'' +
                ", 花费：" + cost +
                "元"+"\n";
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Item(String activity, double cost) {
        this.activity = activity;
        this.cost = cost;
    }

    public Item() {
    }
}
