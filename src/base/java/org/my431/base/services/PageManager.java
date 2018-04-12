package org.my431.base.services;

import java.text.ParseException;

public class PageManager {
	private int RecordCount = 0; // 记录总数

	private int CurrentPageNum = 1; // 当前页码

	private int PageCount = 0; // 总页数

	private int CountPerPage = 10; // 每页记录数

	private int FirstPageNum = 0; // 显示的第一个页码

	private int LastPageNum = 0; // 显示的最后一个页码

	private int PNCountPerPage = 10;// 每页显示的页码数

	private int FirstRecordNum = 0; // 当前页第一个记录

	private int LastRecordNum = 0; // 当前页最后一个记录
	
	
	public PageManager(int _RecordCount, int _CountPerPage, int _PNCountPerPage) {
		// 初始化总记录数
		RecordCount = _RecordCount;

		// 初始化每页记录数
		CountPerPage = _CountPerPage;

		// 初始化总页数
		if (_RecordCount % _CountPerPage != 0) {
			PageCount = _RecordCount / _CountPerPage + 1;
		} else {
			PageCount = _RecordCount / _CountPerPage;
		}

		// 初始化每页显示页码数
		PNCountPerPage = _PNCountPerPage;
	}

	public void goToPage(int _PageNum) {
		if (_PageNum <= PageCount) {

			if (PageCount <= PNCountPerPage) {// 总页数小于等于每页显示页码数
				// 修改显示的第一个页码
				this.FirstPageNum = 1;
				// 修改显示的最后一个页码
				this.LastPageNum = PageCount;
				// 修改当前页第一个记录
				this.FirstRecordNum = (_PageNum - 1) * this.CountPerPage;
				// 修改当前页最后一个记录
				if (_PageNum == this.PageCount) {// 最后一页
					this.LastRecordNum = this.RecordCount - 1;
				} else {// 不是最后一页
					this.LastRecordNum = _PageNum * this.CountPerPage - 1;
				}
				// 修改当前页码
				this.CurrentPageNum = _PageNum;

			} else {// 总页数大于每页显示页码数
				// 修改显示的第一个页码

				int fc = _PageNum - (this.PNCountPerPage / 2);

				if (fc < 1)
					fc = 1;
				this.setFirstPageNum(fc);

				// 修改显示的最后一个页码
				int lc = _PageNum + (this.PNCountPerPage / 2);
				if (lc > this.PageCount)
					lc = this.PageCount;
				this.setLastPageNum(lc);

				// 修改当前页第一个记录
				this.FirstRecordNum = (_PageNum - 1) * this.CountPerPage;
				// 修改当前页最后一个记录
				if (_PageNum == this.PageCount) {// 最后一页
					this.LastRecordNum = this.RecordCount - 1;
				} else {// 不是最后一页
					this.LastRecordNum = _PageNum * this.CountPerPage - 1;
				}
				// 修改当前页码
				this.CurrentPageNum = _PageNum;
			}

		} else {
		}
	}

	public int getCountPerPage() {
		return CountPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		CountPerPage = countPerPage;
	}

	public int getCurrentPageNum() {
		return CurrentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		CurrentPageNum = currentPageNum;
	}

	public int getFirstPageNum() {
		return FirstPageNum;
	}

	public void setFirstPageNum(int firstPageNum) {
		FirstPageNum = firstPageNum;
	}

	public int getFirstRecordNum() {
		return FirstRecordNum;
	}

	public void setFirstRecordNum(int firstRecordNum) {
		FirstRecordNum = firstRecordNum;
	}

	public int getLastPageNum() {
		return LastPageNum;
	}

	public void setLastPageNum(int lastPageNum) {
		LastPageNum = lastPageNum;
	}

	public int getLastRecordNum() {
		return LastRecordNum;
	}

	public void setLastRecordNum(int lastRecordNum) {
		LastRecordNum = lastRecordNum;
	}

	public int getPageCount() {
		return PageCount;
	}

	public void setPageCount(int pageCount) {
		PageCount = pageCount;
	}

	public int getPNCountPerPage() {
		return PNCountPerPage;
	}

	public void setPNCountPerPage(int countPerPage) {
		PNCountPerPage = countPerPage;
	}

	public int getRecordCount() {
		return RecordCount;
	}

	public void setRecordCount(int recordCount) {
		RecordCount = recordCount;
	}

