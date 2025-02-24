package dao;

import static dao.DatosBd.getConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Alumno;

public class AlumnosDao {
	
	public void save(Alumno alumno) {
		try(Connection con=getConnection()){
			String sql="insert into alumnos(dni,nombre,edad,email,curso) values(?,?,?,?,?)";
			PreparedStatement st=con.prepareStatement(sql);
			st.setString(1, alumno.getDni());
			st.setString(2, alumno.getNombre());
			st.setInt(3, alumno.getEdad());
			st.setString(4, alumno.getEmail());
			st.setInt(5, alumno.getCurso());
			st.execute();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public List<Alumno> findByCurso(String denominacion){
		List<Alumno> alumnos=new ArrayList<>();
		try(Connection con=getConnection();){
			String sql="SELECT alumnos.* FROM alumnos,cursos where denominacion=? and curso=idCurso;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, denominacion);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				alumnos.add(new Alumno(
						rs.getString("dni"),
						rs.getString("nombre"),
						rs.getInt("edad"),
						rs.getString("email"),
						rs.getInt("curso")
						));
			}
			return alumnos;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			return List.of();
		}
	}
	public Alumno findByDni(String dni) {
		try(Connection con=getConnection()){
			String sql="select * from alumnos where dni=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, dni);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return new Alumno(
						rs.getString("dni"),
						rs.getString("nombre"),
						rs.getInt("edad"),
						rs.getString("email"),
						rs.getInt("curso")
						);
			}
			return null;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	//listado de alumnos matriculados en cursos cuya fecha de inicio está 
	//comprendida entre dos fechas recibidas como parámetro
	public List<Alumno> findByFechasCurso(LocalDate f1, LocalDate f2){
		List<Alumno> alumnos=new ArrayList<>();
		try(Connection con=getConnection();){
			String sql="SELECT alumnos.* FROM alumnos,cursos where fechaInicio between ? and ? and curso=idCurso";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setDate(1, Date.valueOf(f1));
			ps.setDate(2, Date.valueOf(f2));
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				alumnos.add(new Alumno(
						rs.getString("dni"),
						rs.getString("nombre"),
						rs.getInt("edad"),
						rs.getString("email"),
						rs.getInt("curso")
						));
			}
			return alumnos;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			return List.of();
		}
	}
	
	//método que elimine los alumnos del curso cuyo código se recibe como parámetro
	public void deleteByCurso(int curso) {
		try(Connection con=getConnection()){
			String sql="delete from alumnos where curso=?";
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1, curso);
			st.execute();
		}
		catch(SQLException ex) {
			ex.printStackTrace();	
		}
	}
	
}
