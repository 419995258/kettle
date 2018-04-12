package org.my431.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

public class FileUtil {
	public static HttpServletRequest request0;
	private static String FILE_HISTORY=null;//文件目录
	
	public FileUtil() {
		// TODO Auto-generated constructor stub
		super();
	}
	static{
		if(FILE_HISTORY==null){
			//FILE_HISTORY=ServletActionContext.getServletContext().getRealPath("/resources/logtimetask");
			//FILE_HISTORY=System.getProperty("user.dir");  
			//FILE_HISTORY=System.getProperty("user.dir");  
			//FILE_HISTORY = request0.getSession().getServletContext().getRealPath("/");
			//FILE_HISTORY =getClass().getProtectionDomain().getCodeSource().getLocation().getPath();   
			FILE_HISTORY =FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();   
			if(FILE_HISTORY.indexOf("WEB-INF")>0){
				FILE_HISTORY = FILE_HISTORY.substring(0,FILE_HISTORY.indexOf("/WEB-INF/classes"));
			}
			//System.out.println(FILE_HISTORY+"--");
		}
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//writeLogTxt("log.txt","在"+DateFormater.DateToString(new Date(), "yyyy-MM-dd")+"更新");
		//writeLogTxt("qkqd", "log.txt", "asddddddddddddddddddddddddddd");
		//删除文件夹下的文件
		//delAllFile("logtimetask");
		//delAllFile("qkqd");
		//writeLogTxtV2("log_jh.txt","在"+DateFormater.DateToString(new Date(), "yyyy-MM-dd")+"更新1");
		//writeLogTxtV2("log_jh.txt","在"+DateFormater.DateToString(new Date(), "yyyy-MM-dd")+"更新2");
		//writeLogTxtV2("log_jh.txt","在"+DateFormater.DateToString(new Date(), "yyyy-MM-dd")+"更新3");
		System.out.println(FILE_HISTORY);
	}
	/**
	 * @author hyl
	 * @throws IOException 
	 * @since
	 * 不断将内容写入一个文件
	 * foldersource webapp/resources/logtimetask
	 * */
	public static void writeLogTxt(String filename,String content) throws IOException{
		FileWriter fw = null;
		try {
            //System.out.println(FILE_HISTORY+"/webapp/resources/logtimetask/"+filename);
            //String path=request0.getServletContext().getRealPath("/resources/logtimetask/");
			File file = new File(FILE_HISTORY+"/resources/logtimetask/"+filename);
            if(!file.exists()){
            	file.createNewFile();
            }
            fw = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            try {
	            fw.flush();
	            pw.close();
	            fw.close();
            } catch (IOException e) {
            	e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	/**
	 * 写入文件方法第二种
	 * */
	public static void writeLogTxtV2(String filename,String content) throws IOException{
		//得到系统默认的encoding码  
        String fileEncode = System.getProperty("file.encoding");
		String filePath=FILE_HISTORY+"/resources/logtimetask/"+filename;
		try {  
			File file = new File(filePath);
            if(!file.exists()){
            	file.createNewFile();
            }   
			//构造函数中的第二个参数true表示以追加形式写文件  
	        FileOutputStream fos = new FileOutputStream(filePath, true);  
	        OutputStreamWriter osw = new OutputStreamWriter(fos, fileEncode);  
	        osw.write(content+"\r\n");  
	        osw.close();  
	        fos.close();  
	     } catch (IOException e) {  
	         System.out.println("文件写入失败！" + e);  
	     }  
	}
	/**
	 * 带子目录
	 * author  90  
	 * on 2015-11-2
	 * @param zhiMulu
	 * @param filename
	 * @param content
	 * @throws IOException
	 */
	public static void writeLogTxt(String zhiMulu,String filename,String content) throws IOException{
		FileWriter fw = null;
		try {
            //System.out.println(FILE_HISTORY+"/webapp/resources/logtimetask/"+filename);
            //String path=request0.getServletContext().getRealPath("/resources/logtimetask/");
			File file = new File(FILE_HISTORY+"/resources/"+zhiMulu+"/"+filename);
            if(!file.exists()){
            	file.createNewFile();
            }
            fw = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            try {
	            fw.flush();
	            pw.close();
	            fw.close();
            } catch (IOException e) {
            	e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	/**    
     *  删除文件夹里面的所有文件 <br/> 
     *  
     * filePath为resources下子目录文件夹名字，filePath两边不要带斜杠<br/> 
     */    
   public static void  delAllFile(String  filePath)  {    
	   filePath=FILE_HISTORY+"/resources/"+filePath+"/";
	   File  file  =  new  File(filePath);    
       if  (!file.exists())  {    
           return;    
       }    
       if  (!file.isDirectory())  {    
           return;    
       }    
       String[]  tempList  =  file.list();    
       File  temp  =  null;    
       for  (int  i  =  0;  i  <  tempList.length;  i++)  {    
           if  (filePath.endsWith(File.separator))  {    
               temp  =  new  File(filePath  +  tempList[i]);    
           }    
           else  {    
               temp  =  new  File(filePath  +  File.separator  +  tempList[i]);    
           }    
           if  (temp.isFile())  {    
               temp.delete();    
           }    
           if  (temp.isDirectory())  {    
               delAllFile(filePath+"/"+  tempList[i]);//先删除文件夹里面的文件    
               //delFolder(path+"/"+  tempList[i]);//再删除空文件夹    
           }    
       }    
   }  
   /**    
    *  删除文件夹里面的所有文件 <br/> 
    *  
    */    
  public static void  delAllFileV2(String  filePath)  {    
	  File  file  =  new  File(filePath);    
      if  (!file.exists())  {    
          return;    
      }    
      if  (!file.isDirectory())  {    
          return;    
      }    
      String[]  tempList  =  file.list();    
      File  temp  =  null;    
      for  (int  i  =  0;  i  <  tempList.length;  i++)  {    
          if  (filePath.endsWith(File.separator))  {    
              temp  =  new  File(filePath  +  tempList[i]);    
          }    
          else  {    
              temp  =  new  File(filePath  +  File.separator  +  tempList[i]);    
          }    
          if  (temp.isFile())  {    
              temp.delete();
          }    
          if  (temp.isDirectory())  {    
              delAllFile(filePath+"/"+  tempList[i]);//先删除文件夹里面的文件    
              //delFolder(path+"/"+  tempList[i]);//再删除空文件夹    
          }    
      }    
  }
   /**  
    *  删除文件夹   
    *  @param  filePathAndName  String  文件夹路径及名称  如c:/fqf   
    *  @param  fileContent  String   
    *  @return  boolean   
    */    
  public static void  delFolder(String  folderPath)  {    
      try  {    
          delAllFile(folderPath);  //删除完里面所有内容    
          String  filePath  =  folderPath;    
          filePath  =  filePath.toString();    
          java.io.File  myFilePath  =  new  java.io.File(filePath);    
          myFilePath.delete();  //删除空文件夹    
  
      }    
      catch  (Exception  e)  {    
          System.out.println("删除文件夹操作出错");    
          e.printStackTrace();    
  
      }    
  }    
  
}
