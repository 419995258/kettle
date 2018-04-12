/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package org.my431.base.services;

import java.util.List;

import org.my431.base.model.BaseNoteAttachment;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.springframework.stereotype.Repository;

@Repository
public class BaseNoteAttachmentManager extends HibernateXsqlBuilderQueryDao<BaseNoteAttachment>{

	public Class getEntityClass() {
		return BaseNoteAttachment.class;
	}
	
	public Page findPage(BaseNoteAttachment query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseNoteAttachment t where 1=1 "
			  	+ "/~ and t.noteId = {noteId} ~/"
			  	+ "/~ and t.title = {title} ~/"
			  	+ "/~ and t.fileName = {fileName} ~/"
			  	+ "/~ and t.fileSize = {fileSize} ~/"
			  	+ "/~ and t.fileExt = {fileExt} ~/"
				+ "/~ and t.creTime >= {creTimeBegin} ~/"
				+ "/~ and t.creTime <= {creTimeEnd} ~/"
			  	+ "/~ and t.creUser = {creUser} ~/"
			  	+ "/~ and t.sourceUrl = {sourceUrl} ~/"
				+ "/~ order by [sortColumns] ~/";

        //生成sql2的原因是为了不喜欢使用xsqlbuilder的同学，请修改生成器模板，删除本段的生成
        StringBuilder sql2 = new StringBuilder("select t from BaseNoteAttachment t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.fileId = :fileId ");
        }
        if(isNotEmpty(query.getNoteId())) {
            sql2.append(" and  t.noteId = :noteId ");
        }
        if(isNotEmpty(query.getTitle())) {
            sql2.append(" and  t.title = :title ");
        }
        if(isNotEmpty(query.getFileName())) {
            sql2.append(" and  t.fileName = :fileName ");
        }
        if(isNotEmpty(query.getFileSize())) {
            sql2.append(" and  t.fileSize = :fileSize ");
        }
        if(isNotEmpty(query.getFileExt())) {
            sql2.append(" and  t.fileExt = :fileExt ");
        }
        if(isNotEmpty(query.getCreTime())) {
            sql2.append(" and  t.creTime = :creTime ");
        }
        if(isNotEmpty(query.getCreUser())) {
            sql2.append(" and  t.creUser = :creUser ");
        }
        if(isNotEmpty(query.getSourceUrl())) {
            sql2.append(" and  t.sourceUrl = :sourceUrl ");
        }
        
		return pageQuery(sql, pageNo, pageSize);
	}
	/**
	 * 
     *类的描述:getNoteAttachmentByNoteId
     *功能描述 ：通过通知ID得到附件
     *作者:hyl
     *创建日期:2015-07-07
     *修改人：
     *修改日期：
     *修改原因描述;
	 */
	public BaseNoteAttachment getNoteAttachmentByNoteId(String noteID) {
		List<BaseNoteAttachment> list=find("from BaseNoteAttachment t where 1=1 and t.noteId='"+noteID+"'");
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}
	//service
}
