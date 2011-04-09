/**
 * 
 */
package com.urlshow.pinyin;

/**
 * 打印所有汉字
 * @author ender
 *
 */
public class PrintAllChinese {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int[] u : Utils.unicode) {
			System.out.println(u[0]+"-"+u[1]+"="+((int)u[1]-(int)u[0]+1));
			for(int c=u[0];c<=u[1];c++){
				System.out.print((char)c);
			}
			System.out.println(";");
		}
	}

}
