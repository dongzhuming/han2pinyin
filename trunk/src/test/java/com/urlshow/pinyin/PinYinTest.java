/**
 * 
 */
package com.urlshow.pinyin;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

/**
 * @author ender
 * 
 */
public class PinYinTest {

	/**
	 * Test method for
	 * {@link com.urlshow.pinyin.PinYin#getPinYin(java.lang.String)}.
	 */
	@Test
	public void testGetPinYinString() {
		assertEquals("拼音转换", PinYin.getPinYin("你好"), "nh");
		assertEquals("拼音转换", PinYin.getPinYin("中华人民共和国"), "zhrmghg");
		assertEquals("拼音转换", PinYin.getPinYin("你好Ender"), "nhEnder");
	}

	/**
	 * Test method for
	 * {@link com.urlshow.pinyin.PinYin#getPinYin(java.lang.String, java.lang.String)}
	 * .
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testGetPinYinStringString() throws UnsupportedEncodingException {
		assertEquals("n", PinYin.getPinYin("你", "UTF-8"));
	}

	/**
	 * Test method for {@link com.urlshow.pinyin.PinYin#zhuYin(char)}.
	 */
	@Test
	public void testZhuYin() {
		assertArrayEquals("中", PinYin.zhuYin('中'), new String[] { "zhong1",
				"zhong4" });
	}

	/**
	 * Test method for
	 * {@link com.urlshow.pinyin.PinYin#getPinYin(java.lang.String, com.urlshow.pinyin.ToneType)}
	 * .
	 */
	@Test
	public void testGetPinYinStringToneType() {
		assertEquals("拼音转换", PinYin.getPinYin("你好", ToneType.CI_ZU), "ni3 hao3");
		assertEquals("拼音转换", PinYin.getPinYin("中华人民共和国", ToneType.CI_ZU),
				"zhong1 hua1 ren2 min2 gong1 he2 guo2");
	}

	/**
	 * Test method for
	 * {@link com.urlshow.pinyin.PinYin#getPinYin(java.lang.String, com.urlshow.pinyin.ToneType, java.lang.String)}
	 * .
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testGetPinYinStringToneTypeString() throws UnsupportedEncodingException {
		assertEquals("ni3", PinYin.getPinYin("你", ToneType.QUAN_PIN, "UTF-8"));
	}

	/**
	 * Test method for
	 * {@link com.urlshow.pinyin.PinYin#getPinYin(java.lang.String, com.urlshow.pinyin.ToneType, java.lang.String, boolean)}
	 * .
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testGetPinYinStringToneTypeStringBoolean() throws UnsupportedEncodingException {
		assertEquals("nǐ", PinYin.getPinYin("你", ToneType.QUAN_PIN, null, true));
		assertEquals("ni3", PinYin.getPinYin("你", ToneType.QUAN_PIN, null, false));
		assertEquals("nǐ", PinYin.getPinYin("你", ToneType.QUAN_PIN, "", true));
		assertEquals("ni3", PinYin.getPinYin("你", ToneType.QUAN_PIN, "", false));
		assertEquals("nǐ", PinYin.getPinYin("你", ToneType.QUAN_PIN, "UTF-8", true));
		assertEquals("ni3", PinYin.getPinYin("你", ToneType.QUAN_PIN, "UTF-8", false));
	}

	/**
	 * Test method for
	 * {@link com.urlshow.pinyin.PinYin#getPinYin(java.lang.String, boolean)}.
	 */
	@Test
	public void testGetPinYinStringBoolean() {
		assertEquals("n", PinYin.getPinYin("你", true));
		assertEquals("n", PinYin.getPinYin("你", false));
	}

	/**
	 * Test method for
	 * {@link com.urlshow.pinyin.PinYin#getPinYin(java.lang.String, java.lang.String, boolean)}
	 * .
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testGetPinYinStringStringBoolean() throws UnsupportedEncodingException {
		assertEquals("", PinYin.getPinYin(null, "UTF-8", true));
		assertEquals("", PinYin.getPinYin("", "UTF-8", true));
		assertEquals("傲慢", PinYin.getPinYin("傲慢", "UTF-8", true), "àm");
		assertEquals("傲慢", PinYin.getPinYin("傲慢", "UTF-8", false), "am");
	}

	/**
	 * Test method for
	 * {@link com.urlshow.pinyin.PinYin#getPinYin(java.lang.String, com.urlshow.pinyin.ToneType, boolean)}
	 * .
	 */
	@Test
	public void testGetPinYinStringToneTypeBoolean() {
		assertEquals("拼音转换", PinYin.getPinYin("你好", ToneType.CI_ZU, true),
				"nǐ hǎo");
		assertEquals("拼音转换", PinYin.getPinYin("中华人民共和国", ToneType.CI_ZU, true),
				"zhōng huā rén mín gōng hé guó");
		assertEquals("傲慢与偏见不错。",
				PinYin.getPinYin("傲慢与偏见不错。", ToneType.YU_JU, true),
				"Ào màn yú piān jiàn bú cuò。");
	}

}
