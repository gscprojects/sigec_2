package br.com.sigc.servicos.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* CLASSE DE CONEXAO COM O BANCO DE DADOS*/
public class AutenticacaoDAO {

/* METODO QUE FAZ CONEXAO COM BANCO DE DADOS */
	public Connection getConnection() {
		
	    	Connection con; 
		
		  String dbName = "sigec";
		  String userName ="admin";
		  String password = "12345678";
		  String hostname = "sigec.cvmdbsiqb3pk.sa-east-1.rds.amazonaws.com";
		  String port = "3306";
		  String jdbcUrl = "jdbc:mysql://" + hostname + ":" +
		    port + "/" + dbName + "?user=" + userName + "&password=" + password;
		
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcUrl);
			System.out.println("Conectado!");
			
		return con;
		
		} catch (SQLException e) {
		
			e.printStackTrace();
			return null;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
      
        
        // METODO QUE  VALIDA SE O EMAIL E SENHA ESTAO CADASTRADOS NA BASE
        public String autenticar (String email, String senha) {
        	
    		String resultado;
    		resultado=  "0";
    		Connection con =  null; 
    		PreparedStatement stmt = null;
    		ResultSet rs  = null;
    		
    		try {
    			   			
    			con = getConnection();
    			stmt = con.prepareStatement("SELECT nome FROM ALUNO WHERE EMAIL = ? AND SENHA=?");
    	    	
    			stmt.setString(1,email);
    	        stmt.setString(2,senha);
    	        
    	        rs = stmt.executeQuery();
    	
    	        while (rs.next()) {
    	        	resultado =rs.getString("nome");
    	        }
    	   
    	        con.close();
    			rs.close();
    	        stmt.close();
    	        
    		} catch (SQLException e) {
    		
    			e.printStackTrace();
    			
    		}
    		
    	return resultado;
    	}
   
}
