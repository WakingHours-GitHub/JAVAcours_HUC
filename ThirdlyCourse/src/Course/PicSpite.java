package Course;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

// 分割图片：

public class PicSpite {
    /**
     * 分割图片
     *
     * @param socPath
     * @param cutNumber
     * @param savePath
     * @return boolean
     * @author WakingHours
     */

    public  static boolean cutImage(File socPath, int cutNumber, String savePath){
        try{
            BufferedImage socPic = ImageIO.read(socPath);
            int socWidth = socPic.getWidth(); // 总的宽度
            int socHeight = socPic.getHeight(); // 总的高度

            int breakWidth = socWidth/cutNumber; // 分割后单个图片的宽度
            int breakHeight = socHeight/cutNumber; // 分割后单个图片的高度

            for (int i = 0; i < cutNumber; i++){
                for (int j = 0; j < cutNumber; j++){
                    // 将切割完的图片以特定的名字写入文件夹
                    ImageIO.write(socPic.getSubimage(j * breakWidth,i * breakHeight, breakWidth, breakHeight),"jpg",
                            new File(savePath + "\\" + (i * cutNumber + j) + ".jpg"));
                }
            }
            return true;

        }catch (Exception e){
            e.getStackTrace(); // 打印详细的错误信息
            return false;
        }
    }


}
