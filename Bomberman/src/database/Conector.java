package database;

import java.sql.*;
import java.util.ArrayList;

public class Conector {
	private String ruta = "Database/database";
	private Connection connect;
	
	public void connect(){
		 try {
		     connect = DriverManager.getConnection("jdbc:sqlite:"+ruta);
		     if (connect!=null) {
		         //System.out.println("Conectado");
		     }
		 }catch (SQLException ex) {
		     System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
		 }
		}
	
	public void close(){
		try{
			connect.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void grabarJugador(DatosJugador jugador){
		try{
			PreparedStatement st = connect.prepareStatement("Insert into jugador values(?,?,?,?)");
			st.setString(1, jugador.getId());
			st.setString(2, jugador.getPass());
			st.setInt(3, jugador.getPuntos());
			st.setInt(4, jugador.getEstado());
			st.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void grabarPuntos(DatosJugador jugador){
		PreparedStatement st = null;
		try {
			st = connect.prepareStatement("Update jugador set puntos = ? where id = '"+jugador.getId()+"';");
			st.setString(1, jugador.getId());
			st.setInt(2, jugador.getPuntos());
			st.executeUpdate();			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
    }
	public boolean usuarioRepetido(String usuario){
		boolean respuesta = false;
		ResultSet rs = null;
		try {
			Statement st = connect.createStatement();
			rs = st.executeQuery("SELECT * FROM jugador WHERE id='"+usuario+"';");
			while(rs.next())
				  respuesta = true;
			return respuesta;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return respuesta;
	}
	
	public boolean confirmarLogin(String usuario,String password){
		boolean respuesta = false;
		ResultSet rs = null;
		try {
			Statement st = connect.createStatement();
			rs = st.executeQuery("SELECT * FROM jugador WHERE id='"+usuario+"' AND password='"+password+"';");
			while(rs.next())
				  respuesta = true;
			return respuesta;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return respuesta;
	}

	public int puntosJugador(String usuario){
		ResultSet result = null;
		try {
            PreparedStatement st = connect.prepareStatement("select * from jugador");
            result = st.executeQuery();
            while (result.next()) {
                if(usuario.equals(result.getString("id")))
                	return result.getInt("puntos"); 		
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
    }
	public void modificarEstado(String usuario,int estado){
		PreparedStatement st = null;
		try {
			st = connect.prepareStatement("Update jugador set estado = ? where id = '"+usuario+"';");
			st.setString(1, usuario);
			st.setInt(1, estado);
			st.executeUpdate();			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	public ArrayList<String> ranking(){
		ArrayList<String> listado = new ArrayList<String>();
		ResultSet rs = null;
		Statement st = null;
		try{
			st = connect.createStatement();
			rs = st.executeQuery("Select * From jugador Order by puntos  Up to 5 rows");
			while(rs.next())
				  listado.add(rs.getString(1)+rs.getInt(3)) ;

		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return listado;
	}
	
}
