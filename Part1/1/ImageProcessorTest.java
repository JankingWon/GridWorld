import static org.junit.Assert.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;

public class ImageProcessorTest {
    
    @Test
    public void testR() {
        //测试提取第一个图片的红色通道
        testFunction(1, "/home/janking/Desktop/bmptest/goal/1_red_goal.bmp", "R");
        //测试提取第二个图片的红色通道
        testFunction(2, "/home/janking/Desktop/bmptest/goal/2_red_goal.bmp", "R");
    }
    
    @Test
    public void testG() {
      //测试提取第一个图片的绿色通道
        testFunction(1, "/home/janking/Desktop/bmptest/goal/1_green_goal.bmp", "G");
      //测试提取第二个图片的绿色通道
        testFunction(2, "/home/janking/Desktop/bmptest/goal/2_green_goal.bmp", "G");
    }
    
    @Test
    public void testB() {
      //测试提取第一个图片的蓝色通道
        testFunction(1, "/home/janking/Desktop/bmptest/goal/1_blue_goal.bmp", "B");
      //测试提取第二个图片的蓝色通道
        testFunction(2, "/home/janking/Desktop/bmptest/goal/2_blue_goal.bmp", "B");
    }
    
    @Test
    public void testGray() {
      //测试第一个彩色图像转换成灰度图像
        testFunction(1, "/home/janking/Desktop/bmptest/goal/1_gray_goal.bmp", "GRAY");
      //测试第二个彩色图像转换成灰度图像
        testFunction(2, "/home/janking/Desktop/bmptest/goal/2_gray_goal.bmp", "GRAY");
    }
    //为了避免代码过多重复设置的一个公用测试方法
    public void testFunction(int srcP, String desP, String funName) {
       
            ImplementImageProcessor test = new ImplementImageProcessor();
            try {
                //原始的图片
                BufferedImage originalImg;
                if(srcP == 1) {
                    originalImg = ImageIO.read(new File("/home/janking/Desktop/1.bmp"));
                }else {
                    originalImg = ImageIO.read(new File("/home/janking/Desktop/2.bmp"));
                }
                
                //目标的图片
                BufferedImage requiredImg = ImageIO.read(new File(desP));
                Image des;
                //判断提取通道的方式
                if(funName.equals("R")) {
                    des = test.showChanelR(originalImg);
                }else if(funName.equals("G")){
                    des = test.showChanelG(originalImg);
                }else if(funName.equals("B")){
                    des = test.showChanelB(originalImg);
                }else {
                    des = test.showGray(originalImg);
                }
                //处理后的图片
                BufferedImage processedImg = new BufferedImage(des.getWidth(null), des.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);
                processedImg.getGraphics().drawImage(des, 0, 0 , null);
                //断言宽度上的像素个数相等
                assertEquals(processedImg.getWidth(), requiredImg.getWidth());
                //断言宽高度上的像素个数相等
                assertEquals(processedImg.getHeight(), requiredImg.getHeight());
                for(int i = 0; i < requiredImg.getHeight(); i++) {
                    for(int j = 0; j < requiredImg.getWidth(); j++) {
                        //断言每个像素的RGB值相等
                        assertEquals(processedImg.getRGB(j, i), requiredImg.getRGB(j, i));
                    }
                }
            } catch (IOException e) {
                
            }
        }
    

}
