package Test;

public class T_Main {
    public static void main(String[] args) throws InterruptedException {
        //创建主界面对象
        TankFrame tf=new TankFrame();
        //调用方法生成敌方坦克
        for (int i = 0; i < 5; i++) {
            tf.enemies.add(new Tank(500+i*60,600,Dir.DOWN,Group.BAD,tf));
        }
        //高频率刷新屏幕
        while(true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
