/**
 * 
 */
package com.urlshow.pinyin;

import static org.junit.Assert.*;

import org.junit.Ignore;
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
	 */
	@Test
	@Ignore
	public void testGetPinYinStringString() {
		fail("Not yet implemented");
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
	 */
	@Test
	@Ignore
	public void testGetPinYinStringToneTypeString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.urlshow.pinyin.PinYin#getPinYin(java.lang.String, com.urlshow.pinyin.ToneType, java.lang.String, boolean)}
	 * .
	 */
	@Test
	@Ignore
	public void testGetPinYinStringToneTypeStringBoolean() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.urlshow.pinyin.PinYin#getPinYin(java.lang.String, boolean)}.
	 */
	@Test
	@Ignore
	public void testGetPinYinStringBoolean() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.urlshow.pinyin.PinYin#getPinYin(java.lang.String, java.lang.String, boolean)}
	 * .
	 */
	@Test
	public void testGetPinYinStringStringBoolean() {
		assertEquals("傲慢", PinYin.getPinYin("傲慢", true), "àm");
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
