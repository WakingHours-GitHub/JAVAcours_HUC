package Course;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class SocPicFrame extends JFrame {
    int imageWidth;
    int imageHeight;
    public SocPicFrame(String socPath){
        super("原始图片");
        try {
            // 将读入的文件放在图片缓冲区里面
            BufferedImage socPic = ImageIO.read(new File(socPath+"test.jpg"));
            imageWidth = socPic.getWidth();
            imageHeight = socPic.getHeight();

            ImageIcon icon = new ImageIcon(socPic); // icon 需要装在label里面
            JLabel label = new JLabel(icon); // 直接用构造方法装填
            this.add(label);

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setBounds(50,50,imageWidth,imageHeight); // 坐标，大小
        this.setVisible(true); // 设置可见

    }

}
