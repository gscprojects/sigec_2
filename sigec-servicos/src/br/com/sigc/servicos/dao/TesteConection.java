package br.com.sigc.servicos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TesteConection {


	
public static void main(String[] args) {
		
		Boolean resultado;
		resultado=  false;
		
		try {
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sigec", "root", "adminadmin");
			System.out.println("Conectado!");
			
			
			PreparedStatement stmt = con.prepareStatement("SELECT nome FROM ALUNO WHERE EMAIL = ? AND SENHA=?");
	    	   
	        
	        stmt.setString(1,"maria@email.com");
	         stmt.setString(2,"123456");
	        
	        // executa um select
	        ResultSet rs = stmt.executeQuery();
	        // itera no ResultSet
	        while (rs.next()) {
	        	
	        System.out.println(rs.getString("nome"));
	        	
	        	}
	        
			
			con.close();
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		
}
	}
	
