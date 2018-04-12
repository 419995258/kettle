package org.my431.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrCode {  
	  private static final int BLACK = 0xFF000000; 
	  private static final int WHITE = 0xFFFFFFFF;  
    // 图片宽度的一般  
//    private static final int IMAGE_WIDTH = 15;  
//    private static final int IMAGE_HEIGHT = 15;  
//    private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;  
    private static final int FRAME_WIDTH = 1;  
  
    // 二维码写码器  
    private static MultiFormatWriter mutiWriter = new MultiFormatWriter();  
  
    /** 
     *  
     * @param content 
     *            二维码显示的文本 
     * @param width 
     *            二维码的宽度 
     * @param height 
     *            二维码的高度 
     * @param srcImagePath 
     *            中间嵌套的图片 
     * @param destImagePath 
     *            二维码生成的地址 
     */  
    public static void encode(String content, int width, int height,  
            String srcImagePath, String destImagePath) {  
        try {  
            // ImageIO.write 参数 1、BufferedImage 2、输出的格式 3、输出的文件  
            ImageIO.write(genBarcode(content, width, height, srcImagePath),  
                    "jpg", new File(destImagePath));  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (WriterException e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 得到BufferedImage 
     *  
     * @param content 
     *            二维码显示的文本 
     * @param width 
     *            二维码的宽度 
     * @param height 
     *            二维码的高度 
     * @param srcImagePath 
     *            中间嵌套的图片 
     * @return 
     * @throws WriterException 
     * @throws IOException 
     */  
    private static BufferedImage genBarcode(String content, int width,  
            int height, String srcImagePath) throws WriterException,  
            IOException {  
    	Integer IMAGE_WIDTH=0;
    	Integer IMAGE_HEIGHT=0;
    	if(width==500){
    		IMAGE_WIDTH=100;
    		IMAGE_HEIGHT=100;
    	}
    	if(width==90){
    		IMAGE_WIDTH=25;
    		IMAGE_HEIGHT=25;
    	}
        // 读取源图像  
    	 File file = new File(srcImagePath);  
         BufferedImage scaleImage = ImageIO.read(file);  
          
        java.util.Hashtable hint = new java.util.Hashtable();  
        hint.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
        hint.put(EncodeHintType.MARGIN, 1);
        
        // 生成二维码  
        BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE,width, height, hint);  
  
        BufferedImage image = toBufferedImage(matrix);  
        
        Graphics2D gs = image.createGraphics();  
        int x = (width - IMAGE_WIDTH) / 2;  
        int y = (height - IMAGE_HEIGHT) / 2;  
        gs.drawImage(scaleImage, x, y, IMAGE_WIDTH, IMAGE_HEIGHT, null);  
    //    Shape shape = new RoundRectangle2D.Float(x, y, IMAGE_WIDTH, IMAGE_HEIGHT, 0, 0);  
     //   gs.setStroke(new BasicStroke(3f));  
    //    gs.draw(shape); 

        gs.dispose();  

        return image;  
    }  
  
    /** 
     * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标 
     *  
     * @param srcImageFile 
     *            源文件地址 
     * @param height 
     *            目标高度 
     * @param width 
     *            目标宽度 
     * @param hasFiller 
     *            比例不对时是否需要补白：true为补白; false为不补白; 
     * @throws IOException 
     */  
    private static BufferedImage scale(String srcImageFile, int height,  
            int width, boolean hasFiller) throws IOException {  
        double ratio = 0.0; // 缩放比例  
        File file = new File(srcImageFile);  
        BufferedImage srcImage = ImageIO.read(file);  
        Image destImage = srcImage.getScaledInstance(width, height,  
                BufferedImage.SCALE_SMOOTH);  
        // 计算比例  
        if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {  
            if (srcImage.getHeight() > srcImage.getWidth()) {  
                ratio = (new Integer(height)).doubleValue()  
                        / srcImage.getHeight();  
            } else {  
                ratio = (new Integer(width)).doubleValue()  
                        / srcImage.getWidth();  
            }  
            AffineTransformOp op = new AffineTransformOp(AffineTransform  
                    .getScaleInstance(ratio, ratio), null);  
            destImage = op.filter(srcImage, null);  
        }  
        if (hasFiller) {// 补白  
            BufferedImage image = new BufferedImage(width, height,  
                    BufferedImage.TYPE_INT_RGB);  
            Graphics2D graphic = image.createGraphics();  
            graphic.setColor(Color.white);  
            graphic.fillRect(0, 0, width, height);  
            if (width == destImage.getWidth(null))  
                graphic.drawImage(destImage, 0, (height - destImage  
                        .getHeight(null)) / 2, destImage.getWidth(null),  
                        destImage.getHeight(null), Color.white, null);  
            else  
                graphic.drawImage(destImage,  
                        (width - destImage.getWidth(null)) / 2, 0, destImage  
                                .getWidth(null), destImage.getHeight(null),  
                        Color.white, null);  
            graphic.dispose();  
            destImage = image;  
        }  
        return (BufferedImage) destImage;  
    }  
    
    
    public static BufferedImage toBufferedImage(BitMatrix matrix) { 
        int width = matrix.getWidth(); 
        int height = matrix.getHeight(); 
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
        for (int x = 0; x < width; x++) { 
          for (int y = 0; y < height; y++) { 
            image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE); 
          } 
        } 
        return image; 
      } 
       
         
      public static void writeToFile(BitMatrix matrix, String format, File file) 
          throws IOException { 
        BufferedImage image = toBufferedImage(matrix); 
        if (!ImageIO.write(image, format, file)) { 
          throw new IOException("Could not write an image of format " + format + " to " + file); 
        } 
      } 
       
         
      public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) 
          throws IOException { 
        BufferedImage image = toBufferedImage(matrix); 
        if (!ImageIO.write(image, format, stream)) { 
          throw new IOException("Could not write an image of format " + format); 
        } 
      }  
 
      public static void createQrCode(String format,int width,int height,String text,String outPutPath){
    	  try {
              Hashtable hints = new Hashtable(); 
              //内容所使用编码 
              hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
              hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); 
              hints.put(EncodeHintType.MARGIN, 1);
              BitMatrix bitMatrix = new MultiFormatWriter().encode(text, 
                      BarcodeFormat.QR_CODE, width, height, hints); 
              //生成二维码 
              File outputFile = new File(outPutPath); 
              writeToFile(bitMatrix, format, outputFile); 
		} catch (Exception e) {
		}
 
      }
}  