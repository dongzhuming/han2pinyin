/**
 * 
 */
package com.urlshow.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * @author ender
 * 
 */
public class Pinyin4jMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (char c : "你好长".toCharArray())
			for (String s : PinyinHelper.toHanyuPinyinStringArray(c))
				System.out.println(s);
	}

}
