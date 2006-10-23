/**
 * 2006-10-23
 */
package com.urlshow.pinyin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 徐晨阳
 * @version 1.0
 */
public abstract class HanZi {
	private static String hanZi;

	/**
	 *  获取汉字编码表
	 * @return hanZi 汉字编码表
	 */
	static final String getHanZi() {
		if(hanZi==null){
			BufferedReader br=new BufferedReader(new InputStreamReader(HanZi.class.getResourceAsStream("hanzi.txt")));
			try {
				hanZi=br.readLine();
				br.close();
			} catch (IOException e) {
				hanZi="";
			}
		}
		return hanZi;
	}
}
