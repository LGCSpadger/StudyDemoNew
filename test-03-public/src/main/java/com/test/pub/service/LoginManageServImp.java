//package com.test.pub.service;
//
//import com.linkage.liposs.system.dbimpl.DbAuthorization;
//import com.linkage.module.liposs.system.loginmanage.dao.LoginManageDAO;
//import com.linkage.module.liposs.system.util.PropertiesUtil;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import org.apache.log4j.Logger;
//
//public class LoginManageServImp
//  implements LoginManageServ
//{
//  public static Logger LOG = Logger.getLogger(LoginManageServImp.class);
//
//  private static Map<String, Object> map = new HashMap();
//
//  private long between_time = Long.parseLong(PropertiesUtil.getValue("com/linkage/module/liposs/system/loginmanage/login_manage.properties", "betWeenTime"));
//  private LoginManageDAO dao;
//
//  public static void main(String[] args)
//  {
//    LoginManageServImp ss = new LoginManageServImp();
//  }
//
//  public String commonCreatMessage(String userName, Long sendTime, String mobile)
//  {
//    String result = "";
//
//    Map messageMap = new HashMap();
//
//    String message = creatPassword();
//    messageMap.put("msisdn", mobile);
//    messageMap.put("content", "您本次的动态密码为：" + message + "，请勿告知他人，并请在10分钟内登陆系统。");
//    messageMap.put("module", "iposs");
//    messageMap.put("createdTime", sendTime);
//    messageMap.put("status", "0");
//    LOG.info("3333333" + messageMap);
//    if (this.dao.insertMessage(messageMap) > 0)
//    {
//      DbAuthorization.timeMap.put(userName, userName);
//      DbAuthorization.timeMap.put(userName + "pword", message);
//      DbAuthorization.timeMap.put(userName + "time", sendTime);
//      DbAuthorization.timeMap.put(userName + "mobile", mobile);
//      result = "动态密码发送成功!";
//    }
//    else
//    {
//      result = "动态密码生成失败!";
//    }
//    return result;
//  }
//
//  public String creatMessage(String userName)
//  {
//    String result = "";
//    if (this.dao.getPerson(userName) > 0)
//    {
//      String mobile = this.dao.queryMobile(userName);
//
//      if ((mobile == null) || (mobile.equals("")))
//      {
//        result = "此账户没有存储手机号信息";
//      }
//      else
//      {
//        Long sendTime = Long.valueOf(new Date().getTime() / 1000L);
//        LOG.info("this loginning user is==>" +
//          DbAuthorization.timeMap.get(userName));
//        if (DbAuthorization.timeMap.get(userName) != null)
//        {
//          if (DbAuthorization.timeMap.get(userName).equals(userName))
//          {
//            Long oldTime = Long.valueOf(Long.parseLong(DbAuthorization.timeMap.get(
//              userName + "time").toString()));
//
//            long betweenTime = sendTime.longValue() - oldTime.longValue();
//            if (betweenTime < this.between_time)
//            {
//              long time = 120L - betweenTime;
//              long secd = 0L;
//              long min;
//              if (time > 60L)
//              {
//                long min = 1L;
//                secd = time - 60L;
//              }
//              else
//              {
//                min = 0L;
//                secd = time;
//              }
//              result = "距离下一次动态密码获取的时间为: " + min + " 分 " + secd +
//                " 秒，请耐心等待";
//            }
//            else if (betweenTime > this.between_time)
//            {
//              result = commonCreatMessage(userName, sendTime, mobile);
//            }
//          }
//          else
//          {
//            result = commonCreatMessage(userName, sendTime, mobile);
//          }
//        }
//        else
//        {
//          result = commonCreatMessage(userName, sendTime, mobile);
//        }
//      }
//    }
//    else
//    {
//      result = "系统中没有" + userName + "用户";
//    }
//    return result;
//  }
//
//  private String creatPassword()
//  {
//    String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
//    String password = "";
//    for (int i = 0; i < 8; i++)
//    {
//      password = password + chars.charAt((int)(Math.random() * 36.0D));
//    }
//    return password;
//  }
//
//  public LoginManageDAO getDao()
//  {
//    return this.dao;
//  }
//
//  public void setDao(LoginManageDAO dao)
//  {
//    this.dao = dao;
//  }
//}