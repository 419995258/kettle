package org.my431.util;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
public class Table2Pdf {
	//public static  String RESULT = "d:\\TableDemo2.pdf";
    public static Paragraph getParagraph(String str){
    	BaseFont bfChinese = null;
        try {
        	bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
    				false);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
        Font font = new Font(bfChinese, 8, Font.NORMAL); 
        
        return new Paragraph(str,font);
    }
    
    public static Paragraph getParagraph(String str,int fontSize,int style){
    	BaseFont bfChinese = null;
        try {
        	bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
    				false);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
        //Font font = new Font(bfChinese, fontSize, Font.BOLD); 
        Font font = new Font(bfChinese, fontSize, style); 
        
        return new Paragraph(str,font);
    }
    
    /**
     * 获得单元格
     * author  90  
     * on 2016-7-28
     * @param str
     * @param rowSpan
     * @param colSpan
     * @return
     */
    public static PdfPCell getCell(String str,int rowSpan,int colSpan){
    	PdfPCell cell=new PdfPCell(getParagraph(str));
    	cell.setMinimumHeight(18); 
    	if(rowSpan>0){
    		cell.setRowspan(rowSpan);
    	}
    	if(colSpan>0){
    		cell.setColspan(colSpan);
    	}
        cell.setBorderColor(BaseColor.BLACK);
        cell.setPadding(2f); 
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置字体垂直居中；
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);////设置字体水平居中，只能通过cell来居中；
        return cell;
    }
    
    public static PdfPCell getCell(String str,int rowSpan,int colSpan,int algin,int valign,int fontSize,int style){
    	PdfPCell cell=new PdfPCell(getParagraph(str, fontSize,style));
    	cell.setMinimumHeight(18); 
    	if(rowSpan>0){
    		cell.setRowspan(rowSpan);
    	}
    	if(colSpan>0){
    		cell.setColspan(colSpan);
    	}
        cell.setBorderColor(BaseColor.BLACK);
        cell.setPadding(2f); 
        cell.setVerticalAlignment(valign);//设置字体垂直居中；
        cell.setHorizontalAlignment(algin);////设置字体水平居中，只能通过cell来居中；
        return cell;
    }
    
  
    public static void main(String[] args) throws IOException, DocumentException {
        //Table2Pdf.createPdfV2(RESULT);
    }
}
