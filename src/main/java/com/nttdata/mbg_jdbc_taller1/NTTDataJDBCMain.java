package com.nttdata.mbg_jdbc_taller1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/*
 * Ejercicio JDBC - NTTData
 * 
 * Conexión a una base de datos y operación con sus datos
 * 
 * @author Miguel Ángel Ballano Garduño
 * 
 * */
public class NTTDataJDBCMain 
{
    public static void main( String[] args ){
    	 consultarRolJugador();
    }
    
    
    private static void consultarRolJugador() {
    	try {
    		//Driver de conexion a ala base de datos
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		//Se realiza la conexion con la base de datos
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tallerfpdual", "root",
			         "rootroot");
			
			//Creacion de consulta
			Statement sentencia = conexion.createStatement();
			String consulta = "SELECT sp.name AS nombreJugador, st.name AS nombreEquipo, sp.first_rol AS rol1 FROM nttdata_mysql_soccer_player sp JOIN nttdata_mysql_soccer_team ST ON sp.id_soccer_team = st.id_soccer_team";
			ResultSet resConsulta = sentencia.executeQuery(consulta);
			
			//Recorro los resultados
			StringBuilder infoJugador = new StringBuilder();
			while(resConsulta.next()) {
				infoJugador.append("El jugador ");
				infoJugador.append(resConsulta.getString("nombreJugador"));
				
				infoJugador.append(" pertenece al equipo ");
				infoJugador.append(resConsulta.getString("nombreEquipo"));
				
				infoJugador.append(" y su posicion es ");
				infoJugador.append(resConsulta.getString("rol1"));
				
				infoJugador.append("\n");
			}
			
			System.out.println(infoJugador.toString());
			
			//Finalizo conexion con la base de datos
			conexion.close();
    	 } catch (SQLException e) {
			System.out.println("ERROR");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR");
		}
    }
}
