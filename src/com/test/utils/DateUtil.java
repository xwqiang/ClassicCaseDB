package com.test.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 *����˵��:
 *@author:lishun
 *@date:2012-8-4
 */
/**
 * @author 1202212
 *
 */
public class DateUtil {
	

	/**
	 * ��ȡ12Сʱ�Ƶ�ǰ�����ַ���
	 * @return
	 */
	public static String getStrDate_12(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date  currentTime = new Date();
		String strDate = formatter.format(currentTime);
		return strDate;
	}
	
	/**
	 * ��ȡ24Сʱ�Ƶ�ǰ�����ַ���
	 * @return
	 */
	public static String getStrDate_24(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date  currentTime = new Date();
		String strDate = formatter.format(currentTime);
		return strDate;
	}
	
	/**
	 * ��ȡ24Сʱ�Ƶ�ǰ�����ַ���
	 * @return
	 */
	public static String getMonth(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		Date  currentTime = new Date();
		String strDate = formatter.format(currentTime);
		return strDate;
	}
	
	/**
	 * ��ȡ��ʽ����ǰʱ�䡢�����ַ���
	 * @return
	 */
	public static String getStrDateS(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
		Date currentTime = new Date();
		String strDate = formatter.format(currentTime);
		return strDate;
	}
	
	/**
	 * ת������Ϊ�ַ�����ʽ<p>
	 * @param Date
	 * @return
	 */
	public static String DateToStr(java.util.Date Date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = formatter.format(Date);
		return strDate;
	}
	
	/**
	 * ת������Ϊ��ʽ���ַ���
	 * @param Date
	 * @param format
	 * @return
	 */
	public static String DateToFormatStr(java.util.Date Date,String format){
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String strDate = formatter.format(Date);
		return strDate;
	}
	
	/**
	 * ��ȡ��ǰ���� ��ʽΪ yyyy-MM-dd
	 * @return strDate
	 */
	public static String getNowStrDate(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String strDate = formatter.format(date);
		return strDate;
	}
	
	/**
	 * ��ȡ����ʱ���ַ���   yyyyMMddHHmmss
	 * @return
	 */
	public static String getLongDate(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String strDate = formatter.format(date);
		return strDate;
	}
	
	/**
	 * ��ȡ����ʱ���ַ���   yyyyMMddHHmmssSS
	 * @return
	 */
	public static String getLongDateS(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSS");
		Date date = new Date();
		String strDate = formatter.format(date);
		return strDate;
	}
	
