package controller.database;

import java.sql.Connection;
import jdk.nashorn.internal.objects.annotations.Function;

public interface SQLAction {
	@Function
	public void execute(Connection connection);
}
