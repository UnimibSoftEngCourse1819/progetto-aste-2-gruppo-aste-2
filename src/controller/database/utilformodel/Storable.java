package controller.database.utilformodel;

import jdk.nashorn.internal.objects.annotations.Function;

/**
 * This interface is for class that
 * can provide the insert into statement
 */
public interface Storable {
	@Function
	public SQLiteData getSQLiteData();
}