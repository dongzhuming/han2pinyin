/**
 * 2006-10-23
 */
package com.urlshow.pinyin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * @author 徐晨阳
 * @version 1.0
 */
public abstract class PinYin {	
	private static String HAN_ZI;
	private static final String ZI_MU="[abcdefghijklmnopqrstuvwxyz]";
	/**
	 * 声母
	 */
	public static final int SHENG_MU=1;
	/**
	 * 全拼
	 */
	public static final int QUAN_PIN=2;
	/**
	 * 词组
	 */
	public static final int CI_ZU=3;
	/**
	 * 语句
	 */
	public static final int YU_JU=4;
	/**返回声母
	 *  获取汉字编码表
	 * @return hanZi 汉字编码表
	 */
	private static final String getHAN_ZI() {
		if(HAN_ZI==null){
			BufferedReader br=new BufferedReader(new InputStreamReader(PinYin.class.getResourceAsStream("hanzi.txt")));
			try {
				HAN_ZI=br.readLine();
				br.close();
			} catch (IOException e) {
				HAN_ZI="";
			}
		}
		return HAN_ZI;
	}
	/**
	 * 单个汉字转换拼音
	 * @param str 单个汉字 
	 * @return 全拼
	 */
	private static String zhuYin(String str){
		if(str==null||str.length()==0)
			return null;
		if(str.length()>1)
			str=str.substring(0, 1);
		if(ZI_MU.indexOf(str)>-1)
			return str;
		int i=getHAN_ZI().indexOf(str);
		if(i==-1)
			return str;
		return getHAN_ZI().substring(getHAN_ZI().indexOf("[",i)+1,getHAN_ZI().indexOf("]",i));
	}
	/**
	 * 根据类型、字符集将汉字转换成拼音
	 * @param str 汉字
	 * @param type 类型
	 * @param charsetName 字符集
	 * @return 拼音
	 * @throws UnsupportedEncodingException 字符集错误
	 */
	public static String getPinYin(String str,int type,String charsetName) throws UnsupportedEncodingException{
		if(charsetName==null||charsetName.length()==0||charsetName.toLowerCase().trim().equals("utf-8"))
			return getPinYin(str, type);
		return getPinYin(new String(str.getBytes(charsetName),"UTF-8"), type);
	}
	/**
	 * 将汉字转换成声母
	 * @param str 汉字
	 * @return 声母
	 */
	public static String getPinYin(String str){
		return getPinYin(str,SHENG_MU);
	}
	/**
	 * 根据字符集将汉字转换成声母类型、
	 * @param str 汉字
	 * @param charsetName 字符集
	 * @return 声母
	 * @throws UnsupportedEncodingException 字符集错误
	 */
	public static String getPinYin(String str,String charsetName) throws UnsupportedEncodingException{
		return getPinYin(str,SHENG_MU,charsetName);
	}
	/**
	 * 根据类型将汉字转换成拼音
	 * @param str 汉字
	 * @param type 声母=1,全拼=2,词组=3,语句=4
	 * @return 拼音
	 */
	public static String getPinYin(String str,int type){
		if(str==null||str.length()==0)
			return null;
		StringBuffer re=new StringBuffer();
		switch (type) {
		case QUAN_PIN:
			for(int i=0;i<str.length();i++)
				re.append(zhuYin(str.substring(i,i+1)));
			break;
		case CI_ZU:
			for(int i=0;i<str.length();i++){
				if(i>0){                
					re.append(" ");//词组之间是否要加空格，我不能确定，有明白的请联系我 xuender@gmail.com
					//第二个字如果是[aeiou]的话要加`号
					String temp=zhuYin(str.substring(i,i+1)).substring(0,1);
					if(temp.equals("a")||temp.equals("e")||temp.equals("i")||temp.equals("o")||temp.equals("u"))
						re.append("`");
				}
				re.append(zhuYin(str.substring(i,i+1)));
			}
			break;
		case YU_JU:
			re.append(getPinYin(str,CI_ZU));
			//首字母大写
			re.insert(0,re.substring(0,1).toUpperCase());
			re.delete(1,2);
			break;
		default://声母
			//取拼音的第一个字母
			for(int i=0;i<str.length();i++)
				re.append(zhuYin(str.substring(i,i+1)).substring(0,1));
		break;
		}
		return re.toString();
	}
	/**
	 * 测试输出
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(PinYin.getPinYin("俺穿棉袄去西安!"));
		System.out.println(PinYin.getPinYin("俺穿棉袄去西安!",PinYin.CI_ZU));
		System.out.println(PinYin.getPinYin("俺穿棉袄去西安!",PinYin.QUAN_PIN));
		System.out.println(PinYin.getPinYin("俺穿棉袄去西安!",PinYin.SHENG_MU));
		System.out.println(PinYin.getPinYin("俺穿棉袄去西安!",PinYin.YU_JU));
	}
}
