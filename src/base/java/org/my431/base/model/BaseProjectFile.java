/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2015
 */

package org.my431.base.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.*;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */

//BaseProjectFile
@Entity
@Table(name = "BASE_PROJECT_FILE")
public class BaseProjectFile implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "FILE_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//fileId
	private java.lang.String id;
		
	@Column(name = "TITLE", unique = false, nullable = true, insertable = true, updatable = true, length = 400)
	//标题，暂时不用
	private java.lang.String title;
		
	@Column(name = "FILE_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//文件名，原始名称
	private java.lang.String fileName;
		
	@Column(name = "FILE_SIZE", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//文件大小，单位byte
	private Integer fileSize;
		
	@Column(name = "FILE_EXT", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	//扩展名
	private java.lang.String fileExt;
		
	@Column(name = "CRE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//上传时间
	private java.util.Date creTime;
		
	@Column(name = "CRE_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//上传用户登录名，一般是帖子发帖人
	private java.lang.String creUser;
		
	@Column(name = "SOURCE_URL", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	//sourceUrl
	private java.lang.String sourceUrl;
		
	@Column(name = "FILE_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//project.file.type.zhaobiao 招标文件 project.file.type.hetong  合同文件 project.file.type.yanshou  验收文件 project.file.type.qita  其他文件
	private java.lang.String fileType;
		
	@Column(name = "PRO_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	//proId
	private java.lang.String proId;


	public BaseProjectFile(){
	}

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getFileName() {
		return this.fileName;
	}
	
	public void setFileName(java.lang.String value) {
		this.fileName = value;
	}
	
	public Integer getFileSize() {
		return this.fileSize;
	}
	
	public void setFileSize(Integer value) {
		this.fileSize = value;
	}
	
	public java.lang.String getFileExt() {
		return this.fileExt;
	}
	
	public void setFileExt(java.lang.String value) {
		this.fileExt = value;
	}
	
	public java.util.Date getCreTime() {
		return this.creTime;
	}
	
	public void setCreTime(java.util.Date value) {
		this.creTime = value;
	}
	
	public java.lang.String getCreUser() {
		return this.creUser;
	}
	
	public void setCreUser(java.lang.String value) {
		this.creUser = value;
	}
	
	public java.lang.String getSourceUrl() {
		return this.sourceUrl;
	}
	
	public void setSourceUrl(java.lang.String value) {
		this.sourceUrl = value;
	}
	
	public java.lang.String getFileType() {
		return this.fileType;
	}
	
	public void setFileType(java.lang.String value) {
		this.fileType = value;
	}
	
	public java.lang.String getProId() {
		return this.proId;
	}
	
	public void setProId(java.lang.String value) {
		this.proId = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("FileId",getId())
			.append("Title",getTitle())
			.append("FileName",getFileName())
			.append("FileSize",getFileSize())
			.append("FileExt",getFileExt())
			.append("CreTime",getCreTime())
			.append("CreUser",getCreUser())
			.append("SourceUrl",getSourceUrl())
			.append("FileType",getFileType())
			.append("ProId",getProId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseProjectFile == false) return false;
		if(this == obj) return true;
		BaseProjectFile other = (BaseProjectFile)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

