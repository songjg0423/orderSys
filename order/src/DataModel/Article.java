package DataModel;

/***********************************************************************
 * Module:  Article.java
 * Author:  HGM
 * Purpose: Defines the Class Article
 ***********************************************************************/

import java.util.*;

/** 温馨提示，用来存放每天向用户发送的推荐消息、笑话、菜品、心灵鸡汤等
 * 
 * @pdOid cbbedc63-1a53-4d59-8854-ef9d1e9cad80 */
public class Article {
   /** @pdOid ed7c744f-261d-4ebf-9dc5-b723e6ff1069 */
//文章序列号，自动生成	
   private long articleSeq;
   /** @pdOid 23d24faf-2b95-4576-a2fc-8a29d0ded45d */
//文章简介
   private java.lang.String articletitle;
   /** @pdOid 4f78b873-5ccd-44fa-9551-3af9fb1a45da */
//文章标题
   private java.lang.String articleName;
   /** @pdOid 63cca0f4-7870-472d-aeff-8833e3a045e9 */
//文章具体内容
   private java.lang.String articleInfo;
   /** @pdOid 55be010e-be6c-443b-9b30-67432a05faca */
 //创建日期
   private java.util.Date artDate;
   /** @pdOid 476e2ca2-fec6-4be8-902c-364a75cbbf78 */
   //创建时间
   private java.util.Date arttime;
   /** @pdOid c5a8ef5b-962a-4ea3-a6f8-cd7551ba61d3 */
//  生效日期
   private java.util.Date pushDate;
   /** @pdOid 4694415e-5821-42f3-8f3c-8e3bb8c98ea8 */
  //生效时间
   private java.util.Date pushTime;
   /** @pdOid 9dd3b6fe-315d-43f6-bc34-7cf82c8f642b */
   //文章状态
   private java.lang.String articleState;
   /** @pdOid e4217838-26e4-4be2-8af1-f4ba06c7dc15 */
   //关联产品
   private Integer productSeq;
public long getArticleSeq() {
	return articleSeq;
}
public void setArticleSeq(long articleSeq) {
	this.articleSeq = articleSeq;
}
public java.lang.String getArticletitle() {
	return articletitle;
}
public void setArticletitle(java.lang.String articletitle) {
	this.articletitle = articletitle;
}
public java.lang.String getArticleName() {
	return articleName;
}
public void setArticleName(java.lang.String articleName) {
	this.articleName = articleName;
}
public java.lang.String getArticleInfo() {
	return articleInfo;
}
public void setArticleInfo(java.lang.String articleInfo) {
	this.articleInfo = articleInfo;
}
public java.util.Date getArtDate() {
	return artDate;
}
public void setArtDate(java.util.Date artDate) {
	this.artDate = artDate;
}
public java.util.Date getArttime() {
	return arttime;
}
public void setArttime(java.util.Date arttime) {
	this.arttime = arttime;
}
public java.util.Date getPushDate() {
	return pushDate;
}
public void setPushDate(java.util.Date pushDate) {
	this.pushDate = pushDate;
}
public java.util.Date getPushTime() {
	return pushTime;
}
public void setPushTime(java.util.Date pushTime) {
	this.pushTime = pushTime;
}
public java.lang.String getArticleState() {
	return articleState;
}
public void setArticleState(java.lang.String articleState) {
	this.articleState = articleState;
}
public Integer getProductSeq() {
	return productSeq;
}
public void setProductSeq(Integer productSeq) {
	this.productSeq = productSeq;
}
   
}