package Commodity;

import java.util.HashMap;

public class Method2 {
    private HashMap<String,Double> maps;

    public Method2(HashMap<String, Double> maps) {
        this.maps = maps;
    }

    public Method2() {
    }

    public HashMap<String, Double> getMaps() {
        return maps;
    }

    public void setMaps(HashMap<String, Double> maps) {
        this.maps = maps;
    }

    public void showAndCalculate(String item, double money){
        HashMap<String,Double> hm=new HashMap<>();
        hm=this.getMaps();
        for (String key:hm.keySet()
             ) {
            if (!item.equals(hm.keySet())){
                hm.put(item,money);
            }
            else {
                money=hm.get(key)+money;
            }
            this.setMaps(hm);
            System.out.println("项目:"+key+" 花费了："+hm.get(key)+"元");
        }
        double j=0;
        for (String key:hm.keySet()) {
            j=j+maps.get(key);
        }
        System.out.println("已花费");
        System.out.println("剩余："+j+"元");
    }
}
