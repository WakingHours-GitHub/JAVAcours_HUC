package Dome01;

// Cell类----设计每个按钮对象应该具备的属性功能---针对按钮

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Cell extends JButton {
    private static int imageWidth;//设置按钮的宽度大小
    private static int imageHeight;
    private int ID = 0;//设置当前按钮的指向坐标

    public Cell(Icon icon, int id, int width, int height)//构造函数初始化，传入两个参数，一个是图像的图标，一个是该按钮的数组ID
    {
        this.setIcon(icon);
        this.ID = id;
        this.imageWidth = width;
        this.imageHeight = height;
        this.setSize(imageWidth, imageHeight);
    }

    public void move(Direction dir)//移动
    {
        Rectangle rec = this.getBounds();//获取当前对象的这个边框
        switch (dir) {
            //向上移动，改变坐标
            case UP -> this.setLocation(rec.x, rec.y + imageHeight);
            //向下移动
            case DOWN -> this.setLocation(rec.x, rec.y - imageHeight);
            //向左移动
            case LEFT -> this.setLocation(rec.x - imageWidth, rec.y);
            //向右移动
            case RIGHT -> this.setLocation(rec.x + imageWidth, rec.y);
        }
    }

    public int getID() {
        return ID;
    }

    public int getX() {
        return this.getBounds().x;
    }

    public int getY() {
        return this.getBounds().y;
    }
}