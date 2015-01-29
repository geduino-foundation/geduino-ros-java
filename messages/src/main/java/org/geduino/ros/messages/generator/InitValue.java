package org.geduino.ros.messages.generator;

import java.util.HashMap;
import java.util.Map;

import org.geduino.ros.core.messages.model.DataType;

import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;

class InitValue {

	static final Map<DataType, JExpression> INIT_VALUE = new HashMap<DataType, JExpression>();
	static {
		INIT_VALUE.put(DataType.BOOL, JExpr.lit(false));
		INIT_VALUE.put(DataType.INT8, JExpr.lit(0));
		INIT_VALUE.put(DataType.BYTE, JExpr.lit(0));
		INIT_VALUE.put(DataType.UINT8, JExpr.lit(0));
		INIT_VALUE.put(DataType.CHAR, JExpr.lit(0));
		INIT_VALUE.put(DataType.INT16, JExpr.lit(0));
		INIT_VALUE.put(DataType.UINT16, JExpr.lit(0));
		INIT_VALUE.put(DataType.INT32, JExpr.lit(0));
		INIT_VALUE.put(DataType.UINT32, JExpr.lit(0L));
		INIT_VALUE.put(DataType.INT64, JExpr.lit(0L));
		INIT_VALUE.put(DataType.UINT64, JExpr.direct("java.math.BigInteger.ZERO"));
		INIT_VALUE.put(DataType.FLOAT32, JExpr.lit(0F));
		INIT_VALUE.put(DataType.FLOAT64, JExpr.lit(0D));
		INIT_VALUE.put(DataType.STRING, JExpr.lit(""));
		INIT_VALUE.put(DataType.TIME, JExpr.direct("org.geduino.ros.core.messages.model.Time.now()"));
		INIT_VALUE.put(DataType.DURATION, JExpr.direct("org.geduino.ros.core.messages.model.Duration.ZERO"));
	}

}
