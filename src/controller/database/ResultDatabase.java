package controller.database;

import java.util.List;

import jdk.javadoc.internal.doclets.toolkit.util.Utils.Pair;

public class ResultDatabase {
	private List<Pair<String, SQLValue>> table;
	
	
	private class SQLValue{
		String type;
		Object value;
	}
}
