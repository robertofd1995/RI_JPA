package uo.ri.amp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TMecanicos")
public class Mecanico
{
	@Id
	@GeneratedValue
	private Long id;

	private String dni;
	private String apellidos;
	private String nombre;

	@OneToMany(mappedBy="mecanico")
	private Set<Averia> averias = new HashSet<>();
	@OneToMany(mappedBy="mecanico")
	private Set<Intervencion> intervenciones = new HashSet<>();
	
	@OneToMany(mappedBy="mecanico")
	private Set<Certificado> certificados = new HashSet<>();
	
	@OneToMany(mappedBy="mecanico")
	private Set<Asistencia> asistencias = new HashSet<>();
	

	public Mecanico()
	{
	}

	public Mecanico(String dni)
	{
		super();
		this.dni = dni;
	}

	public Mecanico(String dni, String nombre, String apellidos)
	{
		this(dni);
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public String getApellidos()
	{
		return apellidos;
	}

	public void setApellidos(String apellidos)
	{
		this.apellidos = apellidos;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getDni()
	{
		return dni;
	}

	public Set<Averia> getAsignadas()
	{
		return new HashSet<Averia>(averias);
	}

	protected Set<Averia> _getAsignadas()
	{
		return averias;
	}
	
	public Set<Asistencia> getAsitencias()
	{
		return new HashSet<Asistencia>(asistencias);
	}

	protected Set<Asistencia> _getAsistencias()
	{
		return asistencias;
	}

	public Set<Intervencion> getIntervenciones()
	{
		return new HashSet<Intervencion>(intervenciones);
	}

	protected Set<Intervencion> _getIntervenciones()
	{
		return intervenciones;
	}
	
	public HashSet<Certificado> getCertificados()
	{
		return new HashSet<Certificado>(certificados);
	}

	protected Set<Certificado> _getCertificados()
	{
		return certificados;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mecanico other = (Mecanico) obj;
		if (dni == null)
		{
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Mecanico [dni=" + dni + ", apellidos=" + apellidos + ", nombre=" + nombre + "]";
	}

	public Long getId()
	{
		return id;
	}

}
