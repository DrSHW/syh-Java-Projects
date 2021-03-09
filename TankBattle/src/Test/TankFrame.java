package Test;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

//继承窗口(Frame),以调用其方法
public class TankFrame extends Frame {
    //初始化己方坦克参数
    Tank myTank=new Tank(200,600,Dir.UP,Group.GOOD,this);
    //声明装子弹的容器(一个ArrayList集合)
    List<Bullet> bullets=new ArrayList<>();
    //声明装敌人坦克的容器(一个ArrayList集合)
    List<Tank> enemies =new ArrayList<>();
    //初始化子弹参数
    Bullet b=new Bullet(300,300,Dir.DOWN,Group.GOOD,this);
    static final int GAME_WIDTH=3200,GAME_HEIGHT=2400;

    //定义窗口显示方法
    public TankFrame() {
        //设置窗口初始大小
        setSize(GAME_WIDTH,GAME_HEIGHT);
        //使窗口大小不可变化
        setResizable(false);
        //设置窗口标题
        setTitle("TankBattle");
        //展示窗口
        setVisible(true);
        //添加窗口可关闭
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //添加监视器
        addKeyListener(new MyKeyListener());
    }

    //双缓冲防止屏幕闪烁
    Image offScreenImage=null;
    @Override
    public void update(Graphics g){
        if (offScreenImage == null){
            offScreenImage=this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen =offScreenImage.getGraphics();
        Color c=gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }
    //画出坦克(自动调用的方法)
    @Override
    public void paint(Graphics g) {
        //System.out.println("paint");
        //画调试字幕
        Color c=g.getColor();
        g.setColor(Color.BLUE);
        g.drawString("子弹的数量："+bullets.size(),40,240);
        g.drawString("坦克的数量："+enemies.size(),80,480);
        //画己方坦克
        myTank.paint(g);
        //画子弹
        for (int i=0;i<bullets.size();i++){
            bullets.get(i).paint(g);
        }
        //画敌方坦克
        for(int i=0;i<enemies.size();i++){
            enemies.get(i).paint(g);
        }
        //循环遍历两个集合(判断是否体积碰撞)
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                bullets.get(i).collideWith(enemies.get(j));
            }
        }
    }

    //键盘联动类(内部类，方便一次调用)
    class MyKeyListener extends KeyAdapter {
        //设置按键初始状态
        boolean bL=false;
        boolean bR=false;
        boolean bU=false;
        boolean bD=false;

        @Override
        public void keyPressed(KeyEvent k) {
            int key = k.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL=true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR=true;
                    break;
                case KeyEvent.VK_UP:
                    bU=true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=true;
                    break;
                default:
                    break;
            }
            setMyTankDir();
        }

        @Override
        public void keyReleased(KeyEvent k) {
            int key = k.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL=false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR=false;
                    break;
                case KeyEvent.VK_UP:
                    bU=false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                default:
                    break;
        }
            setMyTankDir();
    }

        private void setMyTankDir() {
            myTank.setMoving(true);
            if (bL) {myTank.setDir(Dir.LEFT);}
            if (bR) {myTank.setDir(Dir.RIGHT);}
            if (bU) {myTank.setDir(Dir.UP);}
            if (bD) {myTank.setDir(Dir.DOWN);}
            if(!bL && !bR && !bU && !bD){myTank.setMoving(false);}
        }
    }
}


