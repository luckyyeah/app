package com.ml.action;

import org.apache.log4j.Logger;

public class ApkUrlSynchronize  implements Runnable{  
	static Logger log = Logger.getRootLogger();  
	public  static boolean isRun =false;
	public  static String  basePath="";
	public  void run()  
	{  
		try {
			while(true){
				try{
					
					if(!CommonUtil.isVidateUrl(AppDownloadSerlet.domain)){
						AppDownloadSerlet.urlList = FileUtil.readFileToStringList("/data/urllist/urlapk");
						int index=0;
		        	//休眠1分钟再请求
		        	Thread.sleep(1000*60*2);
						for(String srcUrl:AppDownloadSerlet.urlList ){
							
								String url=srcUrl;
								if(url!=null && "".equals(url.trim())){
									continue;
								}
			        	//去掉www.或者http://开头的字符串
			        	url = url.replace("wwww.", "").replace("http://", "");
			        	url="http://"+ DateUtil.getDayTime()+"."+url;
			        	if(CommonUtil.isVidateUrl(url)){
			        		AppDownloadSerlet.domain=url;
			        		log.info("domain="+AppDownloadSerlet.domain);
			        		break;
			        	} else {
				        	log.info("error url=" + url);
				        	//AppDownloadSerlet.urlList.remove(srcUrl);
				        }
			        	//休眠1分钟再请求
			        	Thread.sleep(1000*60*2);
			        	index++;
			        }
						//检查主域名
						checkMainDomain();
					}
	        //FileUtil.WriteToFile(AppDownloadSerlet.urlList,"/data/urllist/url");
					//休眠6分钟检查一次
					Thread.sleep(1000*60*10);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}  
  public void checkMainDomain() throws InterruptedException{
	  
			for(int i=AppDownloadSerlet.urlList.size()-1 ;i>=0;i-- ){
				String url=AppDownloadSerlet.urlList.get(i);
				if(url!=null && "".equals(url.trim())){
					continue;
				}
				//去掉www.或者http://开头的字符串
				url = url.replace("wwww.", "").replace("http://", "").trim();
				url="http://"+ url;
				if(!CommonUtil.isVidateUrl(url)){
			    	log.info("error url=" + url);
			    	AppDownloadSerlet.urlList.remove(i);
			    }
				//休眠1分钟再请求
				Thread.sleep(1000*60*2);
			}
			FileUtil.WriteToFile(AppDownloadSerlet.urlList,"/data/urllist/urlapk");
  }
	public String startOrderSyn(){
		if(!isRun){
			try{
				(new Thread(new ApkUrlSynchronize())).start();  
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
    this.isRun=true;
		return "1";

	}
}
