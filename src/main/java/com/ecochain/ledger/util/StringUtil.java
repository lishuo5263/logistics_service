package com.ecochain.ledger.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.*;

/**
 * <p>公共方法类</p>
 * <p>提供字符串处理的实用方法集</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * @author lihongxuan
 * @version 1.0
 *
 */

public class StringUtil
{
	public StringUtil()
	{
	}
	public static final String escapeForIntro(String string)
	{
		//			 String str = escapeHTMLTags(string);
		String str = string;
		str = replace(str, "\r\n", "<br>");
		str = replace(str, "\n", "<br>");
		str = replace(str, "'", "\\'");
		return replace(str, "\r", "");

	}
	/**
	 * 得到非空的字符串，若字符串对象为null，则返回""。
	 * @param objValue Object待转换的原字符串对象
	 * @return String 转换后的字符串
	 * */
	public static String getNotNullStr(Object objValue)
	{
		return (objValue == null ? "" : objValue.toString());
	}
	
	public static char ascii2Char(int ASCII) {  
        return (char) ASCII;  
    }  
  
    public static int char2ASCII(char c) {  
        return (int) c;  
    }  
    
	/**
	 * 得到非空的字符串，若字符串为null，则返回""。
	 * @param strValue String待转换的原字符串
	 * @return String 转换后的字符串
	 * */
	public static String getNotNullStr(String strValue)
	{
		return (strValue == null ? "" : strValue.trim());
	}

	public static void main(String[] args) {
		/*System.out.println(ChineseStringToAscii("汉字"));*/
	}
	
	/**
	 * 将中文转化成AscII码以便存入数据库
	 * @param s  中文字符串
	 * @return 16进制字符串
	 *//*
	public static String ChineseStringToAscii(String s)
	{
		try
		{
			CharToByteConverter toByte = CharToByteConverter.getConverter("GBK");
			byte[] orig = toByte.convertAll(s.toCharArray());
			char[] dest = new char[orig.length];
			for (int i = 0; i < orig.length; i++)
				dest[i] = (char) (orig[i] & 0xFF);
			return new String(dest);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return s;
		}
	}
	*//**
	 * 将UTF-8转化成AscII码以便存入数据库
	 * @param s 中文字符串
	 * @return 16进制字符串
	 *//*
	public static String ChineseStringToUTF(String s)
	{
		try
		{
			CharToByteConverter toByte = CharToByteConverter.getConverter("UTF-8");
			byte[] orig = toByte.convertAll(s.toCharArray());
			char[] dest = new char[orig.length];
			for (int i = 0; i < orig.length; i++)
				dest[i] = (char) (orig[i] & 0xFF);
			return new String(dest);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return s;
		}
	}*/

	/**
	 * 将AscII字符转换成汉字
	 * @param s -  ASCII字符串
	 * @return 汉字
	 */
	/*public static String AsciiToChineseString(String s)
	{
		char[] orig = s.toCharArray();
		byte[] dest = new byte[orig.length];
		for (int i = 0; i < orig.length; i++)
			dest[i] = (byte) (orig[i] & 0xFF);
		try
		{
			ByteToCharConverter toChar = ByteToCharConverter.getConverter("GBK");
			return new String(toChar.convertAll(dest));
		}
		catch (Exception e)
		{
			System.out.println(e);
			return s;
		}
	}*/

//	/**
//	 * 使用正则表达式进行字符串内容替换
//	 * @param regularExpression 正则表达式
//	 * @param sub 替换的字符串
//	 * @param input 要替换的字符串
//	 * @return String 替换后的字符串
//	 */
//	public static synchronized String regexReplace(String regularExpression, String sub, String input)
//	{
//		Pattern pattern = PatternFactory.getPattern(regularExpression);
//		Matcher matcher = pattern.matcher(input);
//		StringBuffer sb = new StringBuffer();
//		while (matcher.find())
//		{
//			matcher.appendReplacement(sb, sub);
//		}
//		matcher.appendTail(sb);
//		return sb.toString();
//	}

	/**
	 * 判断一个字符串中是否包含符合正则表达式定义的匹配条件的子串
	 * @param regularExpression - 正则表达式
	 * @param input - 待检查字符串
	 * @return - 若输入字符串中包含符合正则表达式定义的匹配条件的子串，则返回true，否则返回false
	 */
//	//正则表达式匹配判断
//	public static synchronized boolean exist(String regularExpression, String input)
//	{
//		Pattern pattern = PatternFactory.getPattern(regularExpression);
//		Matcher matcher = pattern.matcher(input);
//		return matcher.find();
//	}

