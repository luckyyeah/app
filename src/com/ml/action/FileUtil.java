package com.ml.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	public static List<String>  readFileToStringList(String filePath){
					List<String> dataList = new ArrayList<String>();
	      
	        BufferedReader reader = null;
	        try {
	        	  File file = new File(filePath);
	            reader = new BufferedReader(new FileReader(file));
	            String recordLine = null;
	            int line = 1;
	            // 一次读入一行，直到读入null为文件结束
	            while ((recordLine = reader.readLine()) != null) {
	            	dataList.add(recordLine);
	            }
	            reader.close();
	        } catch (IOException ex) {
	        	 System.out.println("文件读取处理失败："+ex.getMessage());
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
		
	        return dataList ;
	}
	public static 	void WriteToFile(List<String> listContent,String filePath){
		
        try {

						StringBuffer   sb=new   StringBuffer(4096);
						for(String lineContent:listContent){
									if(lineContent!=null && "".equals(lineContent.trim())){
										continue;
									}
				        sb.append(lineContent).append( "\r ");
						}
						BufferedWriter   bw=new   BufferedWriter(new FileWriter(filePath));
						bw.write(sb.toString());
						bw.close();
        } catch (IOException ex) {
       	 System.out.println("文件读取处理失败："+ex.getMessage());
       } finally {

       }
	
	}
}
