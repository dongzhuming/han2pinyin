/**
 * 
 */
package com.urlshow.pinyin;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * 测试工具类
 * 
 * @author ender
 * 
 */
@RunWith(Parameterized.class)
public class UtilsTest {
	private char c;
	private boolean chinese;

	public UtilsTest(char c, boolean chinese) {
		this.c = c;
		this.chinese = chinese;
	}

	@Parameters
	public static Collection<?> prepareData() {
		return Arrays.asList(new Object[][] { { '鶳', true },//
				{ '徐', true },//
				{ '晨', true },//
				{ '阳', true },//
				{ 'E', false },//
				{ 'n', false },//
				{ 'd', false },//
				{ 'e', false },//
				{ 'r', false },//
				{ ' ', false },//
				{ '쑤', false },//
				{ '도', false },//
				{ '련', false },//
				{ '님', false },//
		});
	}

	/**
	 * Test method for {@link com.urlshow.pinyin.Utils#isChinese(char)}.
	 */
	@Test
	public void testIsChinese() {
		assertEquals("字母:" + c, Utils.isChinese(c), chinese);
		assertEquals("字母:" + c, Utils.hasPinYin(c), chinese);
	}
}
