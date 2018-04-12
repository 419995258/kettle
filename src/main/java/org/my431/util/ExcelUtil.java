package org.my431.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * ExcelUtil类<br>
 * <p>
 * 用于导入导出Excel
 * 
 * @author JiahaoWang
 * 
 */
public class ExcelUtil {
	
	public static void WriteExcel(String templatePath, String title,int startRow,
			List dataList, OutputStream ops) throws BiffException, IOException,
			RowsExceededException, WriteException {

		Workbook wb = Workbook.getWorkbook(new File(templatePath));
		ByteArrayOutputStream targetFile = new ByteArrayOutputStream();
		WritableWorkbook wwb = Workbook.createWorkbook(targetFile, wb);
		WritableSheet wws = wwb.getSheet(0);
		Label label = (Label) wws.getWritableCell(0, 0);
		WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10,
				WritableFont.NO_BOLD);
		WritableCellFormat cellFormat = new WritableCellFormat(font,NumberFormats.TEXT);
		label.setString(title);
		cellFormat.setWrap(true);
		cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
		cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		for (int i = 0; i < dataList.size(); i++) {
			Object[] objs = (Object[]) dataList.get(i);
			for (int j = 0; j < objs.length; j++) {
				wws.addCell(new Label(j, i + startRow, String.valueOf(objs[j]), cellFormat));
			}
		}
		wwb.write();
		wwb.close();
		wb.close();
		targetFile.writeTo(ops);
		targetFile.close();
	}
}
