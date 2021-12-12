package Dome02;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameCore extends JPanel implements MouseListener {
    int imageWidth;
    int imageHeight;

    private int row = 4;
    private int col = 4;
//    private Cell cell[] = new Cell[row * col];

    public GameCore(){
        this.setLayout(null);
        init();
    }

    public void init(){
        BufferedImage completePicture = null; // 图片缓冲区，即将图片读到内存中来
           BufferedImage breakPicture = null;
        int breakWidth = 0, breakHeight = 0;
        try {
         completePicture = ImageIO.read(new File("E:\\HP\\Desktop\\JAVA\\test.jpg"));
            imageWidth = completePicture.getWidth();
            imageHeight = completePicture.getHeight();
            System.out.println("ImageWidth->" + imageWidth + "ImageHeight->" + imageHeight);

            breakWidth = imageWidth/3;
            breakHeight = imageHeight/3;

        } catch (IOException e) {
            e.printStackTrace(); // 显示完整的错误信息
        }

    }


















    // 鼠标点击事件响应
    @Override
    public void mouseClicked(MouseEvent e) {

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
