package org.my431.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class ReadExcel {
	/**
	 * 不区分版本读取EXCEL
	 * @param filename,sheetname(用来读取特定标记工作簿)
	 * @return
	 * @throws IOException
	 */
	public static List<Object[]> read(String filename,String sheetname) throws IOException{
		try{
			if(isExcel2003(filename)){
				return readByHSSF(filename,sheetname);
			}else {
				return readByXSSF(filename,sheetname);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 读取2007EXCEL  后缀xlsx
	 * @param filename
	 * @return
	 * @throws IOException
	 * 修改 过滤空白数据<br/>
	 * 
	 */
	public static List<Object[]> readByXSSF(String filename,String sheetname) throws IOException{
		List<Object[]> list = new ArrayList<Object[]>();
		/******************读取本地文件start**********************************************/
		//指定要读取的Excel文件
		//File file = new File(filename);
		//创建输入流
		//FileInputStream fis = new FileInputStream(file);
		/******************读取本地文件end**********************************************/
		/******************读取网络文件start**********************************************/
		URL url =new URL(filename); // 创建URL
        URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
        urlconn.connect();
        HttpURLConnection httpconn =(HttpURLConnection)urlconn;
        int httpResult = httpconn.getResponseCode();
        InputStream fis=null;
        if(httpResult != HttpURLConnection.HTTP_OK){ // 不等于HTTP_OK说明连接不成功
        	System.out.print("无法连接到");
        }else {
        	//urlconn.getInputStream();
        	fis=urlconn.getInputStream();
        	
		}	
		
		/******************读取网络文件end**********************************************/
		//创建XSSFWorkbook对象
		//读写xls和xlsx格式时，HSSFWorkbook针对xls，XSSFWorkbook针对xlsx
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet childSheet = null;
		if(sheetname!=null && !"".equals(sheetname)){
			childSheet = wb.getSheet(sheetname);
		}else{
			//获得第一个sheet工作表
			 childSheet = wb.getSheetAt(0);
		}
				
		//从第一行开始 A1 表头
		CellReference cellReference = new CellReference("A1"); 		
		//显示行数（如果中间隔行的话getPhysicalNumberOfRows就不能读取到所有的行）
		//System.out.println(childSheet.getPhysicalNumberOfRows());
		//有行数
		/** 得到Excel的行数 */  
		System.out.println("从0开始有行数："+childSheet.getLastRowNum()+";从1开始有行数："+childSheet.getPhysicalNumberOfRows());
		//该标记是判断是否是空白值
		boolean flag = false; 
		for (int r = 0; r < childSheet.getPhysicalNumberOfRows();r++) {
			//获取表格中的行
			XSSFRow row = childSheet.getRow(r);
			int allColumnNum= childSheet.getRow(0).getLastCellNum()+1;//从0开始
			Object[] obj = new Object[allColumnNum];
			//检查是否是空行（即没有任何数据、格式）
			if(row == null){  
                 // 如果是空行（即没有任何数据、格式），直接把它以下的数据往上移动  
				//childSheet.shiftRows(i+1, childSheet.getLastRowNum(),-1);
				list.add(obj);
                continue;  
             }  
             flag = false;  
             
             //循环检查该行是否都是空白值
             for(Cell c:row){  
                 if(c.getCellType() != Cell.CELL_TYPE_BLANK){  
                     flag = true;  
                     break;  
                 }  
             }
           
           //定义数组保存一行数据 根据标题的列数而定
			//有不是空白值循环
			if(flag){
				//遍历每个单元格
				 for(int c = 0; c < row.getPhysicalNumberOfCells(); c++){ 
					//获取行中的单元格
 					XSSFCell cell = row.getCell(c);
					 //如果数值类型不为空值 执行
 					if(null!=cell){
						switch (cell.getCellType()) {  
                        case XSSFCell.CELL_TYPE_NUMERIC: // 数字  
                            // System.out.print(cell.getNumericCellValue() + "   ");
                        	//obj[j] = cell.getNumericCellValue();
                        	if(HSSFDateUtil.isCellDateFormatted(cell)){
                        		Date date = (Date) cell.getDateCellValue();
                        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        		obj[c] = sdf.format(date);
                        		//obj[j] = cell.getDateCellValue();
                        	}else{
                        		//obj[j] = cell.getNumericCellValue();
                        		String str="";  
                                double doubleValue = cell.getNumericCellValue();  
                                 // 是否为数值型  
                                 if (doubleValue - (int) doubleValue < Double.MIN_VALUE) {   
	                                 // 是否为int型  
	                                 str = Integer.toString((int) doubleValue);  
                                 } else {   
                                    //System.out.println("double.....");  
                                    // 是否为double型  
                                    str = Double.toString(cell.getNumericCellValue());  
                                    DecimalFormat df = new DecimalFormat("#");  
                                    str= df.format(cell.getNumericCellValue());  
                                 }  
                                 obj[c] = "" + str;  
                        	}
                            break;  
                        case XSSFCell.CELL_TYPE_STRING: // 字符串  
                            //System.out.print(cell.getStringCellValue()+ "   ");
                            obj[c] = cell.getStringCellValue();
                            break;  
                        case XSSFCell.CELL_TYPE_BOOLEAN: // Boolean  
                        	obj[c] = cell.getBooleanCellValue();
                        	break;  
                        case XSSFCell.CELL_TYPE_FORMULA: // 公式  
                            obj[c] = cell.getCellFormula();
                            break;  
                        case XSSFCell.CELL_TYPE_BLANK: // 空值  
                            obj[c] = "";
                        	break;  
                        case XSSFCell.CELL_TYPE_ERROR: // 故障  
                            obj[c] = "-1";
                            break;  
                        default:  
                        	obj[c] = "-1";
                        	break; 
 						}
	                 }else{
						//System.out.print("-   ");
						obj[c] = "";
 					} 
	             }
				 //list添加值
				 list.add(obj); 
                 //i++;  
                 //continue;  
             }else{//如果是空白行（即可能没有数据，但是有一定格式）  
            	 /*if(i == childSheet.getLastRowNum())//如果到了最后一行，直接将那一行remove掉  
                	childSheet.removeRow(row);  
                 else//如果还没到最后一行，则数据往上移一行  
                	childSheet.shiftRows(i+1, childSheet.getLastRowNum(),-1);*/ 
            	 list.add(obj);
             } 
		}
		System.out.println("检查后总行数："+(childSheet.getLastRowNum()+1));
		System.out.println("检查后集合的数量："+list.size());
		fis.close();
		
		return list;
	}
	
	/**
	 * 读取2003EXCEL  后缀xls
	 * @param filename
	 * @return
	 * @throws IOException
	 * 
	 */
	public static List<Object[]> readByHSSF(String filename,String sheetname) throws IOException{
		List<Object[]> list = new ArrayList<Object[]>();
		
		//指定要读取的Excel文件
		//File file = new File(filename);
		//创建输入流
		//FileInputStream fis = new FileInputStream(file);
		/******************读取网络文件start**********************************************/
		URL url =new URL(filename); // 创建URL
        URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
        urlconn.connect();
        HttpURLConnection httpconn =(HttpURLConnection)urlconn;
        int httpResult = httpconn.getResponseCode();
        InputStream fis=null;
        if(httpResult != HttpURLConnection.HTTP_OK){ // 不等于HTTP_OK说明连接不成功
        	System.out.print("无法连接到");
        }else {
        	//urlconn.getInputStream();
        	fis=urlconn.getInputStream();
        	
		}	
		
		/******************读取网络文件end**********************************************/
		//创建HSSFWorkbook对象
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		
		HSSFSheet childSheet = null;
		if(sheetname!=null && !"".equals(sheetname)){
			childSheet = wb.getSheet(sheetname);
		}else{
			//获得第一个sheet工作表
			 childSheet = wb.getSheetAt(0);
		}
		//从第一行开始 A1 表头
		CellReference cellReference = new CellReference("A1"); 		
		//显示行数（如果中间隔行的话getPhysicalNumberOfRows就不能读取到所有的行）
		//System.out.println(childSheet.getPhysicalNumberOfRows());
		/** 得到Excel的行数 */  
		System.out.println("从0开始有行数："+childSheet.getLastRowNum()+";从1开始有行数："+childSheet.getPhysicalNumberOfRows());
		//该标记是判断是否是空白值
		boolean flag = true; 
		//遍历EXCEL数据 childSheet.getPhysicalNumberOfRows() 原来写的
		//qyb 修改 childSheet.getLastRowNum() 2014.11.12
		//for (int i = cellReference.getRow(); i <= childSheet.getLastRowNum();) {
		for (int r = 0; r < childSheet.getPhysicalNumberOfRows();r++) {
			//获取表格中的行
			HSSFRow row = childSheet.getRow(r);
			int allColumnNum= childSheet.getRow(0).getLastCellNum()+1;//从0开始
			Object[] obj = new Object[allColumnNum];
			//System.out.println(row.getPhysicalNumberOfCells());//从1开始
			//显示列数
			//System.out.println("列数："+row.getLastCellNum());
			
			//检查是否是空行（即没有任何数据、格式）
			if(row == null){  
                 // 如果是空行（即没有任何数据、格式），直接把它以下的数据往上移动  
				//childSheet.shiftRows(i+1, childSheet.getLastRowNum(),-1);  
				list.add(obj);
				continue;  
             }  
             flag = false;  
             
             //循环检查该行是否都是空白值
             for(Cell c:row){  
                 if(c.getCellType() != Cell.CELL_TYPE_BLANK){  
                     flag = true;  
                     break;  
                 }  
             }
			//有不是空白值循环
			if(flag){
				//遍历每个单元格
				 for(int c = 0; c < row.getPhysicalNumberOfCells(); c++){ 
					//获取行中的单元格
 					HSSFCell cell = row.getCell(c);
					 //如果数值类型不为空值 执行
 					if(null!=cell){
						switch (cell.getCellType()) {  
                        case XSSFCell.CELL_TYPE_NUMERIC: // 数字  
                            // System.out.print(cell.getNumericCellValue() + "   ");
                        	//obj[j] = cell.getNumericCellValue();
                        	if(HSSFDateUtil.isCellDateFormatted(cell)){
                        		Date date = (Date) cell.getDateCellValue();
                        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        		obj[c] = sdf.format(date);
                        		//obj[j] = cell.getDateCellValue();
                        	}else{
                        		//obj[j] = cell.getNumericCellValue();
                        		String str="";  
                                double doubleValue = cell.getNumericCellValue();  
                                 // 是否为数值型  
                                 if (doubleValue - (int) doubleValue < Double.MIN_VALUE) {   
	                                 // 是否为int型  
	                                 str = Integer.toString((int) doubleValue);  
                                 } else {   
                                    //System.out.println("double.....");  
                                    // 是否为double型  
                                    str = Double.toString(cell.getNumericCellValue());  
                                    DecimalFormat df = new DecimalFormat("#");  
                                    str= df.format(cell.getNumericCellValue());  
                                 }  
                                 obj[c] = "" + str;
                        	}
                            break;  
                        case XSSFCell.CELL_TYPE_STRING: // 字符串  
                            //System.out.print(cell.getStringCellValue()+ "   ");
                            obj[c] = cell.getStringCellValue();
                            break;  
                        case XSSFCell.CELL_TYPE_BOOLEAN: // Boolean  
                            //System.out.println(cell.getBooleanCellValue() + "   ");  
                        	obj[c] = cell.getBooleanCellValue();
                        	break;  
                        case XSSFCell.CELL_TYPE_FORMULA: // 公式  
                            //System.out.print(cell.getCellFormula() + "   ");
                            obj[c] = cell.getCellFormula();
                            break;  
                        case XSSFCell.CELL_TYPE_BLANK: // 空值  
                            obj[c] = "";
                        	break;  
                        case XSSFCell.CELL_TYPE_ERROR: // 故障  
                            obj[c] = "-1";
                            break;  
                        default:  
                            //System.out.print("未知类型   ");  
                        	obj[c] = "-1";
                        	break; 
 						}
	                 }else{
						//System.out.print("-   ");
						obj[c] = "";
 					} 
	             }
				 //list添加值
				 list.add(obj); 
                 //r++;  
                 //continue;  
             }else{//如果是空白行（即可能没有数据，但是有一定格式）  
            	 /*if(r == childSheet.getLastRowNum())//如果到了最后一行，直接将那一行remove掉  
                	childSheet.removeRow(row);  
                 else//如果还没到最后一行，则数据往上移一行  
                	childSheet.shiftRows(r+1, childSheet.getLastRowNum(),-1);*/  
            	 list.add(obj);
             } 
		}
		System.out.println("检查后总行数："+(childSheet.getLastRowNum()+1));
		System.out.println("检查后集合的数量："+list.size());
		fis.close();
		return list;
	}
	
	
	/**
	 * 
		* 函 数 名 :main 
		* 功能描述：测试方法
		* 参数描述:   
		* 返回值  :void
		* 创 建 人:秦岩宾
		* 日    期:2014-11-15 上午11:54:01
		* 修 改 人: 
		* 日    期:
	 */
	 public static void main(String[] args) {  
		 List<Object[]> list = new ArrayList<Object[]>();
			//创建XSSFWorkbook对象
				//读写xls和xlsx格式时，HSSFWorkbook针对xls，XSSFWorkbook针对xlsx
			 HSSFWorkbook wb = null;
				try {
					wb = new HSSFWorkbook(new FileInputStream("E:\\d.xls"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				HSSFSheet childSheet = null;
				//获得第一个sheet工作表
				childSheet = wb.getSheetAt(0);
				//从第一行开始 A1
				CellReference cellReference = new CellReference("A1"); 		
				//显示行数（如果中间隔行的话getPhysicalNumberOfRows就不能读取到所有的行）
				System.out.println(childSheet.getPhysicalNumberOfRows());
				//有行数
				System.out.println("有行数："+childSheet.getLastRowNum());
				boolean flag = false; 
				//遍历EXCEL数据 childSheet.getPhysicalNumberOfRows() 原来写的
				//qyb 修改 childSheet.getLastRowNum() 2014.11.12
				for (int i = cellReference.getRow(); i <= childSheet.getLastRowNum();) {
					//获取表格中的行
					HSSFRow row = childSheet.getRow(i);
					//显示列数（如果中间隔列的话getPhysicalNumberOfCells就不能读取到所有列）
					//System.out.println(row.getPhysicalNumberOfCells());
					//显示列数
					//System.out.println("列数："+row.getLastCellNum());
					
					//检查是否是空行（即没有任何数据、格式）
					if(row == null){  
		                 // 如果是空行（即没有任何数据、格式），直接把它以下的数据往上移动  
						childSheet.shiftRows(i+1, childSheet.getLastRowNum(),-1);  
		                 continue;  
		             }  
		             flag = false;  
		             
		             for(Cell c:row){  
		                 if(c.getCellType() != Cell.CELL_TYPE_BLANK){  
		                     flag = true;  
		                     break;  
		                 }  
		             } 
		             
		           //定义数组保存一行数据 根据标题的列数而定
					Object[] obj = new Object[childSheet.getRow(0).getLastCellNum()+1];//childSheet.getRow(0).getLastCellNum()+1
					if(flag){
						//遍历每个单元格
						 for(int j = 0; j < row.getLastCellNum(); j++){ 
							//获取行中的单元格
		 					HSSFCell cell = row.getCell(j);
							 //如果数值类型不为空值 执行
		 					if(null!=cell){
	 							switch (cell.getCellType()) {  
	 	                        case XSSFCell.CELL_TYPE_NUMERIC: // 数字  
	 	                            // System.out.print(cell.getNumericCellValue() + "   ");
	 	                        	//obj[j] = cell.getNumericCellValue();
	 	                        	if(HSSFDateUtil.isCellDateFormatted(cell)){
	 	                        		Date date = (Date) cell.getDateCellValue();
	 	                        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 	                        		obj[j] = sdf.format(date);
	 	                        		//obj[j] = cell.getDateCellValue();
	 	                        	}else{
	 	                        		obj[j] = cell.getNumericCellValue();
	 	                        	}
	 	                            break;  
	 	                        case XSSFCell.CELL_TYPE_STRING: // 字符串  
	 	                            //System.out.print(cell.getStringCellValue()+ "   ");
	 	                            obj[j] = cell.getStringCellValue();
	 	                            break;  
	 	                        case XSSFCell.CELL_TYPE_BOOLEAN: // Boolean  
	 	                            //System.out.println(cell.getBooleanCellValue() + "   ");  
	 	                        	obj[j] = cell.getBooleanCellValue();
	 	                        	break;  
	 	                        case XSSFCell.CELL_TYPE_FORMULA: // 公式  
	 	                            //System.out.print(cell.getCellFormula() + "   ");
	 	                            obj[j] = cell.getCellFormula();
	 	                            break;  
	 	                        case XSSFCell.CELL_TYPE_BLANK: // 空值  
	 	                            obj[j] = "";
	 	                        	break;  
	 	                        case XSSFCell.CELL_TYPE_ERROR: // 故障  
	 	                            obj[j] = "-1";
	 	                            break;  
	 	                        default:  
	 	                        	obj[j] = "-1";
	 	                        	break; 
		 						}
			                 }else{
	 							//System.out.print("-   ");
	 							obj[j] = "";
	 							break;
		 					} 
			             }
						 list.add(obj); 
		                 i++;  
		                 continue;  
		             }else{//如果是空白行（即可能没有数据，但是有一定格式）  
		            	 if(i == childSheet.getLastRowNum())//如果到了最后一行，直接将那一行remove掉  
		                	childSheet.removeRow(row);  
		                 else//如果还没到最后一行，则数据往上移一行  
		                	childSheet.shiftRows(i+1, childSheet.getLastRowNum(),-1);  
		             } 
					
		             
				}
				System.out.println("检查后总行数："+(childSheet.getLastRowNum()+1));
				System.out.println(list.size());
		 
     }  
	 public static boolean isExcel2003(String filePath){  
	     return filePath.matches("^.+\\.(?i)(xls)$");  
	 }  
	 public static boolean isExcel2007(String filePath){  
	     return filePath.matches("^.+\\.(?i)(xlsx)$");  
	 }  
}
