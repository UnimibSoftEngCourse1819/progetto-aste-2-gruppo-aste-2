package controller.database.utilformodel;


/**
 * This interface is for class that
 * can provide the insert into statement
 */
public interface Storable {
	public SQLiteData getSQLiteData();
}