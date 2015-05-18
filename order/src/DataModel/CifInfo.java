package DataModel;

/***********************************************************************
 * Module:  CifInfo.java
 * Author:  HGM
 * Purpose: Defines the Class CifInfo
 ***********************************************************************/

import java.util.*;

/**
 * 用户信息表 包含客户端用户、后台管理人员。
 * 
 * @pdOid e6a409ed-fda8-48b1-ad99-a91cf01eb204
 */
public class CifInfo {
//	客户序列号
	/** @pdOid 71b8e229-2e63-476f-862c-4a73f3edd912 */
	private java.lang.Integer cifSeq;
//	客户名称
	/** @pdOid 0dd27bfc-92ce-4af8-a494-933370b7845f */
	private java.lang.String cifName;
	/** @pdOid 36f0065e-00ca-49a0-9019-0957358c7e4e */
//客户渠道：
	private java.lang.String channel;
	/** @pdOid 2d1fa035-9f99-4235-b188-16f6c37a3b3c */
//客户手机
	private java.lang.String phone;
	/** @pdOid 1f46054a-e77a-40f7-a635-0d5514ad213f */
//客户类型
	private CifType cifType;
	/** @pdOid 9d88e231-2f27-4088-a683-e5eee3476abd */
//客户级别
	private CifLevel cifLevel;
	/** @pdOid d078d389-220a-4338-a684-95d76de567fe */
//	客户状态
	private CifState cifState;
	/** @pdOid b1d35360-5daa-4d2b-9096-310d9e65b6e3 */
//	描述
	private java.lang.String describe;
	/** @pdOid 5c70887b-b38c-4a0e-84ce-8b3be4495c6d */
//	注册日期
	private java.sql.Date registerDate;
	/** @pdOid 647f0223-8099-4098-9bb5-fbd19b699f0e */
//地址
	private java.lang.String address;
	/** @pdOid d0dde658-e8bd-4355-98c8-c49bef1d5b10 */
//营业时间
	private java.lang.String opentime;
	/** @pdOid 349dab1d-3c99-4ac6-83d6-d64914fd81a0 */
//停业时间
	private java.lang.String closetime;

	public java.lang.Integer getCifSeq() {
		return cifSeq;
	}

	public void setCifSeq(java.lang.Integer cifSeq) {
		this.cifSeq = cifSeq;
	}

	public java.lang.String getCifName() {
		return cifName;
	}

	public void setCifName(java.lang.String cifName) {
		this.cifName = cifName;
	}

	public java.lang.String getChannel() {
		return channel;
	}

	public void setChannel(java.lang.String channel) {
		this.channel = channel;
	}

	public java.lang.String getPhone() {
		return phone;
	}

	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	public CifType getCifType() {
		return cifType;
	}

	public void setCifType(CifType cifType) {
		this.cifType = cifType;
	}

	public CifLevel getCifLevel() {
		return cifLevel;
	}

	public void setCifLevel(CifLevel cifLevel) {
		this.cifLevel = cifLevel;
	}

	public CifState getCifState() {
		return cifState;
	}

	public void setCifState(CifState cifState) {
		this.cifState = cifState;
	}

	public java.lang.String getDescribe() {
		return describe;
	}

	public void setDescribe(java.lang.String describe) {
		this.describe = describe;
	}

	public java.sql.Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(java.sql.Date registerDate) {
		this.registerDate = registerDate;
	}

	public java.lang.String getAddress() {
		return address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getOpentime() {
		return opentime;
	}

	public void setOpentime(java.lang.String opentime) {
		this.opentime = opentime;
	}

	public java.lang.String getClosetime() {
		return closetime;
	}

	public void setClosetime(java.lang.String closetime) {
		this.closetime = closetime;
	}
	
	@Override
	public String toString() {
		
		return super.toString();
	}
}