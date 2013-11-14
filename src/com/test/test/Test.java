//package com.test.test;
//
//import java.io.IOException;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//
//import com.test.mapper.UserMapper;
//import com.test.model.UserInfo;
//
//public class Test {
//
//	/**
//	 * @param args
//	 * @throws IOException 
//	 */
//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//		ApplicationContext aContext = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
////		ClassPathResource res= new ClassPathResource("config.properties");
////		System.out.println(res.getFile());
//		UserMapper userMapper = aContext.getBean(UserMapper.class);
//		UserInfo user = userMapper.getUser("111");
//		System.out.println(user.toString());
//	}
//
//}