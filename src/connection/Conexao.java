package connection;

import exception.FalhaConexaoBancoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/EmpresaX";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection conectando(){
        try{
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch(SQLException e){
            throw new FalhaConexaoBancoException("Impossível se conectar com o banco!");
        }
    }
}