	/**
	 * 用"0"补足一个字符串到指定长度
	 * @param str -  源字符串
	 * @param size - 补足后应达到的长度
	 * @return - 补零后的结果
	 */
	public static String fillZero(String str, int size)
	{
		String result;
		if (str.length() < size){
			char[] s = new char[size - str.length()];
			for (int i = 0; i < (size - str.length()); i++)
			{
				s[i] = '0';
			}
			result = new String(s) + str;
		}else{
			result = str;
		}
		return result;
	}

	/**
	 * 根据字符串（文件类型或者多行输入类型）获取字符串数组
	 * @param str1 String 输入字符串
	 * @return String[] 返回结果
	 */
	public static String[] getStrArryByString(String str1)
	{
		if (str1.indexOf("\t") > 0)
		{
			for (int i = 0; i < str1.length(); i++)
			{
				if (str1.substring(i, i + 1).equals("\t"))
				{
					str1 = str1.substring(0, i) + " " + str1.substring(i + 1, str1.length());
				}
			}
		}
		StringTokenizer stringTokenizer = new StringTokenizer(str1, "\r\n");
		String[] strId = new String[stringTokenizer.countTokens()];
		int i = 0;
		while (stringTokenizer.hasMoreTokens())
		{
			strId[i] = stringTokenizer.nextToken();
			i++;
		}
		return strId;
	}
	/**
	 * 判断一个字符串是否为 NUll 或为空
	 * @param inStr inStr
	 * @return boolean
	 */
	public static boolean isValid(String inStr)
	{
		if (inStr == null)
		{
			return false;
		}
		else if (inStr.equals(""))
		{
			return false;
		}
		else if (inStr.equals("null"))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * 判断一个字符串是否为 NUll 或为空,不为空并且长度大于0返回true
	 * @param  str
	 * @return boolean
	 */	
	public static boolean checkNotNull(String str){
		boolean flag = false;
		
		if(str != null && str.trim().length() != 0)	
			flag = true;
		return flag;
	}
	/**
	 * 获得指定长度的空格
	 * @param spaceNum spaceNum
	 * @return String
	 */
	public static String getStrSpace(int spaceNum)
	{
		return getStrWithSameElement(spaceNum, " ");
	}
	/**
	 * 获得指定长度的字符串
	 * @param num int
	 * @param element String
	 * @return String
	 */
	public static String getStrWithSameElement(int num, String element)
	{
		if (num <= 0)
		{
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++)
		{
			sb.append(element);
		}
		return sb.toString();
	}
	/**
	 * 从右或左加空格
	 * @param strIn String
	 * @param totalLength int
	 * @param isRightAlign boolean
	 * @return String
	 */
	public static String getFillString(String strIn, int totalLength, boolean isRightAlign)
	{
		int spaceLength = 0;
		String spaces = null;
		String strOut = null;

		if (strIn == null)
		{
			strIn = "";
		}

		spaceLength = totalLength - strIn.length();

		if (spaceLength < 0)
		{
			spaceLength = 0;
		}
		spaces = StringUtil.getStrSpace(spaceLength);

		if (isRightAlign)
		{
			strOut = spaces + strIn;
		}
		else
		{
			strOut = strIn + spaces;

		}
		return strOut;
	}
	/**
	 * 以String类型返回错误抛出的堆栈信息
	 * @param t Throwable
	 * @return String
	 */
	public static String getStackTrace(Throwable t)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		t.printStackTrace(pw);
		return sw.toString();
	}
	/**
	 * 转换字符串第一个字符为大写
	 * @param str String
	 * @return String
	 */
	public static String getStrByUpperFirstChar(String str)
	{
		try
		{
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
		catch (Exception e)
		{
			return "";
		}

	}
	/**
	 * 转换字符串第一个字符为小写
	 * @param str String
	 * @return String
	 */
	public static String getStrByLowerFirstChar(String str)
	{
		try
		{
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		}
		catch (Exception e)
		{
			return "";
		}

	}
	/**
	 * 通过字符串转换成相应的整型，并返回。
	 * @param strValue String 待转换的字符串
	 * @return int 转换完成的整型
	 * */
	public static int getStrToInt(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		int iValue = 0;
		try
		{
			iValue = new Integer(strValue.trim()).intValue();
		}
		catch (Exception ex)
		{
			iValue = 0;
		}
		return iValue;
	}

	/**
	 * 通过字符串转换成相应的DOUBLE，并返回。
	 * @param strValue String 待转换的字符串
	 * @return double 转换完成的DOUBLE
	 * */
	public static double getStrToDouble(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		double dValue = 0;
		try
		{
			dValue = Double.parseDouble(strValue.trim());
		}
		catch (Exception ex)
		{
			dValue = 0;
		}
		return dValue;
	}

	/**
	 * 通过字符串转换成相应的短整型，并返回。
	 * @param strValue String 待转换的字符串
	 * @return short 转换完成的短整型
	 * */
	public static short getStrToShort(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		short iValue = 0;
		try
		{
			iValue = new Short(strValue.trim()).shortValue();
		}
		catch (Exception ex)
		{
			iValue = 0;
		}
		return iValue;
	}

	/**
	 * 通过字符串转换成相应的长整型，并返回。
	 * @param strValue String 待转换的字符串
	 * @return long 转换完成的长整型
	 * */
	public static long getStrToLong(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		long lValue = 0;
		try
		{
			lValue = new Long(strValue.trim()).longValue();
		}
		catch (Exception ex)
		{
			lValue = 0;
		}
		return lValue;
	}

	public static String toLengthForEn(String str, int length)
	{
		if (null != str)
		{
			if (str.length() <= length)
			{
				return str;
			}
			else
			{
				str = str.substring(0, length - 2);
				str = str + "..";
				return str;
			}
		}
		else
		{
			return "";
		}
	}

	/**
	  * 降字符串转换成给定长度的字符串，如超出的话截断，并在最后以两个点结尾
	  * @param str String
	  * @param length int
	  * @return String
	  */
	public static String toLengthForIntroduce(String str, int length)
	{
		str = delTag(str);

		byte[] strByte = str.getBytes();
		int byteLength = strByte.length;
		char[] charArray;
		StringBuffer buff = new StringBuffer();
		if (byteLength > (length * 2))
		{
			charArray = str.toCharArray();
			int resultLength = 0;
			for (int i = 0; i < charArray.length; i++)
			{
				resultLength += String.valueOf(charArray[i]).getBytes().length;
				if (resultLength > (length * 2))
				{
					break;
				}
				buff.append(charArray[i]);

			}
			buff.append("..");
			str = buff.toString();
		}

		//		str = replace(str, "'", "\\'");
		str = replace(str, "\"", "\\\"");
		str = replace(str, "，", ",");
		return str;

	}

	public static String delTag(String str)
	{
		str = str + "<>";
		StringBuffer buff = new StringBuffer();
		int start = 0;
		int end = 0;

		while (str.length() > 0)
		{
			start = str.indexOf("<");
			end = str.indexOf(">");
			if (start > 0)
			{
				buff.append(str.substring(0, start));
			}
			if (end > 0 && end <= str.length())
			{
				str = str.substring(end + 1, str.length());
			}
			else
			{
				str = "";
			}

		}
		String result = buff.toString();

		while (result.startsWith(" "))
		{

			result = result.substring(result.indexOf(" ") + 1, result.length());

		}
		return result;

	}

	public static final String replace(String line, String oldString, String newString)
	{
		if (line == null)
		{
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0)
		{
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0)
			{
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;

	}
	//	Replace
	public static String Replace(String source, String oldString, String newString)
	{
		if (source == null)
		{
			return null;
		}
		StringBuffer output = new StringBuffer();
		int lengOfsource = source.length();
		int lengOfold = oldString.length();
		int posStart = 0;
		int pos;
		while ((pos = source.indexOf(oldString, posStart)) >= 0)
		{
			output.append(source.substring(posStart, pos));
			output.append(newString);
			posStart = pos + lengOfold;
		}
		if (posStart < lengOfsource)
		{
			output.append(source.substring(posStart));
		}
		return output.toString();
	}

	//此函数前台使用中，请勿随便修改，不然会造成显示混乱(以前修改版本在下方注释中)
	public static String toHtml(String s)
	{
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "    ");
		s = Replace(s, "\r\n", "\n");
		s = Replace(s, "\n", "<br>");
		//s = Replace(s, " ", "&nbsp;");
		s = Replace(s, "'", "&#39;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "\\", "&#92;");
		s = Replace(s, "%", "％");
		//	s = Replace(s, "&", "&amp;");
		return s;
	}
	//	逆
	public static String unHtml(String s)
	{

		//s = Replace(s, "&lt;", "<");
		//s = Replace(s, "&gt;", ">");
		//		s = Replace(s, "    ", "\t");
		//		s = Replace(s, "\n", "\r\n");
		s = Replace(s, "<br>", "\n");
		//		s = Replace(s, "&nbsp;", " ");
		//		s = Replace(s, "&amp;", "&");
		//		s = Replace(s, "&#39;", "'");
		//		s = Replace(s, "&#92;", "\\");
		//		s = Replace(s, "％", "%");
		return s;
	}

	//	此函数后台使用中，请勿随便修改，不然会造成显示混乱(以前修改版本在下方注释中)
	public static String toHtmlBack(String s)
	{
		//显示
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "\\", "&#92;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "    ");
		s = Replace(s, "\r\n", "\n");
		//		s = Replace(s, "\n", "<br>");
//		s = Replace(s, " ", "&nbsp;");
		//		s = Replace(s, "'", "&#39;");
		//		s = Replace(s, "%", "%");

		return s;
	}
	//	逆
	public static String unHtmlBack(String s)
	{
		s = Replace(s, "&lt;", "<");
		s = Replace(s, "&gt;", ">");
		s = Replace(s, "    ", "\t");
		s = Replace(s, "\n", "\r\n");
		s = Replace(s, "<br>", "\n");
		s = Replace(s, "&nbsp;", " ");
		s = Replace(s, "&amp;", "&");
		s = Replace(s, "&#39;", "'");
		s = Replace(s, "&#92;", "\\");
		s = Replace(s, "％", "%");
		return s;
	}
	/*
	public static String toHtml(String s)
	{
		//显示
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "\\", "&#92;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "    ");
		s = Replace(s, "\r\n", "\n");
	//		s = Replace(s, "\n", "<br>");
		s = Replace(s, " ", "&nbsp;");
	//		s = Replace(s, "'", "&#39;");
	//		s = Replace(s, "%", "%");
		
		return s;
	}
	
	public static String unHtml(String s)
	{
		s = Replace(s, "&lt;", "<");
		s = Replace(s, "&gt;", ">");
		s = Replace(s, "    ", "\t");
		s = Replace(s, "\n", "\r\n");
		s = Replace(s, "<br>", "\n");
		s = Replace(s, "&nbsp;", " ");
		s = Replace(s, "&amp;", "&");
		s = Replace(s, "&#39;", "'");
		s = Replace(s, "&#92;", "\\");
		s = Replace(s, "％", "%");
		return s;
	}
	*/
	//判断是否含有中文，如果含有中文返回ture
	public static boolean containsChinese(String str) throws UnsupportedEncodingException
	{

		if (str.length() < (str.getBytes()).length)
			return true;

		return false;

		//	  for (int i = 0; i < username.length(); i++) {
		//		String unit=Character.toString(username.charAt(i));
		//		byte[] unitByte=unit.getBytes("GBK");
		////  		((unitByte[0]+256)*256 + (unitByte[1]+256)) <= 0xFEFE)
		//	   if (unitByte.length == 2)
		//		{
		//		  return true;
		//		}
		//	  }
		//	  return false;

	}

	public static boolean isEmpty(String str)
	{
		if (str == null)
			return true;
		return "".equals(str.trim());
	}

	public static String[] split(String str1, String str2)
	{
		return StringUtils.split(str1, str2);
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>将字符串转成list<br>
	 * <b>作者：</b>lihongxuan<br>
	 * <b>日期：</b> Oct 28, 2013 <br>
	 * @param exp 分割符 如","
	 * @param value 
	 * @return
	 */
	public static List<Long> StringToListLong(String value,String exp)
	{	List<Long> resultList = new ArrayList<Long>();
	String[] vals = split(value , exp);
	for(String val  : vals){
		resultList.add(Long.parseLong(val));
	}
	return resultList;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>将字符串转成list<br>
	 * <b>作者：</b>lihongxuan<br>
	 * <b>日期：</b> Oct 28, 2013 <br>
	 * @param exp 分割符 如","
	 * @param value 
	 * @return
	 */
	public static List<String> StringToList(String value,String exp)
	{	List<String> resultList = new ArrayList<String>();
		String[] vals = split(value , exp);
		for(String val  : vals){
			resultList.add(val);
		}
		return resultList;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>数字转换成字符串<br>
	 * <b>作者：</b>lihongxuan<br>
	 * <b>日期：</b> Jul 30, 2013 <br>
	 * @param arrs
	 * @return
	 */
	public static String arrayToString(String[] arrs)
	{
		StringBuffer result= new StringBuffer("");
		if(arrs != null && arrs.length >0){
			for(int i=0;i<arrs.length;i++){
				
				if(!result.toString().equals("")){
					result.append(",");
				}
				if(arrs[i] != null &&  !"".equals(arrs[i].trim())){
					result.append(arrs[i]);
				}
			}
		} 
		return result.toString();
	}
	
	
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>数字转换成字符串<br>
	 * <b>作者：</b>lihongxuan<br>
	 * <b>日期：</b> Jul 30, 2013 <br>
	 * @param arrs
	 * @return
	 */
	public static String arrayToString(Object[] arrs)
	{
		StringBuffer result= new StringBuffer("");
		if(arrs != null && arrs.length >0){
			for(int i=0;i<arrs.length;i++){
				
				if(!result.toString().equals("")){
					result.append(",");
				}
				if(arrs[i] != null &&  !"".equals(arrs[i].toString().trim())){
					result.append(arrs[i]);
				}
			}
		} 
		return result.toString();
	}

	public static String left(String str, int length)
	{
		return StringUtils.left(str, length);
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>替换回车<br>
	 * <b>作者：</b>lihongxuan<br>
	 * <b>日期：</b> Oct 26, 2013 <br>
	 * @param str
	 * @return
	 */
	public static String replaceHuiche(String str){
	   String after = str.replaceAll("\r\n", "");
	   return after;
	}


	/**
	 * 根据输入的长度截取字符串，单个单词超过输入长度的强制加<BR>换行
	 * @param str 输入的字符串
	 * @param len 输入的长度
	 * @return 处理过后的字符串
	 */
	public static String truncateStr(String str, int len)
	{
		if (str != null && !("").equalsIgnoreCase(str))
		{

			String strs[] = str.split(" ");
			StringBuffer buff = new StringBuffer();
			if (strs.length > 0)
			{
				for (int i = 0; i < strs.length; i++)
				{
					StringBuffer temp = new StringBuffer();
					while (strs[i].length() > len)
					{
						temp.append(strs[i].substring(0, len) + "<BR>");
						strs[i] = strs[i].substring(len);
					}
					temp.append(strs[i]);
					buff.append(temp.toString() + " ");
				}

			}
			return buff.toString();
		}
		else
		{
			return "";
		}
	}
	
	public static String truncateStr2(String str, int len)
	{
		if (str != null && !("").equalsIgnoreCase(str) && len!=0)
		{
			String strs[] = str.split(" ");
			
			StringBuffer buff = new StringBuffer();
			for (int i = 0; i < strs.length; i++)
			{
				StringBuffer temp = new StringBuffer();
				String tempstr = "";
				while (strs[i].length() > len)
				{
					tempstr = strs[i].substring(0, len);
					tempstr = tempstr.replaceAll(" ","&nbsp; ");
					tempstr = tempstr.replaceAll("<","&lt; ");
					tempstr = tempstr.replaceAll("\n","<br> ").replaceAll("\"","&quot; ").replaceAll("'","&#39; ");
					tempstr = tempstr + "<br>";
					temp.append(tempstr);
					
					strs[i] = strs[i].substring(len);
				}
				tempstr = strs[i];
				tempstr = tempstr.replaceAll(" ","&nbsp; ");
				tempstr = tempstr.replaceAll("<","&lt; ");
				tempstr = tempstr.replaceAll("\n","<br> ").replaceAll("\"","&quot; ").replaceAll("'","&#39; ");
								
				temp.append(tempstr);
				buff.append(temp.toString() + " ");
			}
			
			if(buff.length() > 0)
				return buff.toString().substring(0,buff.length()-1);
			else
				return str;
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * 编码转换，从unicode转换为GBK
	 * @param l_S_Source 输入字符串
	 * @return str编码转换后的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String unicodeToGB(String l_S_Source) throws UnsupportedEncodingException
	{
		String l_S_Desc = "";
		if (l_S_Source != null && !l_S_Source.trim().equals(""))
		{
			byte l_b_Proc[] = l_S_Source.getBytes("GBK");
			l_S_Desc = new String(l_b_Proc, "ISO8859_1");
		}
		return l_S_Desc;
	}
	/**
	 * 编码转换，从GBK转换为unicode
	 * @param l_S_Source 输入字符串
	 * @return str 编码转换后的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String GBToUnicode(String l_S_Source) throws UnsupportedEncodingException
	{
		String l_S_Desc = "";
		if (l_S_Source != null && !l_S_Source.trim().equals(""))
		{
			byte l_b_Proc[] = l_S_Source.getBytes("ISO8859_1");
			l_S_Desc = new String(l_b_Proc, "GBK");
		}
		return l_S_Desc;
	}

	/**
	 * Escapes a <code>String</code> according the JavaScript string literal
	 * escaping rules. The resulting string will not be quoted.
	 * 
	 * <p>It escapes both <tt>'</tt> and <tt>"</tt>.
	 * In additional it escapes <tt>></tt> as <tt>\></tt> (to avoid
	 * <tt>&lt;/script></tt>). Furthermore, all characters under UCS code point
	 * 0x20, that has no dedicated escape sequence in JavaScript language, will
	 * be replaced with hexadecimal escape (<tt>\x<i>XX</i></tt>). 
	 */ 
	public static String javaScriptStringEnc(String s) {
		int ln = s.length();
		for (int i = 0; i < ln; i++) {
			char c = s.charAt(i);
			if (c == '"' || c == '\'' || c == '\\' || c == '>' || c < 0x20) {
				StringBuffer b = new StringBuffer(ln + 4);
				b.append(s.substring(0, i));
				while (true) {
					if (c == '"') {
						b.append("\\\"");
					} else if (c == '\'') {
						b.append("\\'");
					} else if (c == '\\') {
						b.append("\\\\");
					} else if (c == '>') {
						b.append("\\>");
					} else if (c < 0x20) {
						if (c == '\n') {
							b.append("\\n");
						} else if (c == '\r') {
							b.append("\\r");
						} else if (c == '\f') {
							b.append("\\f");
						} else if (c == '\b') {
							b.append("\\b");
						} else if (c == '\t') {
							b.append("\\t");
						} else {
							b.append("\\x");
							int x = c / 0x10;
							b.append((char)
									(x < 0xA ? x + '0' : x - 0xA + 'A'));
							x = c & 0xF;
							b.append((char)
									(x < 0xA ? x + '0' : x - 0xA + 'A'));
						}
					} else {
						b.append(c);
					}
					i++;
					if (i >= ln) {
						return b.toString();
					}
					c = s.charAt(i);
				}
			} // if has to be escaped
		} // for each characters
		return s;
	}
	
	
	private static StringUtil instance = null;
	
	public static synchronized StringUtil getInstance()
	{
		if (instance == null)
		{
			instance = new StringUtil();
		}
		return instance;
	}
	/**
	 * 将多个连续空格替换为一个,"a  b"-->"a b"
	 * @param src
	 * @return
	 * @author WilliamLau
	 * @desc
	 */
	public static String trimContinuousSpace(String src){
		return src.replaceAll("\\s+", " ");
	}
	public static String replace(String src, String target, String rWith, int maxCount)
	{
		return StringUtils.replace(src, target, rWith, maxCount);
	}

	public static boolean equals(String str1, String str2)
	{
		return StringUtils.equals(str1, str2);
	}

	public static boolean isAlphanumeric(String str)
	{
		return StringUtils.isAlphanumeric(str);
	}

	public static boolean isNumeric(String str)
	{
		return StringUtils.isNumeric(str);
	}

	public static String[] stripAll(String[] strs)
	{
		return StringUtils.stripAll(strs);
	}
//	public static void main(String[] args)
//	{
//		System.out.println(wcsUnescape("#lt;strong#gt;#lt;span style=#quot;color:#e53333;#quot;#gt;1111111111#lt;/span#gt;#lt;/strong#gt;"));
//		//		String testStr = "<input > &    \\r\\n    \\n", newStr;
//		//		newStr = toHtml(testStr);
//		//		System.out.println(testStr);
//		//		System.out.println(newStr);
//		//		System.out.println(unHtml(newStr));
//		//		String aaa = "中文中文中文bcdefghijk";
//
//		//        		String bbb = toLengthForIntroduce(aaa,5);
//		//		System.out.println(bbb);
//		//		Object aa = null;
//		//		String bb = "  aaaa  ";
//
//		try
//		{
//			//			System.out.println(getNotNullStr(aa));
//			//			System.out.println(getNotNullStr(bb));
//			//			System.out.println(containsChinese(aaa));
//			//			System.out.println(containsChinese(aaa));
//			//			System.out.println(containsChinese(bb));
//			//String abc = null;
//			//System.out.println(toLengthForEn(abc, 3));
//			/*String num = "05";
//			if(num.indexOf(".")==-1){
//				num = num + ".00";
//			}*/
////			String str="<p >ksdkks </br> </p>    jkskskeeee <div>  lllll </div>";
////			System.out.println(delTag(str));
//			//System.out.println(toFloatNumber("5.2"));
//		}
//		catch (Exception e)
//		{
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//
//	}
//
	public static String toFloatNumber(String num) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		return nf.format(Double.parseDouble(num));
	}
	
	
	public static String toFloatNumber(Double num, int accuracy){
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(accuracy);
		nf.setMinimumFractionDigits(accuracy);
		return nf.format(num);
	}
	
	
	public static String wcsUnescape(String str) {
		str = str.replace("#lt;", "<");
		str = str.replace("#gt;", ">");
		str = str.replace("#quot;", "\"");
		str = str.replace("#amp;amp;", "&");
		str = str.replace("#amp;", "&");
		str = str.replace("#039;", "'");
		return str;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>返回string型的字节数<br>
	 * <b>作者：</b>lihongxuan<br>
	 * <b>日期：</b> Sep 2, 2013 <br>
	 * @param str
	 * @return
	 */
	public static int getByteLength(String str){
		if(str  == null){
			return 0;
		}
		return str.getBytes().length;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>详细的功能描述<br>
	 * <b>作者：</b>lihongxuan<br>
	 * <b>日期：</b> Sep 2, 2013 <br>
	 * @param str 字符
	 * @param limitLen 长度
	 * @return
	 */
	public static String getByteStr(String str,int limitLen){
		StringBuffer sb  = new StringBuffer();
		char[] chars =getNotNullStr(str).toCharArray();
		int len = 0;
		for(char c :  chars){
			len += getByteLength(String.valueOf(c));
			if(len<= limitLen){
				sb.append(c);
			}
		}
		return sb.toString();
		
	}
	
	 /**
     *@param  content 内容
     *@param  length 指定长度。 超出这个长度就截取字符串。
     *@param  padding 超出长度后，尾加上字符，如"..."，可以为空
     *@return 返回结果 如果内容没有超出指定的长度。则返回原字符串，超出长度后则截取到指定的长度的内容
     */
    public static String subStr(String content,Integer length,String padding) throws UnsupportedEncodingException{
    	String str = "";
    	int paddSize =  StringUtils.isBlank(padding)? 0 : padding.length();
    	//如果内容为空，或者小于指定的长度。则返回原内容
    	if(StringUtils.isBlank(content) ||  content.length() <= length){
    		return content;
    	}
    	str = content.substring(0, length-paddSize);
    	if(StringUtils.isNotBlank(padding)){
    		str += padding;
    	}
    	return str;
    }
	
//	public static void main(String[] args) {
//		String str="a  lihongxuanozejun";
//		System.out.println(getByteLength(str));
//		System.out.println(getByteStr(str,6));
//		
//		
//	}

	public static String[] sortArray(String[] array){
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i].compareTo(array[j]) > 0) {
					String temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		return array;
	}
	
	/**
	 * 使用 Map按key进行排序
	 * @param map
	 * @return
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}
	/**
	 * 生成编码
	 * @param prefix
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static String generateCode(String prefix, Long id) throws Exception {
		String date = DateUtil.getNowShortDate();
		NumberFormat formatter = NumberFormat.getNumberInstance();   
		formatter.setMinimumIntegerDigits(4);   
		formatter.setGroupingUsed(false);   
		String s = formatter.format(id);   
		String code = prefix + date + s;
		return code;
	}

	//判断数组中是否有重复值
	public static boolean checkRepeat(String[] array){
	    Set<String> set = new HashSet<String>();
	    for(String str : array){
	        set.add(str);
	    }
	    if(set.size() != array.length){
	        return true;//有重复
	    }else{
	        return false;//不重复
	    }
	}

	/**
	 * 判断一个数字是否为 NUll 或为0
	 * @param  integer
	 * @return boolean
	 */	
	public static boolean checkNotNull(Integer integer){
		boolean flag = false;
		
		if(integer != null && integer != 0)	
			flag = true;
		return flag;
	}
	
	/**
	 * 判断一个数字是否为 NUll 或为0
	 * @param  l
	 * @return boolean
	 */	
	public static boolean checkNotNull(Long l){
		boolean flag = false;
		
		if(l != null && l != 0)	
			flag = true;
		return flag;
	}
	
	/**
	 * @Description:把数组转换为一个用逗号分隔的字符串 ，以便于用in+String 查询
	 */
	public static String converToString(String[] ig) {
		String str = "";
		if (ig != null && ig.length > 0) {
			for (int i = 0; i < ig.length; i++) {
				str += ig[i] + ",";
			}
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}

	/**
	 * @Description:把list转换为一个用逗号分隔的字符串
	 */
	public static String listToString(List<?> list) {
		StringBuilder sb = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i < list.size() - 1) {
					sb.append(list.get(i) + ",");
				} else {
					sb.append(list.get(i));
				}
			}
		}
		return sb.toString();
	}
	
	/**
     * 将字符串补数,将sourString的<br>后面</br>用cChar补足cLen长度的字符串,如果字符串超长，则不做处理
     * @author kezhiyi
     * @param sourString 源字符串
     * @param cChar 补数用的字符
     * @param cLen 字符串的目标长度
     * @return 字符串
     */
    public static String RCh(String sourString, String cChar, int cLen)
    {
        int tLen = sourString.length();
        int i, iMax;
        String tReturn = "";
        if (tLen >= cLen)
        {
            return sourString;
        }
        iMax = cLen - tLen;
        for (i = 0; i < iMax; i++)
        {
            tReturn += cChar;
        }
        tReturn = sourString.trim() + tReturn.trim();
        return tReturn;
    }

    /**
     * 将字符串补数,将sourString的<br>前面</br>用cChar补足cLen长度的字符串,如果字符串超长，则不做处理
     * @author kezhiyi
     * @param sourString 源字符串
     * @param cChar 补数用的字符
     * @param cLen 字符串的目标长度
     * @return 字符串
     */
    public static String LCh(String sourString, String cChar, int cLen)
    {
        int mLen = sourString.length();
        int i, iMax;
        String tReturn = "";
        if (mLen >= cLen)
        {
            return sourString;
        }
        iMax = cLen - mLen;
        for (i = 0; i < iMax; i++)
        {
            tReturn += cChar;
        }
        tReturn = tReturn.trim() + sourString.trim();
        
        return tReturn;
    }
	
    /**
     * 获得导出的excel名称，如果xmlPath不为空，是导出模板，否则是导出列表数据
     * @param request
     * @param xmlPath
     * @param type
     * @return
     * @throws Exception
     * @throws UnsupportedEncodingException
     */
    public static String getFileName(HttpServletRequest request, String xmlPath, String type)
			throws Exception, UnsupportedEncodingException {
		String fileName = "";
		if(StringUtils.isNotBlank(xmlPath) && "modle".equals(xmlPath)){
			fileName = type + "模板.xlsx";
		}else if("errzip".equals(xmlPath)){
			fileName = type + DateUtil.getNowShortDate() + "【错误信息】.zip";
		}else if("downzip".equals(xmlPath)){
			fileName = type + DateUtil.getNowShortDate() + ".zip";
		}
		else{
			fileName = type + DateUtil.getNowShortDate() + ".xlsx";
		}
		String ua = request.getHeader("User-Agent");
		if(ua!=null && ua.toLowerCase().contains("firefox")){
			fileName = new String(fileName.getBytes("GBK"),"ISO8859-1");
		}else{
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}
		return fileName;
	}
	
    /**
     * 去除字符串中指定char字符
     * @author kezhiyi
     * @param tSourceStr 源字符串
     * @param tChar 需去除的字符
     * @return 去除后的字符串
     */
    public static String strClear(String tSourceStr,char tChar){
		 String dest=tSourceStr;
		 		
		 		int strLength = tSourceStr.length();
		 		char value[] = new char[strLength];
		 		for(int j=0;j<strLength;j++){
		 			value[j] = tSourceStr.charAt(j);
		 		}
	            int len = value.length;
	            int i = -1;
	            char[] val = value; 

	            while (++i < len) {
	                if (val[i] == tChar) {
	                    break;
	                }
	            }
	            if (i < len) {
	                char buf[] = new char[len];
	                for (int j = 0; j < i; j++) {
	                    buf[j] = val[j];
	                }
	                while (i < len) {
	                    char c = val[i];
	                    buf[i] = (c == tChar) ? (char)0 : c;
	                    i++;
	                }	               
	                dest = new String(buf);
	                return dest;
	            }	 
		return dest;
	 }
    
    /**
     * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
     * 例如：HelloWorld->HELLO_WORLD
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线大写方式命名的字符串
     */
    public static String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }
     
    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
     * 例如：HELLO_WORLD->HelloWorld
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel :  camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
    public static boolean isNotEmpty(String str) {
        if (str != null && !str.equals("")&&!str.equals("null"))
            return true;
        else
            return false;
    }
}
