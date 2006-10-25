/**
 * 2006-10-25
 */
package com.urlshow.pinyin;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

/**
 * @author 徐晨阳
 * @version 1.0
 */
class Config {
	private ResourceBundle bundle;
	//韵母分隔符号
	private String SPACE="'";
	//输出是否保留全角字符  DBC=半角 SBC=全角
	private String CASE="DBC";

	/**
	 * 构造方法
	 *
	 */
	Config(){
		try {
			bundle=ResourceBundle.getBundle("pinyin");
			//读取分隔符号
			String value=getString("SPACE");
			if(value!=null)
				SPACE=value;
			//读取半角 全角输出
			value=getString("CASE");
			if(value!=null)
				CASE=value;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 修改输出，CASE＝SBC全角不变，CASE=DBC所有全角修改成半角
	 * @param str 拼音
	 * @return 输出
	 */
	String change(String str){
		//输出不变
		if(CASE==null||CASE.trim().toUpperCase().equals("SBC"))
			return str;
		return SBCchange(str);
	}
	/**
	 * 半角转换
	 * @param str 文字
	 * @return 输出
	 */
	private static final String SBCchange(String str){
		String outStr="";
		String Tstr="";
		byte[] b=null;

		for(int i=0;i<str.length();i++){     
			try{
				Tstr=str.substring(i,i+1);
				b=Tstr.getBytes("unicode");
			}catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}     

			if (b[3]==-1){
				b[2]=(byte)(b[2]+32);
				b[3]=0;      

				try{       
					outStr=outStr+new String(b,"unicode");
				}	catch(UnsupportedEncodingException e){
					e.printStackTrace();
				}      
			}else outStr=outStr+Tstr;
		}

		return outStr; 
	}

	/**
	 * 读取配置信息
	 * @param key 键
	 * @return 值
	 */
	private String getString(String key){
		if(bundle==null)
			return null;
		try {
			return bundle.getString(key);
			//.properties文件中如果有中文需要以下代码
			//return new String(bundle.getString(key).getBytes("ISO-8859-1"),"UTF-8");
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 韵母开头的拼音前加的分隔符号
	 * @return 分隔符号
	 */
	String getSPACE(){
		return SPACE;
	}
}
