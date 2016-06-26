package uo.ri.amp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TRepuestos")
public class Repuesto
{

	@Id
	@GeneratedValue
	private Long id;

	private String codigo;
	private String descripcion;
	private double precio;

	@OneToMany(mappedBy="repuesto")
	private Set<Sustitucion> sustituciones = new HashSet<>();

	Repuesto()
	{
	}

	public Repuesto(String codigo)
	{
		super();
		this.codigo = codigo;
	}

	public Repuesto(String codigo, String descripcion, double precio)
	{
		this(codigo);
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public Object getId()
	{
		return id;
	}

	public long getDescripcion()
	{
		return id;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	public double getPrecio()
	{
		return precio;
	}

	public void setPrecio(double precio)
	{
		this.precio = precio;
	}

	public String getCodigo()
	{
		return codigo;
	}

	public Set<Sustitucion> getSustituciones()
	{
		return new HashSet<Sustitucion>(sustituciones);
	}

	protected Set<Sustitucion> _getSustituciones()
	{
		return sustituciones;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Repuesto other = (Repuesto) obj;
		if (codigo == null)
		{
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Repuesto [codigo=" + codigo + ", descripcion=" + descripcion + ", precio=" + precio + "]";
	}

}
