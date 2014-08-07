package com.schmapty.guvnah.test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class AcheckerMYSQLReader {

	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	private String USER_NAME ="[USERNAME]";
	private String PASSWORD  = "[PASSWORD]";
	
	String query = 
			" select c.check_id, c.confidence, c.html_tag, l_name.text name, l_note.text note, l_err.text err, l_desc.text description, c.func " +
			" from achecker.ac_checks c  " +
			"	     LEFT JOIN achecker.ac_language_text l_name ON c.name=l_name.term " +
			"		 LEFT JOIN achecker.ac_language_text l_desc ON c.description=l_desc.term " +
			"		 LEFT JOIN achecker.ac_language_text l_err ON c.err=l_err.term " +
			"		 LEFT JOIN achecker.ac_language_text l_note ON c.note = l_note.term " +
			" order by check_id ";
	
	@Test
	public void readDataBase() throws Exception {
	    try {
	      
	      Class.forName("com.mysql.jdbc.Driver");
	      connect = DriverManager
	          .getConnection(String.format("jdbc:mysql://localhost:8889/achecker?user=%s&password=%s", USER_NAME, PASSWORD));

	      statement = connect.createStatement();
	      resultSet = statement
	          .executeQuery(query);
	      
	      while(resultSet.next()) {
	    	  
	    	  String name = resultSet.getString("name");
	    	  if(name!=null) name = name.replace("\"","'");
	    	  String note = resultSet.getString("note");
	    	  if(note!=null) note = note.replace("\"","'");
	    	  String err = resultSet.getString("err");
	    	  if(err!=null) err = err.replace("\"","'");
	    	  String description = resultSet.getString("description");
	    	  if(description!=null) err = description.replace("\"","'");
	    	  
	    	  System.out.println("rule." + resultSet.getInt("check_id") + ".name="+name);
	    	  System.out.println("rule." + resultSet.getInt("check_id") + ".desc="+description);
	    	  
	    	  
	    	  /*System.out.println("\"" + resultSet.getInt("check_id") + "\",\"" 
	    			  + resultSet.getInt("confidence")  + "\",\""
	    			  + name + "\",\""
	    			  + resultSet.getString("html_tag") + "\",\""
	    			  + note + "\",\""
	    			  + err + "\",\""
	    			  + description + "\"");//+ "|"
	    			 // + resultSet.getString("func").replace("\n"," "));
	      	*/
	      }

	    } catch (Exception e) {
	      throw e;
	    } finally {
	      close();
	    }
	  }
	
	  private void close() {
		    try {
		      if (resultSet != null) {
		        resultSet.close();
		      }

		      if (statement != null) {
		        statement.close();
		      }

		      if (connect != null) {
		        connect.close();
		      }
		    } catch (Exception e) {

		    }
		  }

}
