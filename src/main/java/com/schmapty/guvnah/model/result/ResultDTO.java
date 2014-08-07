package com.schmapty.guvnah.model.result;

import java.io.Serializable;

public interface ResultDTO extends Serializable, Comparable<ResultDTO> {

	
	public static final Integer NULL_INTEGER = new Integer(-1);
	public static final String  NULL_STRING = "NULL";
	
}
