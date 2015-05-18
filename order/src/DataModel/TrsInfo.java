package DataModel;

/***********************************************************************
 * Module:  TrsInfo.java
 * Author:  HGM
 * Purpose: Defines the Class TrsInfo
 ***********************************************************************/

import java.util.*;

/** 交易信息表
 * 
 * @pdOid dddc98a5-ee4a-4549-90c9-579a349f224e */
public class TrsInfo {
//	交易流水号
   /** @pdOid ab696f01-6a86-4ef1-93f5-33b6f5618f19 */
   private long jnlno;
   /** @pdOid 88a61788-6b91-4789-aa7b-28e698ce6545 */
//交易客户序列号
   private int cifSeq;
   /** @pdOid f5096100-db24-4774-a9b2-c0716b0d2e2d */
//交易ID
   private java.lang.String trsId;
   /** @pdOid 53631a83-ee32-416e-b2bc-512d322c1d92 */
//交易日期
   private java.util.Date trsdate;
   /** @pdOid d4239b01-a195-4252-87f9-f63cff2fb557 */
// 交易时间
   private java.lang.String trstime;
   /** @pdOid a2d8c954-4cac-4a96-ba9f-ad1706f722d4 */
//交易状态
   private TrsState trsState;
   

}