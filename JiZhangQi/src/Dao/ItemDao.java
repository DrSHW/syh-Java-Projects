package Dao;

import Commodity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDao implements IItemDao {
    private static List<Item> itemList = new ArrayList<>();

    static {
        //...(调用ItemDaoManager类中方法addItem();方法添加)
    }

    public void expendList(Item item){
        itemList.add(item);
    }

    @Override
    public void getItem() {
        System.out.println(itemList);
    }

    //计算开支余额
    public void calculate(){
        double j=0.00,k=0.00;
        for (int i=0;i<itemList.size();i++) {
            if (itemList.get(i).getCost() < 0.00) {
                j = j + itemList.get(i).getCost();
            }
        }
            System.out.println("已开销："+(-j)+"元");
        for (int i=0;i<itemList.size();i++){
            k=k+itemList.get(i).getCost();
        }
            System.out.println("余额："+k+"元");
        if (k<=1500){
            System.out.println("本月开销已过多，请注意！");
        }
    }

    public void tian(Item item){expendList(item);}
}
