/**
 * 
 */
package com.urlshow.pinyin;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ender
 * 
 */
public class PinYin {
	private PinYin() {
	}

	/**
	 * 根据转换类型、字符集将汉字转换成拼音
	 * 
	 * @param str
	 *            汉字
	 * @param type
	 *            转换类型
	 * @param charsetName
	 *            字符集
	 * @return 拼音
	 * @throws UnsupportedEncodingException
	 */
	public static String getPinYin(String str, ToneType type, String charsetName)
			throws UnsupportedEncodingException {
		return getPinYin(str, type, charsetName, false);
	}

	/**
	 * 根据转换类型、字符集将汉字转换成拼音
	 * 
	 * @param str
	 *            汉字
	 * @param type
	 *            转换类型
	 * @param charsetName
	 *            字符集
	 * @param yb
	 *            是否使用音标显示
	 * @return 拼音
	 * @throws UnsupportedEncodingException
	 */
	public static String getPinYin(String str, ToneType type,
			String charsetName, boolean yb) throws UnsupportedEncodingException {
		if (charsetName == null || charsetName.length() == 0
				|| charsetName.equalsIgnoreCase("utf-8"))
			return getPinYin(str, type);
		return getPinYin(new String(str.getBytes(charsetName), "UTF-8"), type);
	}

	/**
	 * 将汉字转换成声母
	 * 
	 * @param str
	 *            汉字
	 * @return 声母
	 */
	public static String getPinYin(String str) {
		return getPinYin(str, ToneType.SHENG_MU);
	}

	/**
	 * 将汉字转换成声母
	 * 
	 * @param str
	 *            汉字
	 * @param yb
	 *            是否使用音标显示
	 * @return 声母
	 */
	public static String getPinYin(String str, boolean yb) {
		return getPinYin(str, ToneType.SHENG_MU, yb);
	}

	/**
	 * 根据字符集将汉字转换成声母
	 * 
	 * @param str
	 *            汉字
	 * @param charsetName
	 *            字符集
	 * @return 声母
	 * @throws UnsupportedEncodingException
	 */
	public static String getPinYin(String str, String charsetName)
			throws UnsupportedEncodingException {
		return getPinYin(str, ToneType.SHENG_MU, charsetName);
	}

	/**
	 * 根据字符集将汉字转换成声母
	 * 
	 * @param str
	 *            汉字
	 * @param charsetName
	 *            字符集
	 * @param yb
	 *            是否使用音标
	 * @return 声母
	 * @throws UnsupportedEncodingException
	 */
	public static String getPinYin(String str, String charsetName, boolean yb)
			throws UnsupportedEncodingException {
		return getPinYin(str, ToneType.SHENG_MU, charsetName, yb);
	}

	/**
	 * 为一个汉字的注音
	 * 
	 * @param c
	 *            汉字
	 * @return 拼音数组
	 */
	public static String[] zhuYin(char c) {
		List<String> ret = new ArrayList<String>();
		for (int i = 0; i < PinYinData.data.length; i++) {
			for (char y : PinYinData.data[i]) {
				if (c < y)
					break;
				if (c == y) {
					ret.add(PinYinData.pinyin[i]);
					break;
				}
			}
		}
		return ret.toArray(new String[0]);
	}

	/**
	 * 根据转换类型、字符集将汉字转换成声母
	 * 
	 * @param str
	 *            汉字
	 * @param type
	 *            转换类型
	 * @return 生母
	 */
	public static String getPinYin(String str, ToneType type) {
		return getPinYin(str, type, false);
	}

	/**
	 * 根据转换类型、字符集将汉字转换成音标声母
	 * 
	 * @param str
	 *            汉字
	 * @param type
	 *            转换类型
	 * @param yb
	 *            音标
	 * @return 生母
	 */

	public static String getPinYin(String str, ToneType type, boolean yb) {
		if (str == null || str.length() == 0)
			return "";

		// 修改成半角状态
		final String old = Utils.SBCchange(str);

		StringBuffer re = new StringBuffer();
		switch (type) {
		case QUAN_PIN:
			for (char c : old.toCharArray()) {
				if (Utils.hasPinYin(c)) {
					if (yb)
						re.append(Utils.abc2py(zhuYin(c)[0]));
					else
						re.append(zhuYin(c)[0]);
				} else
					re.append(c);
			}
			break;
		case CI_ZU:
			boolean b = false;
			for (char c : old.toCharArray()) {
				if (Utils.hasPinYin(c)) {
					String temp = zhuYin(c)[0];
					if (b) {
						re.append(" ");// 词组之间是否要加空格，我不能确定，有明白的请联系我
						// xuender@gmail.com
						// 第二个字如果是[aeiou]的话要加`号
						if (temp.substring(0, 1).equalsIgnoreCase("a")
								|| temp.substring(0, 1).equalsIgnoreCase("e")
								|| temp.substring(0, 1).equalsIgnoreCase("i")
								|| temp.substring(0, 1).equalsIgnoreCase("o")
								|| temp.substring(0, 1).equalsIgnoreCase("u"))
							re.append('\'');
					}
					b = true;
					if (yb)
						re.append(Utils.abc2py(temp));
					else
						re.append(temp);
				} else
					re.append(c);
			}
			break;
		case YU_JU:
			re.append(getPinYin(str, ToneType.CI_ZU, yb));
			// 首字母大写
			re.insert(0, re.substring(0, 1).toUpperCase());
			re.delete(1, 2);
			break;
		default:// 声母
			// 取拼音的第一个字母
			for (char c : old.toCharArray()) {
				if (Utils.hasPinYin(c)) {
					if (yb)
						re.append(Utils.abc2py(zhuYin(c)[0]).substring(0, 1));
					else
						re.append(zhuYin(c)[0].substring(0, 1));
				} else
					re.append(c);
			}
			break;
		}
		return re.toString();
	}
}
