package controller.database.select;

import jdk.nashorn.internal.objects.annotations.Function;

/**
 * TODO concrete classes : JOINT, WHERE, ORDER BY
 * @author Vallero
 *
 */
public interface SelectClause {
	@Function
	public abstract String getSQLText();
}
