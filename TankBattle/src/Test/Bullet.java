package Test;

import java.awt.*;

public class Bullet {
    //设置初始速度
    private static final int SPEED = 30;
    //声明变量:子弹坐标
    private int x, y;
    //连接枚举
    private Dir dir;
    //定义子弹长度宽度
    public static final int WIDTH = 5, HEIGHT = 5;
    //判断子弹死没死
    private boolean live=true;
    //对象间进行通讯
    private TankFrame tf=null;
    //
    private Group group=Group.BAD;

    //构造方法
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }


    public Bullet(int x, int y, Dir dir,Group group,TankFrame tf) {
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
    //画出子弹
    public void paint(Graphics g) {
        //如果子弹死了就移除
        if (!live)
        {
            tf.bullets.remove(this);
        }
        //设置子弹颜色,坐标,大小
        Color c = g.getColor();
        g.setColor(Color.PINK);
        g.fillRect(x, y, WIDTH, HEIGHT);
        g.setColor(c);
        //动起来！
        move();
    }
    //设置子弹的移动规则
    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
            //移除飞出屏幕外的子弹
        if(x<0||y<0||x>TankFrame.GAME_WIDTH||y>TankFrame.GAME_HEIGHT)
        {
            live=false;
        }
    }

    //判断坦克和子弹是否存在体积碰撞
    public void collideWith(Tank tank){
        if (this.group==tank.getGroup()){
            return;
        }
        Rectangle rect1=new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rect2=new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        if (rect1.intersects(rect2)){
            tank.die();
            this.die();
        }
    }

    //将子弹设为死了的状态
    private void die() {
        this.live=false;
    }
}