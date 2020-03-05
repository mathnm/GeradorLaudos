package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	public static Connection getConexao() {
		Connection conn = null;
		try {
			File bd = new File("GeradorLaudos.db");
			if (bd.exists()){
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:GeradorLaudos.db");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
