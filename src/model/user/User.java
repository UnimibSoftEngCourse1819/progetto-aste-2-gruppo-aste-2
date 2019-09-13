package model.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import controller.database.SQLParameter;
import controller.database.utilformodel.SQLiteData;
import controller.database.utilformodel.Storable;

public class User implements Storable {
	private String name;
	private String surname;
	private LocalDate birthDate;
	private String city;
	private String street;
	private String email;
	private String phone;
	private String password;
	private String id;
	private List<UserAttribute> data;
	
	public User(HttpServletRequest request) {
		name = request.getParameter("name");
		surname = request.getParameter("surname");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		formatter = formatter.withLocale(Locale.getDefault());
		birthDate = LocalDate.parse(request.getParameter("date"), formatter);
		city = request.getParameter("city");
		street = request.getParameter("street");
		email = request.getParameter("email");
		phone = request.getParameter("phone");
		password = request.getParameter("password");
	}
	
	public String getId() {
		return id;
	}

	@Override
	public SQLiteData getSQLiteData() {
		SQLiteData sqlData = new SQLiteData("user");
		
		sqlData.add("Name", SQLParameter.VARCHAR + "(25)", name);
		sqlData.add("Surname", SQLParameter.VARCHAR + "(25)", surname);
		sqlData.add("Birth", SQLParameter.DATE, birthDate);
		sqlData.add("Email", SQLParameter.VARCHAR + "(100)", email);
		sqlData.add("Password", SQLParameter.VARCHAR + "(30)", password);
		sqlData.add("Address", SQLParameter.VARCHAR + "(50)", street);
		sqlData.add("City", SQLParameter.VARCHAR + "(25)", city);
		
		return sqlData;
	}
}
