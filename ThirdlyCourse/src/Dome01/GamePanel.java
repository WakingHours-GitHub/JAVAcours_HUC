package Dome01;

/*
GamePanel类-----游戏面板设计类，真正的游戏思想从此开始
        主要实现的功能有：
        1.初始化面板按钮数组，将图像转化成图标然后存入按钮中；
        2.打乱数组面板中的按钮排序，实现游戏娱乐功能；
        3.每个按钮添加监听机制，实现点击按钮后的移动功能；
*/


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseListener {

    int ImageWidth;
    int ImageHeight;
    private Cell BlankCell = null;
    private int row = 4;
    private int col = 4;//设置这个拼图的行列
    private Cell cells[] = new Cell[row * col];//创建一个按钮对象数组

    public GamePanel()//构造函数
    {
        this.setLayout(null);
        init();
    }

    public void init()//初始化完成以下功能--完成图像的切割，完成图像到图标的转换，完成按钮图标的添加，将按钮添加到面板上，并且给每一个按钮添加监听机制
    {
        int num = 0;
        BufferedImage buf = null;
        BufferedImage bufnew = null;
        ImageIcon icon = null;
        int breakWidth = 0;
        int breakHeight = 0;
        // 切割图片：
        try {
            buf = ImageIO.read(new File("E:\\HP\\Desktop\\JAVA\\type1\\test.jpg"));//读取文件图像
            ImageWidth = buf.getWidth();
            ImageHeight = buf.getHeight();
            System.out.println("ImageWidth->" + ImageWidth + "ImageHeight->" + ImageHeight);
            breakWidth = ImageWidth / col;
            breakHeight = ImageHeight / row;
        } catch (Exception e) {
            System.out.println(e);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                num = i * col + j;//表示当前这个图像的坐标id，在数组中的下标
                if (num < row * col - 1) {
                    // getSubimage(x,y,w,h)分别表示从x，y开始截取，截取w宽，h高
                    bufnew = buf.getSubimage(breakWidth * j, breakHeight * i, breakWidth, breakHeight);
                    icon = new ImageIcon(bufnew);//将图像转化成图标
                } else { //使最后一张图像为空白图像
                    icon = new ImageIcon("F:/Image/Puzzle_game/background2.jpg");//一张空白图像
                }
                cells[num] = new Cell(icon, num, breakWidth, breakHeight);//添加图标到每一个BUTTON按钮上面
                cells[num].setLocation(breakWidth * j, breakHeight * i);
            }
        }

        BlankCell = cells[cells.length - 1];//初始化空白格
        for (int i = 0; i < cells.length; i++) {
            this.add(cells[i]);//将每一个按钮添加到当前这个面板上面
            if (i < cells.length - 1)
                cells[i].addMouseListener(this);//空白格不添加监听机制
        }
    }

    public void OutOfOrder()//乱序----打乱图片的排布顺序
    {
        Random random = new Random();
        for (int i = 0; i < cells.length; i++) {
            int index1 = random.nextInt(cells.length);//cells的长度是9，但是他的上限是9，取不到9，所取值范围是0-8
            int index2 = random.nextInt(cells.length);
            int x = cells[index1].getX();
            int y = cells[index1].getY();//获取下标是index1的数组元素按钮的坐标
            cells[index1].setLocation(cells[index2].getX(), cells[index2].getY());
            cells[index2].setLocation(x, y);
        }
    }

    public boolean IsWin()//判断游戏玩家是否赢
    {
        for (int i = 0; i < cells.length; i++) {
            int x = cells[i].getX();
            int y = cells[i].getY();
            if (x / (ImageWidth / col) + y / (ImageHeight / row) != i) {
                return false;
            }
        }
        return true;
    }

    public void mouseClicked(MouseEvent e) {
        Cell t = (Cell) e.getSource();
        int x = BlankCell.getX();
        int y = BlankCell.getY();
        if (t.getY() == y && t.getX() + ImageWidth / col == x)//图像向右走
        {
            t.move(Direction.RIGHT);
            BlankCell.move(Direction.LEFT);
        } else if (t.getY() == y && t.getX() - ImageWidth / col == x)//图像向左走
        {
            t.move(Direction.LEFT);
            BlankCell.move(Direction.RIGHT);
        } else if (t.getX() == x && t.getY() + ImageHeight / row == y)//图像向上走
        {
            t.move(Direction.UP);
            BlankCell.move(Direction.DOWN);
        } else if (t.getX() == x && t.getY() - ImageHeight / row == y)//图像向下走
        {
            t.move(Direction.DOWN);
            BlankCell.move(Direction.UP);
        }

        if (IsWin()) {
            int choice = JOptionPane.showConfirmDialog(null, "恭喜您过关了是否还来一局？", "提示", JOptionPane.YES_NO_OPTION);
            if (choice == 0)//表示再来一局
            {
                this.OutOfOrder();
            } else//表示退出游戏
                System.exit(1);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}