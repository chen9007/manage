package com.cy.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.thymeleaf.util.StringUtils;

import com.cy.pojo.JsonResult;

@Controller
public class Dowload {

	@RequestMapping(value = {"/download"},method = RequestMethod.GET)
	public void download(@RequestParam("filename") String filename, HttpServletRequest request,HttpServletResponse response) throws IOException {
		if(!StringUtils.isEmpty(filename)) {
			//模拟文件，myfile.txt为需要下载的文件  
	        String path = "D:\\file"+"\\"+filename; 
	        //创建添加定义获取文件
	        File file=new File(path);
	        //创建添加定义获取该文件的文件输入流
            FileInputStream fileInputStream=new FileInputStream(file);
		    //创建添加定义将文件输入流放在输入流里
            InputStream inputStream=new BufferedInputStream(fileInputStream);
            //创建添加定义转码防止文件名中文乱码
		    filename=URLEncoder.encode(filename,"UTF-8");
		    //创建添加定义设置文件下载头
		    response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		    //创建添加定义设置下载文件类型
		    response.setContentType("multipart/form-data");
		    //创建添加定义获取文件输出流
		    BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(response.getOutputStream());
		    int len=0;
		    while((len = inputStream.read()) != -1){  
		    	bufferedOutputStream.write(len);  
		    	bufferedOutputStream.flush();  
	        }  
		    bufferedOutputStream.close();
		}
		
	}
	@RequestMapping("/uploadfile1")
	@ResponseBody
	public JsonResult springUpload1(HttpServletRequest request) throws IllegalStateException, IOException {
	    JsonResult JsonResult=new JsonResult();
	    //获取从1970年到现在的毫秒数
	    Long startTime=System.currentTimeMillis();
	    //将当前上下文初始化给CommonsMultipartResolver(多部分解析器)
	    CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
	    //检查form表单中有没有enctype="multipart/form-data"这个属性
	    if(commonsMultipartResolver.isMultipart(request))
        {
	    //将request转换成多部分请求
		MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest) request;
		//获取multipartHttpServletRequest的所有的文件名
		Iterator irIterator=multipartHttpServletRequest.getFileNames();
		while(irIterator.hasNext()) {
		MultipartFile multipartFile=multipartHttpServletRequest.getFile(irIterator.next().toString());
		if(multipartFile!=null){
			String path="E:/springUpload/"+multipartFile.getOriginalFilename();
			// 检查该路径对应的目录是否存在，如果不存在则创建目录
						File dir = new File(path);
						if (!dir.exists()) {
							dir.mkdirs();
						}
			//上传
				
			 multipartFile.transferTo(new File(path));
		}
		}
        }
	    Long endTime=System.currentTimeMillis();
	    System.out.println("文件上传一共用时 "+String.valueOf(endTime-startTime)+" 毫秒");
	    return JsonResult;
	}


	//@RequestMapping("/download1")


	public String download1(String filePath, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			//获取文件名
			String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
			System.out.println(filePath);
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			//处理下载弹出框名字的编码问题
			response.setHeader("Content-Disposition", "attachment;fileName="
					+ new String( fileName.getBytes("gb2312"), "ISO8859-1" ));
			//获取文件的下载路径
			String path = request.getSession().getServletContext().getRealPath(filePath);
			System.out.println(path);
			//利用输入输出流对文件进行下载
			InputStream inputStream = new FileInputStream(new File(path));
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			// 这里主要关闭。
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//  返回值要注意，要不然就出现下面这句错误！
		//java+getOutputStream() has already been called for this response
		return null;
	}
}
