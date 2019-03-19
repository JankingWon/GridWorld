import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import imagereader.IImageIO;

public class ImplementImageIO implements IImageIO{
    @Override
    public Image myRead(String arg0) throws IOException {
        //用文件流读取bum图片
        FileInputStream file = new FileInputStream(arg0);
        //这个字节数组用于保存图片的宽度和高度,而54是为了跳过前面的"位图头"和"位图信息",为后面读取RGB值提供便利
        byte[] bytes = new byte[54];  
        file.read(bytes);  
        //通过将字节数组转化为int值得到图片的宽和高
        int width = byteChangeToInt(bytes[21], bytes[20], bytes[19], bytes[18]);  
        int height = byteChangeToInt(bytes[25], bytes[24], bytes[23], bytes[22]);
        //得到每行要跳过的数字(与windows 系统机制有关)
        int skip = 4 - width*3%4;
        //这是用一个一维数组存储每个像素的颜色
        int[] rgb = new int[width * height];
        int index = 0;
        //遍历高度
        for(int i = height - 1;i > 0;i--){  
            //遍历宽度
            //这里是因为存储的时候是从下到上保存的,而用读取read读取的时候却是从上到下,所以需要改变一下放入rgb数组的顺序
            index = i * width;
            for(int j=0;j < width;j++){  
                //保存的时候是以B,G,R的顺序,而我们需要r,g,b的顺序
                int blue = file.read();
                int green = file.read();
                int red = file.read();
                //通过移位将三种颜色组成一个新的int值
                rgb[index++] = (255 << 24) | (red << 16) | (green << 8)| blue;
            }  
            if(skip!=4) {
                file.skip(skip);
            }
                
        }
        //关闭文件
        file.close();
        //调用creatImage方法创建创建一个新的Image
        return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, rgb, 0, width));
    }
    
    //byte类型转int类型的方法
    private int byteChangeToInt(byte byte1,byte byte2,byte byte3,byte byte4) {  
        int value1 = ((int)byte1&0xff)<<24;  
        int value2 = ((int)byte2&0xff)<<16;  
        int value3 = ((int)byte3&0xff)<<8;  
        int value4 = (int)byte4&0xff;  
        return value1|value2|value3|value4;  
    }
    
    @Override
    public Image myWrite(Image img, String arg1) throws IOException {
        //因为Image是一个抽象类,BufferedImage类是它的子类,如果img参数本身就是BufferedImage类就可以直接返回
        if (img instanceof BufferedImage)
        {
            return img;
        }
        //将Image类转化为BufferedImage类
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);
        bimage.getGraphics().drawImage(img, 0, 0 , null);
        //写进文件中,格式是bmp
        ImageIO.write(bimage, "bmp", new File(arg1));
        return img;    
    }
    
}
