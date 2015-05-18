package DataModel;

/***********************************************************************
 * Module:  OderInfo.java
 * Author:  HGM
 * Purpose: Defines the Class OderInfo
 ***********************************************************************/

import java.math.BigDecimal;
import java.util.*;

/** 订单详情
 * 
 * @pdOid 92a209ad-4bf6-47d2-bab6-884eaae23616 */
public class OderInfo {
//	交易流水号
   /** @pdOid e1277110-6ffb-4eff-b621-a17426a4afcc */
   private Integer jnlno;
   /** @pdOid ca3a6501-43c6-4bd5-82a3-8a7780a6aaac */
//   交易用户序列号
   private Integer cifSeq;
   /** @pdOid 0493742c-1417-4e0e-bafd-efd9dd997770 */
//   用户类别
   private String cifType;
   /** @pdOid 9dc38723-e263-4635-b17b-74702b06ab4c */
// 交易日期
   private java.sql.Date trsdate;
   /** @pdOid 749064ff-5ba2-4d4d-8b87-e080ee011453 */
// 交易时间
   private String trstime;
   /** @pdOid 053b6d2f-3528-4ab5-9198-56da7d39a7ff */
//送达地址
   private String addres;
   /** @pdOid 9925a239-3981-4b83-bb5b-1eb365e803da */
//联系方式
   private String phone;
   /** @pdOid 7025c287-9fa6-47b3-bb34-52deccefcb43 */
// 交易状态
   private TrsState trsState;
   /** @pdOid b8533435-773f-4e6c-a312-6a07e20d66b4 */
// 产品类型
   private ProductType productType;
   /** @pdOid 899b1bd4-e529-4ee4-bbda-fbb20a1191bf */
//产品id
   private String productId;
   /** @pdOid 7540067e-d818-450e-b994-4a0aca008d81 */
//产品数量
   private Integer productCount;
   /** @pdOid a32d955f-713b-403c-8ab3-bc610a1adda7 */
// 交易 金额
   private BigDecimal amount;
//交易类型   
   private String trsId;
   /** @pdOid ad9d1f03-4f47-47bc-89b4-d7744231177f */
// 上级流水号
   private Integer parentJnlno;
   
   
}