package service;

import java.util.List;

import dao.AlumnosDao;
import dao.CursosDao;
import model.Alumno;
import model.Curso;

public class FormacionService {
	AlumnosDao alumnosDao;
	CursosDao cursosDao;
	public FormacionService() {
		alumnosDao=new AlumnosDao();
		cursosDao=new CursosDao();
	}
	public boolean altaAlumno(Alumno alumno) {		
		if(alumnosDao.findByDni(alumno.getDni())==null) {
			alumnosDao.save(alumno);
			return true;
		}
		return false;
	}
	public boolean altaCurso(Curso curso) {		
		if(cursosDao.findByDenominacionAndFechaInicio(curso.getDenominacion(), curso.getFechaInicio())==null) {
			cursosDao.save(curso);
			return true;
		}
		return false;
	}
	
	public List<Alumno> alumnosPorCurso(String curso){
		return alumnosDao.findByCurso(curso);
	}
	//si el curso existe, se elimina tanto el curso como los alumnos matriculados
	//en el mismo. Si el curso no existe, no hace nada y devuelve false
	public boolean eliminarCurso(int curso) {
		if(cursosDao.findByIdCurso(curso)!=null) {
			cursosDao.delete(curso);
			alumnosDao.deleteByCurso(curso);
			return true;
		}
		return false;
	}
}
