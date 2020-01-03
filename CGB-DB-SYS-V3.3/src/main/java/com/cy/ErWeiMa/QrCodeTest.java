package com.cy.ErWeiMa;

public class QrCodeTest {
	 /**添加依赖
	  * <dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>core</artifactId>
    <version>3.3.0</version>
</dependency>
	  	*/
	public static void main(String[] args) throws Exception {
		// 存放在二维码中的内容
		String text = "欢迎来到来吧读书!电话:188888888888";
				
		// 嵌入二维码的图片路径
		String imgPath = "C:/Users/Administrator/Desktop/a66.jpg";
		// 生成的二维码的路径及名称
		String destPath = "C:/Users/Administrator/Desktop/v1.jpg";
		//生成二维码
		QRCodeUtil.encode(text, imgPath, destPath, true);
		// 解析二维码
		String str = QRCodeUtil.decode(destPath);
		// 打印出解析出的内容
		System.out.println(str);
 
	}
 
}

