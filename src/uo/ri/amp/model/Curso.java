package uo.ri.amp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uo.ri.model.exception.BusinessException;


@Entity
@Table(name="TCursos")
public class Curso {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	private String descripcion;
	private Long codigo;
	private Double totalHoras;
	
	@OneToMany(mappedBy="curso")
	private Set<Fragmento> fragmentos=new HashSet<Fragmento>();
	
	@OneToMany(mappedBy="curso")
	private Set<Asistencia> asistencias = new HashSet<>();
	
	

	public Curso() {}
	
	public Curso(String nombre ,Long codigo, String descripcion,Double totalHoras){
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.totalHoras=totalHoras;
		this.codigo=codigo;
	}
	
	public boolean anadirFragmento(Fragmento fragmento) throws BusinessException{
		
		double suma=0;
		
		for (Fragmento fragmentoaux : fragmentos) {
			suma+=fragmentoaux.getHoras();
		}
		
		if(this.totalHoras.doubleValue() >= suma){
			Association.Fragmentar.link(this, fragmento);
			return true;
		}else{
			//return false;
			throw new BusinessException("La suma de los fragmentos supera las horas del curso");
		}	
		
		
	}
	
	public boolean eliminarFragmento(Fragmento fragmento) throws BusinessException{
		
		if (fragmentos.contains(fragmento)) {
			fragmentos.remove(fragmento);
			return true;
		}else{
			throw new BusinessException("Se esta intentando eliminar un fragmento que no existe");	
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Curso [nombre=" + nombre + ", descripcion=" + descripcion + ", totalHoras=" + totalHoras
				+ ", fragmentos=" + fragmentos + "]";
	}
	
	//GETTERS AND SETTERS

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getTotalHoras() {
		return totalHoras;
	}

	public void setTotalHoras(Double totalHoras) {
		this.totalHoras = totalHoras;
	}

	Set<Fragmento> _getFragmentos() {
		return fragmentos;
	}
	
	public Set<Fragmento> getFragmentos() {
		return new HashSet<Fragmento>(fragmentos);
	}
	
	Set<Asistencia> _getAsistencias() {
		return asistencias;
	}
	
	public Set<Asistencia> getAsistencias() {
		return new HashSet<Asistencia>(asistencias);
	}


	public Long getId() {
		return id;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	
	
	
	

}
