package es.flasheat.util;

import java.util.List;

public class SQLUtils {

	public static final String toSQLIn(List<Long> ids) {
		StringBuilder sb = new StringBuilder();
		//sb.append("(");
		for (int i = 0; i<ids.size()-1; i++) {
			sb.append(ids.get(i));
			sb.append(",");
		}
		sb.append(ids.get(ids.size()-1));
		//sb.append(")");
		return sb.toString();
	}
	
	public static final void addClause(StringBuilder queryString, boolean first, String clause) {
		queryString.append(first? "WHERE ": " AND ").append(clause);
	}

	
	public static final boolean addClause(Object parameter, StringBuilder queryString, boolean first, String clause) {
		return addClause(parameter!=null, queryString, first, clause);
	}
	
	public static final boolean addClause(boolean filterBy, StringBuilder queryString, boolean first, String clause) {
		if (filterBy) {
			addClause(queryString, first, clause);
			first = false;
		}
		return first;			
	}
}
