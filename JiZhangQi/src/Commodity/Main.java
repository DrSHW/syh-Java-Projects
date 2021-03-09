package Commodity;

import Dao.ItemDao;
import Dao.ItemMangerDao;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
//        Method m = new Method();
////	m.showAndCalculate(new Expense("晚饭开销",-7.5));
////    }
//        Expense exp=new Expense("定额",2500);
//        ;
//        Method2 m2=new Method2();
//        HashMap<String,Double> hm1=new HashMap<>();
//        hm1.put("定额", (double) -2500);
//        m2.setMaps(hm1);
//        m2.showAndCalculate("早饭开销：",9.1);
//    }
        ItemDao dao=new ItemDao();
        ItemMangerDao imd=new ItemMangerDao();
        imd.addItem(dao);
        dao.getItem();
        dao.calculate();
}}
