/**
 * 
 */
package com.urlshow.pinyin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author ender
 * 
 */
public class CreatePinYinArray {
	private Connection conn;
	private int count = 0;
	private int commit_count = 0;
	private PreparedStatement ps;
	private Set<String> set = new HashSet<String>();

	// private Map<String, Integer[]> map = new HashMap<String, Integer[]>();

	public CreatePinYinArray() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		conn = DriverManager.getConnection("jdbc:h2:mem:testdb");
	}

	public void createTable() throws SQLException {
		Statement s = conn.createStatement();
		s.execute("create table zi (unicode int,gbk int,pinyin VARCHAR(20),zimu VARCHAR(20),yindiao int,constraint pk_pinyin primary key(unicode,pinyin));");
		s.close();
		ps = conn
				.prepareStatement("insert into zi (unicode,pinyin,zimu,yindiao) values(?,?,?,?);");
	}

	public void insert(char c, String py) throws SQLException {
		if (set.contains(py + c))
			return;
		set.add(py + c);
		ps.setInt(1, (int) c);
		ps.setString(2, py);
		ps.setString(3, py.substring(0, py.length() - 1));
		ps.setInt(4, Integer.parseInt(py.substring(py.length() - 1)));
		ps.addBatch();
		count++;
		if (count > 100) {
			commit();
		}
	}

	public void commit() throws SQLException {
		count = 0;
		int[] is = ps.executeBatch();
		for (int i : is)
			commit_count += i;
		// System.out.println("insert:" + commit_count);
		ps.clearBatch();
	}

	public void close() throws SQLException {
		ps.close();
		conn.close();
	}

	private void print() throws SQLException {
		Statement ps = conn.createStatement();
		ResultSet rs = ps
				.executeQuery("select * from zi order by pinyin,unicode");
		String py = null;
		StringBuilder sb = new StringBuilder("static final char[][] data = {");
		StringBuffer zm = new StringBuffer("static final String[] pinyin=\"");
		while (rs.next()) {
			int unicode = rs.getInt("unicode");
			String p = rs.getString("pinyin");
			if (!p.equals(py)) {
				if (py == null) {
					sb.append("\"");
				} else {
					sb.append("\".toCharArray(),");
					System.out.println(sb.toString());
					sb = new StringBuilder("\"");
					zm.append(py).append(',');
				}
				py = p;

			}
			sb.append((char) unicode);
		}
		sb.append("\".toCharArray()};");
		System.out.println(sb.toString());
		System.out.println(zm.append("\".split(\",\");").toString());
		rs.close();
	}

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, BadHanyuPinyinOutputFormatCombination {
		CreatePinYinArray pa = new CreatePinYinArray();
		pa.createTable();
		HanyuPinyinOutputFormat hf=new HanyuPinyinOutputFormat();
		hf.setVCharType(HanyuPinyinVCharType.WITH_V);
		for (int[] u : Utils.unicode) {
			for (int c = u[0]; c <= u[1]; c++) {
				try {
					for (String s : PinyinHelper
							.toHanyuPinyinStringArray((char) c,hf)){
						pa.insert((char) c, s);
					}
				} catch (NullPointerException e) {
				}
			}
		}
		pa.commit();
		pa.print();
		pa.close();

	}

}
