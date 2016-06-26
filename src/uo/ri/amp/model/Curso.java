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

	public Curso(Long codigo){
		this.codigo=codigo;
	}
	
	public Curso(String nombre ,Long codigo, String descripcion,Double totalHoras){
		this(codigo);
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.totalHoras=totalHoras;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Curso curso = (Curso) o;

		return getCodigo().equals(curso.getCodigo());

	}

	@Override
	public int hashCode() {
		return getCodigo().hashCode();
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

	
	
	
	
	

}
