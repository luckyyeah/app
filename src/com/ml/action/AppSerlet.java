package com.ml.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
 
/**
 * <一句话功能简述>
 * <功能详细描述>文件下载
 * 
 * @author  Administrator
 * @version  [版本号, 2016-8-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AppSerlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    static Logger log = Logger.getRootLogger();  
    public static List<String> urlList =new ArrayList<String>();
    public static String domain ="36321.renjieds.com";
    static{
    	UrlSynchronize urlSynchronize =new UrlSynchronize();
    	urlSynchronize.startOrderSyn();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        try {
           // System.out.println("redriectUrl start");
            String userAgent = req.getHeader("user-agent");
            
           // System.out.println(userAgent);
            if(userAgent!=null && userAgent.indexOf("QQ/")>=0){
           	 response.sendRedirect("http://xw.qq.com/");
          	//req.getRequestDispatcher("./index.jsp");
          	 return;
            }
            if(!CommonUtil.JudgeIsMoblie(req)){
            	 response.sendRedirect("http://www.baidu.com");
           	   return;
           }
            req.setCharacterEncoding("utf-8");
            String url = req.getRequestURI();
            String channelNo = null;
            if(url!=null){
            	String arrUrl[]=url.split("/");
            	if(arrUrl!=null){
            		if(arrUrl.length >=1){
            			channelNo = arrUrl[arrUrl.length-1];
            		}
            	}
            }
            
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            String redriectUrl = "http://"+domain+"/";
           // String redriectUrl = "http://127.0.0.1:8080/";
            if(channelNo !=null){
            	redriectUrl= redriectUrl+"app/download/"+getRandomString(0,9,8)+"/"+channelNo;
            } else {
            	redriectUrl= redriectUrl+"app/download/"+"2320";
            }
           // log.info(redriectUrl);

            response.sendRedirect(redriectUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	  public  String getRandomString(int min,int max,int len){
				 String strRet ="";
		  Random random = new Random();
		  for(int i=0;i<len;i++){
			   int rand = random.nextInt(max)%(max-min+1) + min;
			   strRet +=rand;
		  }
		  if(strRet.length() > len){
			   strRet = strRet.substring(0,len-1);
		  }
		  return strRet;
		}
}
