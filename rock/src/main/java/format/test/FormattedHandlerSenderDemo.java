/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package format.test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import cn.ac.iie.di.datadock.rdata.exchange.client.core.REFieldType;
import cn.ac.iie.di.datadock.rdata.exchange.client.exception.REConnectionException;
import cn.ac.iie.di.datadock.rdata.exchange.client.exception.RESessionException;
import cn.ac.iie.di.datadock.rdata.exchange.client.v1.connection.REConnection;
import cn.ac.iie.di.datadock.rdata.exchange.client.v1.session.RESendSession;
import cn.ac.iie.di.datadock.rdata.exchange.client.v1.session.RESendSessionBuilder;

/**
 *
 * @author zwlin
 */
public class FormattedHandlerSenderDemo {

	public static void main(String[] args) throws REConnectionException, RESessionException {
		REConnection conn = new REConnection("192.168.79.132:9876");
		RESendSessionBuilder builder = (RESendSessionBuilder) conn.getSendSessionBuilder("datadock_client_test");
		allColSend(builder);
		conn.close();
	}

	public static void singleSend(RESendSessionBuilder builder) throws RESessionException, REConnectionException {

		builder.addColumn("testboolean", REFieldType.Boolean, true);
		builder.addColumn("testint", REFieldType.Int, true);
		builder.addColumn("testlong", REFieldType.Long, true);
		builder.addColumn("testfloat", REFieldType.Float, true);
		builder.addColumn("testdouble", REFieldType.Double, true);
		builder.addColumn("testbinary", REFieldType.Binary, true);
		builder.addColumn("teststring", REFieldType.String, true);

		RESendSession sender = (RESendSession) builder.build();
		sender.start();

		sender.setBoolean("testboolean", true);
		sender.setInt("testint", 1);
		sender.setLong("testlong", 988989889);
		sender.setFloat("testfloat", 3.1f);
		sender.setDouble("testdouble", 3.1011010010110101001);
		byte[] testbytes = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07};
		sender.setBinary("testbinary", testbytes);
		sender.setString("teststring", "hello, this is string testing");

