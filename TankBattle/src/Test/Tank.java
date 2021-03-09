package Test;

import java.awt.*;
import java.util.Random;

public class Tank {
    //定义坐标初始值
    private int x, y;
    //定义初始方向
    private Dir dir = Dir.DOWN;
    //定义常量速度
    private static int SPEED = 10;
    //判断坦克是否停止
    private boolean moving = true;
    //对象间进行通讯
    private TankFrame tf=null;
    //判断坦克活着
    private boolean live=true;
    //坏坦克随机走
    private Random random=new Random();
    //判断坦克是好的还是坏的
    private Group group=Group.BAD;
    //设定坦克的长度和宽度
    public static int WIDTH=50,HEIGHT=50;

    //构造方法
    public boolean isLiving() {
        return live;
    }
    public void setLiving(boolean living) {
        this.live = living;
    }
    public boolean isMoving() {
        return moving;
    }
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf=tf;
        this.group=group;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Dir getDir() {
        return dir;
    }
    public void setDir(Dir dir) {
        this.dir = dir;
    }

    //画出坦克
    public void paint(Graphics g) {
        //如果坦克死了就移除
        if (!live) {
            tf.enemies.remove(this);
            return;
        }
        //设置颜色
        if (group==Group.GOOD){
            g.setColor(Color.CYAN);
        }
        else {
            g.setColor(Color.GREEN);
        }
        //设置坐标,大小
        g.fillRect(x, y, WIDTH, HEIGHT);
        //动起来！
        move();
    }

    //移动规则设置
    private void move() {
        if (!moving){return;}
    switch (dir){
        case LEFT:
            x-=SPEED;
            break;
        case RIGHT:
            x+=SPEED;
            break;
        case UP:
            y-=SPEED;
            break;
        case DOWN:
            y+=SPEED;
            break;
    }
        //坦克随机射子弹
        if (this.group==Group.BAD&&random.nextInt(100)>95){
            this.fire();
        }
        if (this.group==Group.BAD&&random.nextInt(10)>8) {
            randomDir();
            boundsCheck();
        }
}

    private void boundsCheck() {
        if (this.x<2){x=2;}
        if (this.y<28){y=28;}
//        if (this.x>TankFrame.GAME_WIDTH-Tank.WIDTH-2){
//            x=TankFrame.GAME_WIDTH-Tank.WIDTH-2;
//        }
//        if (this.y>TankFrame.HEIGHT-Tank.HEIGHT-2){
//            y=TankFrame.HEIGHT-Tank.HEIGHT-2;
//        }
    }

    private void randomDir() {
        this.dir=Dir.values()[random.nextInt(4)];
    }

    //射子弹的规则
    public void fire() {
        int bx=this.x+WIDTH/2-Bullet.WIDTH/2;
        int by=this.y+WIDTH/2-Bullet.WIDTH/2;
        tf.bullets.add(new Bullet(bx,by,this.dir,this.group,this.tf));
    }
    //将坦克设为死了状态
    public void die() {
        this.live=false;
    }
}