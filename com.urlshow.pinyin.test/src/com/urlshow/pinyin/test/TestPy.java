/**
 * 2006-10-24
 */
package com.urlshow.pinyin.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.urlshow.pinyin.PinYin;

/**
 * @author 徐晨阳
 * @version 1.0
 */
public class TestPy {
	static void run2() throws UnsupportedEncodingException{
		String str="，＃中华人民共和国西安！ｆｓ";
		System.out.println(PinYin.getPinYin(str));
	}
	static void run1(){
		System.out.println(PinYin.getPinYin("俺穿棉袄去西安!"));
		System.out.println(PinYin.getPinYin("俺穿棉袄去西安!",PinYin.CI_ZU));
		System.out.println(PinYin.getPinYin("俺穿棉袄去西安!",PinYin.QUAN_PIN));
		System.out.println(PinYin.getPinYin("俺穿棉袄去西安!",PinYin.SHENG_MU));
		System.out.println(PinYin.getPinYin("俺穿棉袄去西安!",PinYin.YU_JU));
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		run2();
	}

}
