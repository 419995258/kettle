package org.my431.util.excel;


/**
 * 
 * @author wangzhen
 *
 */
public class ExcelTestPo {
	@ExcelAnnotation(exportName = "第1标题")
	String  title1;
	@ExcelAnnotation(exportName = "第2标题")
	String title2;
	@ExcelAnnotation(exportName = "第3标题")
	String title3;
	@ExcelAnnotation(exportName = "第4标题")
	String title4;
	@ExcelAnnotation(exportName = "第5标题")
	String title5;
	@ExcelAnnotation(exportName = "第6标题")
	String title6;	
//	@ExcelAnnotation(exportName = "第7标题")
	String title7;
	public String getTitle1() {
		return title1;
	}
	public String getTitle2() {
		return title2;
	}
	public String getTitle3() {
		return title3;
	}
	public String getTitle4() {
		return title4;
	}
	public String getTitle5() {
		return title5;
	}
	public String getTitle6() {
		return title6;
	}
	public String getTitle7() {
		return title7;
	}
	public void setTitle1(String title1) {
		this.title1 = title1;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public void setTitle3(String title3) {
		this.title3 = title3;
	}
	public void setTitle4(String title4) {
		this.title4 = title4;
	}
	public void setTitle5(String title5) {
		this.title5 = title5;
	}
	public void setTitle6(String title6) {
		this.title6 = title6;
	}
	public void setTitle7(String title7) {
		this.title7 = title7;
	}
	
}