		sender.add();
		sender.flush();
		sender.shutdown();
	}

	public static void structSend(RESendSessionBuilder builder) throws RESessionException, REConnectionException {
		builder.addColumn("teststruct", REFieldType.Struct, true);
		HashMap<String, REFieldType> structDetail = new HashMap<String, REFieldType>();
		structDetail.put("testint", REFieldType.Int);
		structDetail.put("teststring", REFieldType.String);
		REFieldType.Struct.setDetail("teststruct", structDetail);

		RESendSession sender = (RESendSession) builder.build();
		sender.start();

		HashMap<String, Object> value = new HashMap<String, Object>();
		value.put("testint", 10010);
		value.put("teststring", "hello, this is struct test");
		sender.setStruct("teststruct", value);

		sender.add();
		sender.flush();
		sender.shutdown();
	}

	public static void arraySend(RESendSessionBuilder builder) throws RESessionException, REConnectionException {
		builder.addColumn("testbooleans", REFieldType.Booleans, true);
		builder.addColumn("testints", REFieldType.Ints, true);
		builder.addColumn("testlongs", REFieldType.Longs, true);
		builder.addColumn("testfloats", REFieldType.Floats, true);
		builder.addColumn("testdoubles", REFieldType.Doubles, true);
		builder.addColumn("testbinaries", REFieldType.Binaries, true);
		builder.addColumn("teststrings", REFieldType.Strings, true);

		RESendSession sender = (RESendSession) builder.build();
		sender.start();

		boolean[] booleans = {true, false, true};
		sender.setBooleans("testbooleans", booleans);
		int[] ints = {1, 0, 1};
		sender.setInts("testints", ints);
		long[] longs = {989989889, 898898998};
		sender.setLongs("testlongs", longs);
		float[] floats = {0.1f, 0.2f, 1.3f};
		sender.setFloats("testfloats", floats);
		double[] doubles = {3.1011010010110101001, 3.1011010010110101002, 3.1011010010110101003};
		sender.setDoubles("testdoubles", doubles);
		byte[] bytes = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07};
		byte[] bytes1 = Arrays.copyOf(bytes, bytes.length);
		for (int i = 0; i < bytes1.length; ++i) {
			bytes1[i] += 1;
		}
		byte[][] binaries = new byte[2][];
		binaries[0] = bytes;
		binaries[1] = bytes1;
		sender.setBinaries("testbinaries", binaries);
		String[] strings = {"hello, this is string testing", "hello, this is string testing1", "hello, this is string testing2"};
		sender.setStrings("teststrings", strings);

		sender.add();
		sender.flush();
		sender.shutdown();
	}

	public static void structsSend(RESendSessionBuilder builder) throws RESessionException, REConnectionException {

		builder.addColumn("teststructs", REFieldType.Structs, true);
		HashMap<String, REFieldType> structDetail = new HashMap<String, REFieldType>();
		structDetail.put("testint", REFieldType.Int);
		structDetail.put("teststring", REFieldType.String);
		REFieldType.Structs.setDetail("teststructs", structDetail);

		RESendSession sender = (RESendSession) builder.build();
		sender.start();

		HashMap<String, Object> value = new HashMap<String, Object>();
		value.put("testint", 10010);
		value.put("teststring", "hello, this is struct test");
		HashMap<String, Object> value2 = new HashMap<String, Object>();
		value2.put("testint", 10011);
		value2.put("teststring", "hello, this is struct test2");
		Map[] maps = new Map[2];
		maps[0] = value;
		maps[1] = value2;
		sender.setStructs("teststructs", maps);

		sender.add();
		sender.flush();
		sender.shutdown();
	}

	public static void allColSend(RESendSessionBuilder builder) throws RESessionException, REConnectionException {
		builder.addColumn("testboolean", REFieldType.Boolean, true);
		builder.addColumn("testint", REFieldType.Int, true);
		builder.addColumn("testlong", REFieldType.Long, true);
		builder.addColumn("testfloat", REFieldType.Float, true);
		builder.addColumn("testdouble", REFieldType.Double, true);
		builder.addColumn("testbinary", REFieldType.Binary, true);
		builder.addColumn("teststring", REFieldType.String, true);
		builder.addColumn("teststruct", REFieldType.Struct, true);
		HashMap<String, REFieldType> structDetail = new HashMap<String, REFieldType>();
		structDetail.put("testint", REFieldType.Int);
		structDetail.put("teststring", REFieldType.String);
		REFieldType.Struct.setDetail("teststruct", structDetail);
		builder.addColumn("testbooleans", REFieldType.Booleans, true);
		builder.addColumn("testints", REFieldType.Ints, true);
		builder.addColumn("testlongs", REFieldType.Longs, true);
		builder.addColumn("testfloats", REFieldType.Floats, true);
		builder.addColumn("testdoubles", REFieldType.Doubles, true);
		builder.addColumn("testbinaries", REFieldType.Binaries, true);
		builder.addColumn("teststrings", REFieldType.Strings, true);
		builder.addColumn("teststructs", REFieldType.Structs, true);
		HashMap<String, REFieldType> structsDetail = new HashMap<String, REFieldType>();
		structsDetail.put("testint", REFieldType.Int);
		structsDetail.put("teststring", REFieldType.String);
		REFieldType.Structs.setDetail("teststructs", structsDetail);

		RESendSession sender = (RESendSession) builder.build();
		sender.start();
		for (int j = 0; j < 5; ++j) {
			//single data
			sender.setBoolean("testboolean", true);
			sender.setInt("testint", 1);
			sender.setLong("testlong", 988989889);
			sender.setFloat("testfloat", 3.1f);
			sender.setDouble("testdouble", 3.1011010010110101001);
			byte[] testbytes = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07};
			sender.setBinary("testbinary", testbytes);
			sender.setString("teststring", "hello, this is string testing");
			HashMap<String, Object> value = new HashMap<String, Object>();
			value.put("testint", 10010);
			value.put("teststring", "hello, this is struct test");
			sender.setStruct("teststruct", value);

			//array data
			boolean[] booleans = {true, false, true};
			sender.setBooleans("testbooleans", booleans);
			int[] ints = {1, 0, 1};
			sender.setInts("testints", ints);
			long[] longs = {989989889, 898898998};
			sender.setLongs("testlongs", longs);
			float[] floats = {0.1f, 0.2f, 1.3f};
			sender.setFloats("testfloats", floats);
			double[] doubles = {3.1011010010110101001, 3.1011010010110101002, 3.1011010010110101003};
			sender.setDoubles("testdoubles", doubles);
			byte[] bytes = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07};
			byte[] bytes1 = Arrays.copyOf(bytes, bytes.length);
			for (int i = 0; i < bytes1.length; ++i) {
				bytes1[i] += 1;
			}
			byte[][] binaries = new byte[2][];
			binaries[0] = bytes;
			binaries[1] = bytes1;
			sender.setBinaries("testbinaries", binaries);
			String[] strings = {"hello, this is string testing", "hello, this is string testing1", "hello, this is string testing2"};
			sender.setStrings("teststrings", strings);
			HashMap<String, Object> value1 = new HashMap<String, Object>();
			value1.put("testint", 10010);
			value1.put("teststring", "hello, this is struct test");
			HashMap<String, Object> value2 = new HashMap<String, Object>();
			value2.put("testint", 10011);
			value2.put("teststring", "hello, this is struct test2");
			Map[] maps = new Map[2];
			maps[0] = value1;
			maps[1] = value2;
			sender.setStructs("teststructs", maps);

			sender.add();
		}
		sender.flush();
		sender.shutdown();
	}
}
