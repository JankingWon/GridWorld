import java.awt.Image;
import java.awt.image.BufferedImage;

import imagereader.IImageProcessor;

public class ImplementImageProcessor implements IImageProcessor{

    @Override
    public Image showChanelB(Image img) {
        //提取蓝色通道
        return processor(img, "B");
    }

    @Override
    public Image showChanelG(Image img) {
        //提取绿色通道
        return processor(img, "G");
    }

    @Override
    public Image showChanelR(Image img) {
        //提取红色通道
        return processor(img, "R");
    }

    @Override
    public Image showGray(Image img) {
        //彩色图像转换成灰度图像
        return processor(img, "GRAY");
    }
    
    public Image processor(Image img, String color) {
      //将Image类型转换为BufferedImage
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        bimage.getGraphics().drawImage(img, 0, 0 , null);
        //长度为3的数组存储每个像素上的三元色值
        int[] rgb = new int[3];  
        //遍历宽度(注意这里先遍历宽,再遍历高,而不是像读取文件一样先竖向再横向)
        for (int i = 0; i < width; i++) {  
            //遍历高度
            for (int j = 0; j < height; j++) {  
             // 下面三行代码将一个数字转换为RGB数字
                int pixel = bimage.getRGB(i, j);   
                rgb[0] = (pixel & 0xff0000) >> 16;  
                rgb[1] = (pixel & 0xff00) >> 8;  
                rgb[2] = (pixel & 0xff);  
                if(color.equals("R")) {
                  //把B和G上的颜色去掉,只提取出R通道的值
                    pixel = (255 << 24) | (rgb[0] << 16) | (0 << 8)| 0;
                }else if(color.equals("G")){
                  //把R和G上的颜色去掉,只提取出G通道的值
                    pixel = (255 << 24) | (0 << 16) | (rgb[1] << 8)| 0;
                }else if(color.equals("B")) {
                    //把R和B上的颜色去掉,只提取出B通道的值
                    pixel = (255 << 24) | (0 << 16) | (0 << 8)| rgb[2];
                }else{
                  //将三个通道的值都设为I = 0.299 * R + 0.587 * G + 0.114 *B
                    int gray = (int) (0.299 * rgb[0] + 0.587 * rgb[1] + 0.114 *rgb[2]);
                    rgb[0] = gray;
                    rgb[1] = gray;
                    rgb[2] = gray;
                    pixel = (255 << 24) | (rgb[0] << 16) | (rgb[1] << 8)| rgb[2];
                }
                bimage.setRGB(i, j, pixel);
            }  
        }  
        //返回这个Image类型
        return bimage;
    }
    
}
