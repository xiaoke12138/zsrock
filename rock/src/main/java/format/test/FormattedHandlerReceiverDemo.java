/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package format.test;

import cn.ac.iie.di.datadock.rdata.exchange.client.core.session.REAbstractSession;
import cn.ac.iie.di.datadock.rdata.exchange.client.core.session.receive.REAbstractReceiveMessageHandler;
import cn.ac.iie.di.datadock.rdata.exchange.client.exception.REConnectionException;
import cn.ac.iie.di.datadock.rdata.exchange.client.exception.RESessionException;
import cn.ac.iie.di.datadock.rdata.exchange.client.v1.ConsumePosition;
import cn.ac.iie.di.datadock.rdata.exchange.client.v1.FailureMessage;
import cn.ac.iie.di.datadock.rdata.exchange.client.v1.REMessageExt;
import cn.ac.iie.di.datadock.rdata.exchange.client.v1.connection.REConnection;
import cn.ac.iie.di.datadock.rdata.exchange.client.v1.session.FormattedHandler;
import cn.ac.iie.di.datadock.rdata.exchange.client.v1.session.REReceiveSession;
import cn.ac.iie.di.datadock.rdata.exchange.client.v1.session.REReceiveSessionBuilder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author zwlin
 */
public class FormattedHandlerReceiverDemo {
	private static Logger logger = LoggerFactory.getLogger(FormattedHandlerReceiverDemo.class);

	static final boolean TEST_VALUE = true;

	public static void main(String[] args) throws REConnectionException {
		//REConnection conn = new REConnection("192.168.79.132:9876");
		REConnection conn = new REConnection("10.136.64.28:9877;10.136.64.29:9877;10.136.64.30:9877;10.136.64.31:9877;10.136.64.32:9877");
		REReceiveSessionBuilder builder = (REReceiveSessionBuilder) conn
				.getReceiveSessionBuilder("yw_new_new_test_0_mq");
		builder.setGroupName("formattedHandlerTestGroup");
		builder.setConsumPosition(ConsumePosition.CONSUME_FROM_FIRST_OFFSET);
		builder.setConsumeThreadNum(1);
		builder.setFailureHandler(new REAbstractReceiveMessageHandler<FailureMessage>() {
			@Override
			public boolean handle(FailureMessage message) {
				System.err.println("something error happened!");
				message.getEx().printStackTrace();
				return true;
			}
		});

		if (TEST_VALUE) {
			builder.setHandler(new ValueTestHandler());
		} else {
			builder.setHandler(new GetFunctionTestHandler());
		}
		REAbstractSession build = builder.build();
		REReceiveSession receiver = null;
		if (build instanceof REReceiveSession) {
			receiver = (REReceiveSession) builder.build();
		}
		receiver.start();
	}

	/**
	 * 用 record.getAllColumnDetails得到所有的列并且处理
	 */
	public static class ValueTestHandler extends FormattedHandler {

		@Override
		public boolean handle(REMessageExt messageExt) {
			System.out.println("=== === === === ===");
			logger.info("=====进入代码层=====");
			Iterator<REMessageExt.Record> itr = messageExt.getRecordIterator();
			while (itr.hasNext()) {
				System.out.println(" --- --- --- ");
				Map<String, Object> insertMap = new HashMap<>();
				REMessageExt.Record rec = itr.next();
				for (REMessageExt.ColumnDetail detail : rec.getAllColumnDetails()) {
					switch (detail.getType()) {
					// single data
					case Boolean:
						insertMap.put(detail.getKey(), detail.getValue());
						break;
					case Int:
						insertMap.put(detail.getKey(), detail.getValue());
						break;
					case Long:
						insertMap.put(detail.getKey(), detail.getValue());
						break;
					case Float:
						insertMap.put(detail.getKey(), detail.getValue());
						break;
					case Double:
						insertMap.put(detail.getKey(), detail.getValue());
						break;
					case String:
						insertMap.put(detail.getKey(), detail.getValue());
						break;
					case Struct: {
						System.out.println("key: " + detail.getKey() + "    \ttype: " + detail.getType().name()
								+ "\tvalue: " + detail.getValue());
						break;
					}
					case Binary: {
						System.out.print(
								"key: " + detail.getKey() + "    \ttype: " + detail.getType().name() + "\tvalue:");
						for (byte b : (byte[]) detail.getValue()) {
							System.out.print(" " + b);
						}
						System.out.println();
						break;
					}
					// array data
					case Booleans:
					case Ints:
					case Longs:
					case Floats:
					case Doubles:
					case Strings:
					case Structs: {
						System.out.print(
								"key: " + detail.getKey() + "    \ttype: " + detail.getType().name() + "\tvalue:");
						Object[] value = (Object[]) detail.getValue();
						for (Object o : value) {
							System.out.print(o + "; ");
						}
						System.out.println();
						break;
					}
					case Binaries: {
						System.out.print(
								"key: " + detail.getKey() + "    \ttype: " + detail.getType().name() + "\tvalue:");
						for (Object bytes : (Object[]) detail.getValue()) {
							for (byte b : (byte[]) bytes) {
								System.out.print(" " + b);
							}
							System.out.print(";");
						}
						System.out.println();
						break;
					}
					}
				}
				System.out.println("insertMap"+insertMap);
				logger.info("insertMap"+insertMap);
			}
			return true;
		}

	}