	public String getPageCode1() {
		if(this.RecordCount==0) return "";
		String pagecode="";
		pagecode=pagecode+"<div class=\"page2\">";
		pagecode=pagecode+"<p>共"+this.getRecordCount()+"条</p>";
		pagecode=pagecode+"<ul>";
		if(this.getCurrentPageNum()!=1)
		{
			pagecode=pagecode+"<li><a  class=\"pre\" href=\"javascript:goToPage("+(this.CurrentPageNum-1)+")\">&lt;上一页</a>";
		}else{
			pagecode=pagecode+"<li><a  class=\"pre\" href='###' >&lt;上一页</a></li>";
		}
    	if(this.getFirstPageNum()>1){
			pagecode=pagecode+"<li><a href=\"javascript:goToPage(1)\">1</a>...</li>";
    	}
	    for(int i=this.getFirstPageNum();i<=this.getLastPageNum();i++)
	    {
	    	if(this.getCurrentPageNum()==i)
	    	{
	    	pagecode=pagecode+"<li><span class=\"cur\">"+i+"</span></li> ";	
	    	}
	    	else
	    	{
	    	pagecode=pagecode+"<li><a class=\"num\" href=\"javascript:goToPage("+i+")\">"+i+"</a></li>";	
	    	}
	    }
	    
	    if(this.getLastPageNum()<this.PageCount){
			pagecode=pagecode+"<li>...<a class=\"num\" href=\"javascript:goToPage("+this.PageCount+")\">"+this.PageCount+"</a></li>";
    	}
	    
	    if(this.getCurrentPageNum()!=this.getLastPageNum())
		{
			pagecode=pagecode+"<li><a class=\"next\" href=\"javascript:goToPage("+(this.CurrentPageNum+1)+")\">下一页 &gt;</a></li>";
		}else{
			pagecode=pagecode+"<li><a href=\"###\" class=\"next\" >下一页 &gt;</a></li>";
		}
	    pagecode=pagecode+"</ul></div>";
		return pagecode;
	}
	public String getPageCode() {
		if(this.RecordCount==0) return "";
		String pagecode="";
		pagecode=pagecode+"<div class=\"pager\">";
		//pagecode=pagecode+"<span style=\"margin-top:10px;font-size:12px;margin-right:5px;\">共 "+this.getRecordCount()+" 条</span>";
		//System.out.println(getCurrentPageNum()+"=====" + getFirstPageNum());
		if(this.getCurrentPageNum()!=1)
		{
			pagecode=pagecode+"<a class=\"pre\" href=\"javascript:goToPage("+(this.CurrentPageNum-1)+")\">上一页</a> ";
		}else{
			pagecode=pagecode+"<a href=\"###\" class=\"pre\">上一页</a> ";
		}
    	if(this.getFirstPageNum()>1){
			pagecode=pagecode+"<a class=\"num\" href=\"javascript:goToPage(1)\">1</a> ... ";
    	}
	    for(int i=this.getFirstPageNum();i<=this.getLastPageNum();i++)
	    {
	    	if(this.getCurrentPageNum()==i)
	    	{
	    	pagecode=pagecode+"<a href=\"###\" class=\"on\">"+i+"</a> ";	
	    	}
	    	else
	    	{
	    	pagecode=pagecode+"<a  href=\"javascript:goToPage("+i+")\">"+i+"</a> ";	
	    	}
	    }
	    
	    if(this.getLastPageNum()<this.PageCount){
			pagecode=pagecode+"...<a  class=\"num\" href=\"javascript:goToPage("+this.PageCount+")\">"+this.PageCount+"</a> ";
    	}
	    
	    if(this.getCurrentPageNum()!=this.getLastPageNum())
		{
			pagecode=pagecode+"<a href=\"javascript:goToPage("+(this.CurrentPageNum+1)+")\" class=\"next\">下一页</a> ";
		}else{
			pagecode=pagecode+"<a  href=\"###\" class=\"next\">下一页</a> ";
		}
	    pagecode=pagecode+"<span class=\"all\">共<b>"+PageCount+"</b>页<b>"+RecordCount+"</b>条</span> ";
	    pagecode=pagecode+"<input type=\"text\" class=\"input\" name=\"queryPageNo\" id=\"queryPageNo\" placeholder=\"输入页数\" /> <a href=\"javascript:goToPage($('#queryPageNo').val())\" class=\"trun\">跳转</a>";
	    pagecode=pagecode+"</div>";
		return pagecode;
	}
	/**
	 * 
     *类的描述:<br>
     *功能描述 ：页码显示<br>
     *作者:hyl<br>
     *创建日期:2015-08-07<br>
     *修改人：<br>
     *修改日期：<br>
     *修改原因描述：增加记录的总条数<br>
	 */
	public String getPageCode2() {
		if(this.RecordCount==0) return "";
		String pagecode="";
		pagecode=pagecode+"<div class=\"page\">";
		pagecode=pagecode+"<span style=\"margin-top:10px;font-size:12px;margin-right:5px;color:#06a284;\">共 "+this.getRecordCount()+" 条</span>";
		if(this.getCurrentPageNum()!=1)
		{
			//pagecode=pagecode+"<a class=\"pre\" href=\"javascript:goToPage("+(this.CurrentPageNum-1)+")\">pre</a> ";
		}else{
			//pagecode=pagecode+"<a href=\"###\" class=\"pre\">pre</a> ";
		}
    	if(this.getFirstPageNum()>1){
			pagecode=pagecode+"<a class=\"num\" href=\"javascript:goToPage(1)\">1</a> ... ";
    	}
	    for(int i=this.getFirstPageNum();i<=this.getLastPageNum();i++)
	    {
	    	if(this.getCurrentPageNum()==i)
	    	{
	    	pagecode=pagecode+"<span class=\"num\">"+i+"</span> ";	
	    	}
	    	else
	    	{
	    	pagecode=pagecode+"<a class=\"num\" href=\"javascript:goToPage("+i+")\">"+i+"</a> ";	
	    	}
	    }
	    
	    if(this.getLastPageNum()<this.PageCount){
			pagecode=pagecode+"...<a  class=\"num\" href=\"javascript:goToPage("+this.PageCount+")\">"+this.PageCount+"</a> ";
    	}
	    
	    if(this.getCurrentPageNum()!=this.getLastPageNum())
		{
			//pagecode=pagecode+"<a href=\"javascript:goToPage("+(this.CurrentPageNum+1)+")\" class=\"next\">next</a> ";
		}else{
			//pagecode=pagecode+"<a  href=\"###\" class=\"next\">next</a> ";
		}
	    pagecode=pagecode+"<input type=\"text\" class=\"input\" name=\"queryPageNo\" id=\"queryPageNo\" placeholder=\"输入页数\" /> <a href=\"javascript:goToPage($('#queryPageNo').val())\" class=\"to\">跳转</a>";
	    pagecode=pagecode+"</div>";
		return pagecode;
	}
}

