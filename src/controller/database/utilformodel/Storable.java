package controller.database.utilformodel;

import jdk.nashorn.internal.objects.annotations.Function;

public interface Storable {
	@Function
	public SQLiteData getSQLiteData();
}