	/**
	 * 利用getXXX方法直接得到对应列的格式化数据
	 */
	public static class GetFunctionTestHandler extends FormattedHandler {

		@Override
		public boolean handle(REMessageExt messageExt) {
			System.out.println("=== === === === ===");
			Iterator<REMessageExt.Record> itr = messageExt.getRecordIterator();
			while (itr.hasNext()) {
				try {
					REMessageExt.Record rec = itr.next();
					System.out.println(" --- --- --- ");
					// single
					if (rec.containsColumn("testboolean")) {
						System.out.println("key: testboolean\tvalue: " + rec.getBoolean("testboolean"));
					}
					if (rec.containsColumn("testint")) {
						System.out.println("key: testint\t\tvalue: " + rec.getInt("testint"));
					}
					if (rec.containsColumn("testlong")) {
						System.out.println("key: testlong\t\tvalue: " + rec.getLong("testlong"));
					}

					if (rec.containsColumn("testfloat")) {
						System.out.println("key: testfloat\t\tvalue: " + rec.getFloat("testfloat"));
					}
					if (rec.containsColumn("testdouble")) {
						System.out.println("key: testdouble\t\tvalue: " + rec.getDouble("testdouble"));
					}
					if (rec.containsColumn("teststring")) {
						System.out.println("key: teststring\t\tvalue: " + rec.getString("teststring"));
					}
					if (rec.containsColumn("testbinary")) {
						System.out.print("key: testbinary\t\tvalue:");
						for (byte b : rec.getBinary("testbinary")) {
							System.out.print(" " + b);
						}
						System.out.println();
					}
					if (rec.containsColumn("teststruct")) {
						System.out.println("key: teststruct\t\tvalue: " + rec.getStruct("teststruct"));
					}

					// array
					if (rec.containsColumn("testbooleans")) {
						System.out.println("key: testbooleans\tvalue: " + rec.getBooleans("testbooleans"));
					}
					if (rec.containsColumn("testints")) {
						System.out.println("key: testints\t\tvalue: " + rec.getInts("testints"));
					}
					if (rec.containsColumn("testlongs")) {
						System.out.println("key: testlongs\t\tvalue: " + rec.getLongs("testlongs"));
					}

					if (rec.containsColumn("testfloats")) {
						System.out.println("key: testfloats\t\tvalue: " + rec.getFloats("testfloats"));
					}
					if (rec.containsColumn("testdoubles")) {
						System.out.println("key: testdoubles\t\tvalue: " + rec.getDoubles("testdoubles"));
					}
					if (rec.containsColumn("teststrings")) {
						System.out.println("key: teststrings\t\tvalue: " + rec.getStrings("teststrings"));
					}
					if (rec.containsColumn("testbinaries")) {
						System.out.print("key: testbinaries\t\tvalue:");
						for (byte[] bs : rec.getBinaries("testbinaries")) {
							for (byte b : bs) {
								System.out.print(" " + b);
							}
							System.out.print(";");
						}
						System.out.println();
					}
					if (rec.containsColumn("teststructs")) {
						System.out.println("key: teststructs\t\tvalue: " + rec.getStructs("teststructs"));
					}

				} catch (RESessionException ex) {
					ex.printStackTrace();
				}
			}
			return true;
		}
	}
}
