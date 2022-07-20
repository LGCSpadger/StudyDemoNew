//package com.test.pub.service;
//
//import com.linkage.module.liposs.system.util.Serial;
//import com.linkage.system.jdbc.jpa.JPABaseDAO;
//import com.linkage.system.jdbc.jpa.SqlSessionTemplate;
//import java.util.Map;
//import org.apache.log4j.Logger;
//
//public class LoginManageDAOImp extends JPABaseDAO
//  implements LoginManageDAO
//{
//  private static final Logger LOG = Logger.getLogger(LoginManageDAOImp.class);
//  private final String NAMESPACE = LoginManageDAOImp.class.getName();
//
//  public int insertMessage(Map m)
//  {
//    int maxSmsid = Serial.getTableMaxid("tab_sms_scheduler", "smsid", 1);
//    m.put("smsid", Integer.valueOf(maxSmsid));
//    LOG.info("==============" + m.toString());
//    return getSqlSessionTemplate().insert(this.NAMESPACE + ".insertMessage", m);
//  }
//
//  public String queryMobile(String loginName)
//  {
//    return (String)getSqlSessionTemplate().selectOne(this.NAMESPACE + ".queryMobile", loginName);
//  }
//
//  public int getPerson(String loginName)
//  {
//    return ((Integer)getSqlSessionTemplate().selectOne(this.NAMESPACE + ".queryPerson", loginName)).intValue();
//  }
//}