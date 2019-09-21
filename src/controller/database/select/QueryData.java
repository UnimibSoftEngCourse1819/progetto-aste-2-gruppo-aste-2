package controller.database.select;

import java.util.List;

public class QueryData {
	private final String query;
	private final List<String> typeParameters;
	
	public QueryData(String query, List<String> typeParameters) {
		this.query = query;
		this.typeParameters = typeParameters;
	}

	public String getQuery() {
		return query;
	}

	public List<String> getTypeParameters() {
		return typeParameters;
	}
}