	/**
	 * �Ƚ϶�������
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean Compare_Date(java.util.Date date1,java.util.Date date2){
		return date1.equals(date2);
	} 
	
	/**
	 * ���ַ������͵�ʱ��ת��ΪDate��
	 * @param strDate
	 * @param formatDate
	 * @return Date
	 * @throws ParseException 
	 */
	public static Date str2Date(String strDate,String formatDate) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(formatDate); 
		return sdf.parse(strDate);
	}
	
	/**
	 * ���ַ������͵�ʱ��ת��ΪDate�ͣ������ڴ�ʱ���Ͻ������ӻ������Ӧ��
	 * @param strDate
	 * @param formatDate
	 * @return Date
	 * @throws ParseException 
	 */
	public static Date otherDate(String strDate,String formatDate,int num) throws ParseException{
		Calendar c = new GregorianCalendar();
		
		Date date = str2Date(strDate,formatDate);
		
		c.setTime(date);
		
		c.add(Calendar.DATE,num);
		
		SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
		
		return str2Date(sdf.format(c.getTime()),formatDate);
	}
	
	 /**
	  * ��ȡ����ʱ��
	  * 
	  * @return ����ʱ������ yyyy-MM-dd HH:mm:ss
	  */
	 public static Date getNowDate() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String dateString = formatter.format(currentTime);
	  ParsePosition pos = new ParsePosition(8);
	  Date currentTime_2 = formatter.parse(dateString, pos);
	  return currentTime_2;
	 }
	 


	 /**
	  * ��ȡ����ʱ��
	  * 
	  * @return�����ַ�����ʽ yyyy-MM-dd HH:mm:ss
	  */
	 public static String getStringDate() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String dateString = formatter.format(currentTime);
	  return dateString;
	 }

	 /**
	  * ��ȡ����ʱ��
	  * 
	  * @return ���ض�ʱ���ַ�����ʽyyyy-MM-dd
	  */
	 public static String getStringDateShort() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String dateString = formatter.format(currentTime);
	  return dateString;
	 }

	 /**
	  * ��ȡʱ�� Сʱ:��;�� HH:mm:ss
	  * 
	  * @return
	  */
	 public static String getTimeShort() {
	  SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	  Date currentTime = new Date();
	  String dateString = formatter.format(currentTime);
	  return dateString;
	 }

	 /**
	  * ����ʱ���ʽ�ַ���ת��Ϊʱ�� yyyy-MM-dd HH:mm:ss
	  * 
	  * @param strDate
	  * @return
	  */
	 public static Date strToDateLong(String strDate) {
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  ParsePosition pos = new ParsePosition(0);
	  Date strtodate = formatter.parse(strDate, pos);
	  return strtodate;
	 }

	 /**
	  * ����ʱ���ʽʱ��ת��Ϊ�ַ��� yyyy-MM-dd HH:mm:ss
	  * 
	  * @param dateDate
	  * @return
	  */
	 public static String dateToStrLong(java.util.Date dateDate) {
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String dateString = formatter.format(dateDate);
	  return dateString;
	 }

	 /**
	  * ����ʱ���ʽʱ��ת��Ϊ�ַ��� yyyy-MM-dd
	  * 
	  * @param dateDate
	  * @param k
	  * @return
	  */
	 public static String dateToStr(java.util.Date dateDate) {
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String dateString = formatter.format(dateDate);
	  return dateString;
	 }

	 /**
	  * ����ʱ���ʽ�ַ���ת��Ϊʱ�� yyyy-MM-dd 
	  * 
	  * @param strDate
	  * @return
	  */
	 public static Date strToDate(String strDate) {
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  ParsePosition pos = new ParsePosition(0);
	  Date strtodate = formatter.parse(strDate, pos);
	  return strtodate;
	 }

	 /**
	  * �õ�����ʱ��
	  * 
	  * @return
	  */
	 public static Date getNow() {
	  Date currentTime = new Date();
	  return currentTime;
	 }

	 /**
	  * ��ȡһ�����е����һ��
	  * 
	  * @param day
	  * @return
	  */
	 public static Date getLastDate(long day) {
	  Date date = new Date();
	  long date_3_hm = date.getTime() - 3600000 * 34 * day;
	  Date date_3_hm_date = new Date(date_3_hm);
	  return date_3_hm_date;
	 }

	 /**
	  * �õ�����ʱ��
	  * 
	  * @return �ַ��� yyyyMMdd HHmmss
	  */
	 public static String getStringToday() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
	  String dateString = formatter.format(currentTime);
	  return dateString;
	 }

	 /**
	  * �õ�����Сʱ
	  */
	 public static String getHour() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String dateString = formatter.format(currentTime);
	  String hour;
	  hour = dateString.substring(11, 13);
	  return hour;
	 }

	 /**
	  * �õ����ڷ���
	  * 
	  * @return
	  */
	 public static String getTime() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String dateString = formatter.format(currentTime);
	  String min;
	  min = dateString.substring(14, 16);
	  return min;
	 }

	 /**
	  * �����û������ʱ���ʾ��ʽ�����ص�ǰʱ��ĸ�ʽ �����yyyyMMdd��ע����ĸy���ܴ�д��
	  * 
	  * @param sformat
	  *            yyyyMMddhhmmss
	  * @return
	  */
	 public static String getUserDate(String sformat) {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat(sformat);
	  String dateString = formatter.format(currentTime);
	  return dateString;
	 }

	 /**
	  * ����Сʱʱ���Ĳ�ֵ,���뱣֤����ʱ�䶼��"HH:MM"�ĸ�ʽ�������ַ��͵ķ���
	  */
	 public static String getTwoHour(String st1, String st2) {
	  String[] kk = null;
	  String[] jj = null;
	  kk = st1.split(":");
	  jj = st2.split(":");
	  if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
	   return "0";
	  else {
	   double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
	   double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
	   if ((y - u) > 0)
	    return y - u + "";
	   else
	    return "0";
	  }
	 }

	 /**
	  * �õ��������ڼ�ļ������
	  */
	 public static String getTwoDay(String sj1, String sj2) {
	  SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
	  long day = 0;
	  try {
	   java.util.Date date = myFormatter.parse(sj1);
	   java.util.Date mydate = myFormatter.parse(sj2);
	   day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
	  } catch (Exception e) {
	   return "";
	  }
	  return day + "";
	 }

	 /**
	  * ʱ��ǰ�ƻ���Ʒ���,����JJ��ʾ����.
	  */
	 public static String getPreTime(String sj1, String jjj) {
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String mydate1 = "";
	  try {
	   Date date1 = format.parse(sj1);
	   long Time = (date1.getTime() / 1000) + Integer.parseInt(jjj) * 60;
	   date1.setTime(Time * 1000);
	   mydate1 = format.format(date1);
	  } catch (Exception e) {
	  }
	  return mydate1;
	 }

	 /**
	  * �õ�һ��ʱ���Ӻ��ǰ�Ƽ����ʱ��,nowdateΪʱ��,delayΪǰ�ƻ���ӵ�����
	  */
	 public static String getNextDay(String nowdate, String delay) {
	  try{
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	  String mdate = "";
	  Date d = strToDate(nowdate);
	  long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
	  d.setTime(myTime * 1000);
	  mdate = format.format(d)+" 23:59:59";
	  return mdate;
	  }catch(Exception e){
	   return "";
	  }
	 }

	 /**
	  * �ж��Ƿ�����
	  * 
	  * @param ddate
	  * @return
	  */
	 public static boolean isLeapYear(String ddate) {

	  /**
	   * ��ϸ��ƣ� 1.��400���������꣬���� 2.���ܱ�4������������ 3.�ܱ�4����ͬʱ���ܱ�100������������
	   * 3.�ܱ�4����ͬʱ�ܱ�100������������
	   */
	  Date d = strToDate(ddate);
	  GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	  gc.setTime(d);
	  int year = gc.get(Calendar.YEAR);
	  if ((year % 400) == 0)
	   return true;
	  else if ((year % 4) == 0) {
	   if ((year % 100) == 0)
	    return false;
	   else
	    return true;
	  } else
	   return false;
	 }

	 /**
	  * ��������ʱ���ʽ 26 Apr 2006
	  * 
	  * @param str
	  * @return
	  */
	 public static String getEDate(String str) {
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  ParsePosition pos = new ParsePosition(0);
	  Date strtodate = formatter.parse(str, pos);
	  String j = strtodate.toString();
	  String[] k = j.split(" ");
	  return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
	 }

	 /**
	  * ��ȡһ���µ����һ��
	  * 
	  * @param dat
	  * @return
	  */
	 public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
	  String str = dat.substring(0, 8);
	  String month = dat.substring(5, 7);
	  int mon = Integer.parseInt(month);
	  if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
	   str += "31";
	  } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
	   str += "30";
	  } else {
	   if (isLeapYear(dat)) {
	    str += "29";
	   } else {
	    str += "28";
	   }
	  }
	  return str;
	 }

	 /**
	  * �ж϶���ʱ���Ƿ���ͬһ����
	  * 
	  * @param date1
	  * @param date2
	  * @return
	  */
	 public static boolean isSameWeekDates(Date date1, Date date2) {
	  Calendar cal1 = Calendar.getInstance();
	  Calendar cal2 = Calendar.getInstance();
	  cal1.setTime(date1);
	  cal2.setTime(date2);
	  int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
	  if (0 == subYear) {
	   if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
	    return true;
	  } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
	   // ���12�µ����һ�ܺ�������һ�ܵĻ������һ�ܼ���������ĵ�һ��
	   if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
	    return true;
	  } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
	   if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
	    return true;
	  }
	  return false;
	 }



	 /**
	  * ���һ���������ڵ��ܵ����ڼ������ڣ���Ҫ�ҳ�2002��2��3�������ܵ�����һ�Ǽ���
	  * 
	  * @param sdate
	  * @param num
	  * @return
	  */
	 public static String getWeek(String sdate, String num) {
	  // ��ת��Ϊʱ��
	  Date dd = DateUtil.strToDate(sdate);
	  Calendar c = Calendar.getInstance();
	  c.setTime(dd);
	  if (num.equals("1")) // ��������һ���ڵ�����
	   c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	  else if (num.equals("2")) // �������ڶ����ڵ�����
	   c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
	  else if (num.equals("3")) // �������������ڵ�����
	   c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
	  else if (num.equals("4")) // �������������ڵ�����
	   c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
	  else if (num.equals("5")) // �������������ڵ�����
	   c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
	  else if (num.equals("6")) // �������������ڵ�����
	   c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
	  else if (num.equals("0")) // �������������ڵ�����
	   c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	  return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	 }

	 /**
	  * ����һ�����ڣ����������ڼ����ַ���
	  * 
	  * @param sdate
	  * @return
	  */
	 public static String getWeek(String sdate) {
	  // ��ת��Ϊʱ��
	  Date date = DateUtil.strToDate(sdate);
	  Calendar c = Calendar.getInstance();
	  c.setTime(date);
	  // int hour=c.get(Calendar.DAY_OF_WEEK);
	  // hour�д�ľ������ڼ��ˣ��䷶Χ 1~7
	  // 1=������ 7=����������������
	  return new SimpleDateFormat("EEEE").format(c.getTime());
	 }
	 public static String getWeekStr(String sdate){
	  String str = "";
	  str = DateUtil.getWeek(sdate);
	  if("1".equals(str)){
	   str = "������";
	  }else if("2".equals(str)){
	   str = "����һ";
	  }else if("3".equals(str)){
	   str = "���ڶ�";
	  }else if("4".equals(str)){
	   str = "������";
	  }else if("5".equals(str)){
	   str = "������";
	  }else if("6".equals(str)){
	   str = "������";
	  }else if("7".equals(str)){
	   str = "������";
	  }
	  return str;
	 }

	 /**
	  * ����ʱ��֮�������
	  * 
	  * @param date1
	  * @param date2
	  * @return
	  */
	 public static long getDays(String date1, String date2) {
	  if (date1 == null || date1.equals(""))
	   return 0;
	  if (date2 == null || date2.equals(""))
	   return 0;
	  // ת��Ϊ��׼ʱ��
	  SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
	  java.util.Date date = null;
	  java.util.Date mydate = null;
	  try {
	   date = myFormatter.parse(date1);
	   mydate = myFormatter.parse(date2);
	  } catch (Exception e) {
	  }
	  long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
	  return day;
	 }

	 /**
	  * �γ����µ����� �� ���ݴ����һ��ʱ�䷵��һ���ṹ ������ ����һ ���ڶ� ������ ������ ������ ������ �����ǵ��µĸ���ʱ��
	  * �˺������ظ�������һ�����������ڵ�����
	  * 
	  * @param sdate
	  * @return
	  */
	 public static String getNowMonth(String sdate) {
	  // ȡ��ʱ�������µ�һ��
	  sdate = sdate.substring(0, 8) + "01";

	  // �õ�����µ�1�������ڼ�
	  Date date = DateUtil.strToDate(sdate);
	  Calendar c = Calendar.getInstance();
	  c.setTime(date);
	  int u = c.get(Calendar.DAY_OF_WEEK);
	  String newday = DateUtil.getNextDay(sdate, (1 - u) + "");
	  return newday;
	 }

	 /**
	  * ȡ�����ݿ����� ���ɸ�ʽΪyyyymmddhhmmss+kλ�����
	  * 
	  * @param k
	  *            ��ʾ��ȡ��λ������������Լ���
	  */

	 public static String getNo(int k) {

	  return getUserDate("yyyyMMddhhmmss") + getRandom(k);
	 }

	 /**
	  * ����һ�������
	  * 
	  * @param i
	  * @return
	  */
	 public static String getRandom(int i) {
	  Random jjj = new Random();
	  // int suiJiShu = jjj.nextInt(9);
	  if (i == 0)
	   return "";
	  String jj = "";
	  for (int k = 0; k < i; k++) {
	   jj = jj + jjj.nextInt(9);
	  }
	  return jj;
	 }

	 /**
	  * 
	  * @param args
	  */
	 public static boolean RightDate(String date) {

	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	  ;
	  if (date == null)
	   return false;
	  if (date.length() > 10) {
	   sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	  } else {
	   sdf = new SimpleDateFormat("yyyy-MM-dd");
	  }
	  try {
	   sdf.parse(date);
	  } catch (ParseException pe) {
	   return false;
	  }
	  return true;
	 }
	 
	 
	 /**������12��26��-12��31�ա�2012��1��1��-2012��1��8��ת��Ϊ2012-xx-xx.
	  * û��������Ϊ�ǵ�ǰ���
	 * @param date
	 * @return
	 */
	public static String[]  TransformDate(String date){
		 String[] dateFinal = new String[2];
		 if(date != null && !date.equals("") && date.contains("��")){
			 dateFinal = date.split("-");
			 for(int i=0;i<dateFinal.length;i++){
				 dateFinal[i] = dateFinal[i].replace("��", "-").replace("��", "-").replace("��", "");
			 }
		 }else if(date != null && !date.equals("") && date.contains(".")){
			dateFinal[0] = date.replace(".", "-");
		 }else if(date != null && !date.equals("")){
			 dateFinal = date.split("-");
			 Calendar cal = Calendar.getInstance();
			 int year = cal.get(Calendar.YEAR);
			 for(int i=0;i<dateFinal.length;i++){
				 dateFinal[i] = year + "-" + dateFinal[i].replace("��", "-").replace("��", "");
			 }
		 }
		 return dateFinal;
	 }
	
	/**�õ�����������·ݣ���ʽ201301,201212,201211
	 * @return
	 */
	public static String[] recentlyThreeMonth(){
		String[] timeRes = new String[3];
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		if(month<10){
			timeRes[0]=year+"0"+month;
		}else{
			timeRes[0]=year+""+month;
		}
		for(int i=1;i<3;i++){
			if((month - 1) <= 0){  
			    month = month + 12 - 1;  
			    year = year -1;  
			}else {  
			    month = month - 1;  
			} 
			if(month<10){
				timeRes[i]=year+"0"+month;
			}else{
				timeRes[i]=year+""+month;
			}
		}
		return timeRes;
	}
	
	public static String  getBetweenTime1(String time1,String time2) throws Exception{
		if(time1==null || time2==null ||time1.equals("")||time2.equals("")){
			return "0";
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date now = df.parse(time1);
		java.util.Date date=df.parse(time2);
		long l=now.getTime()-date.getTime();
		long day=l/(24*60*60*1000);
		long hour=(l/(60*60*1000)-day*24);
		long min=((l/(60*1000))-day*24*60-hour*60);
		long s=(l/1000-day*24*60*60-hour*60*60-min*60);
		//System.out.println(""+day+"��"+hour+"Сʱ"+min+"��"+s+"��");
		String hour1="";
		if(hour<10){
			hour1="0"+hour;
		}else{
			hour1=""+hour;
		}
		String min1="";
		if(min<10){
			min1="0"+min;
		}else{
			min1=""+min;
		}
		String s1="";
		if(s<10){
			s1="0"+s;
		}else{
			s1=""+s;
		}
		//System.out.println(s+"=="+min);
		String str=day+" �� "+hour1+":"+min1+":"+s1;
		return str;
	}
	
	
	/**
	 * 
	 * ������һ����
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getpreMonth(String date) throws ParseException {
		date = date + "-01";
        DateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c1 = Calendar.getInstance();
        c1.setTime(sdf.parse(date));
        c1.add(Calendar.MONTH, -1);
        return sdf.format(c1.getTime()).substring(0, 7);
    }
	
	/**
	 * 
	 * ������һ����
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getLastMonth(String date) throws ParseException {
		date = date + "-01";
        DateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c1 = Calendar.getInstance();
        c1.setTime(sdf.parse(date));
        c1.add(Calendar.MONTH, 1);
        return sdf.format(c1.getTime()).substring(0, 7);
    }
	//�Ƚ� ��ʱʱ��
	public static boolean compareTimingTime(String date){
		boolean flag=false;
		if(date!=null && !date.equals("")){
			if(date.compareTo(getPreTime(getStrDate_24(),"20"))>=0 && date.compareTo(getNextDay(getStrDate_24(),"3"))<=0){
				flag=true;
			}
		}
		return flag;
    }
	
	public static void main(String[] args) throws Exception {
		System.out.println("20����֮��:"+getPreTime(getStrDate_24(),"20"));
		System.out.println("3��֮��:"+getNextDay(getStrDate_24(),"3"));
	}
	
}

