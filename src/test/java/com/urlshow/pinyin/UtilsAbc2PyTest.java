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
public class UtilsAbc2PyTest {
	private String abc;
	private String py;

	public UtilsAbc2PyTest(String abc, String py) {
		this.abc = abc;
		this.py = py;
	}

	@Parameters
	public static Collection<?> prepareData() {
		return Arrays.asList(new Object[][] { { "han4", "hàn" },//
				{ "ao3", "ǎo" },//
				{ "Ao3", "Ǎo" },//
				{ "tui4", "tuì" },//
		});
	}

	/**
	 * Test method for {@link com.urlshow.pinyin.Utils#isChinese(char)}.
	 */
	@Test
	public void testIsChinese() {
		assertEquals("abc:" + abc + " py:" + py, Utils.abc2py(abc), py);
	}
}
