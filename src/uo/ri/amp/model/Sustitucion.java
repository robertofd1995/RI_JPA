package uo.ri.amp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uo.ri.model.types.SustitucionKey;

@Entity
@IdClass(SustitucionKey.class)
@Table(name="TSustituciones")
public class Sustitucion
{

	@Id
	@ManyToOne
	private Repuesto repuesto;
	@Id
	@ManyToOne
	private Intervencion intervencion;
	
	private int cantidad;

	Sustitucion()
	{
	}

	public Sustitucion(Repuesto repuesto, Intervencion intervencion)
	{
		Association.Sustituir.link(this, repuesto, intervencion);
	}

	public void setCantidad(int cantidad)
	{
		this.cantidad = cantidad;

	}

	public Repuesto getRepuesto()
	{
		return repuesto;
	}

	public Intervencion getIntervencion()
	{
		return intervencion;
	}

	public int getCantidad()
	{
		return cantidad;
	}

	protected void _setIntevencion(Intervencion intervencion)
	{
		this.intervencion = intervencion;
	}

	protected void _setRepuesto(Repuesto repuesto)
	{
		this.repuesto = repuesto;
	}

	

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((intervencion == null) ? 0 : intervencion.hashCode());
		result = prime * result + ((repuesto == null) ? 0 : repuesto.hashCode());
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
		Sustitucion other = (Sustitucion) obj;
		if (intervencion == null)
		{
			if (other.intervencion != null)
				return false;
		} else if (!intervencion.equals(other.intervencion))
			return false;
		if (repuesto == null)
		{
			if (other.repuesto != null)
				return false;
		} else if (!repuesto.equals(other.repuesto))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Sustitucion [repuesto=" + repuesto + ", intervencion=" + intervencion + ", cantidad=" + cantidad + "]";
	}

	public double getImporte()
	{
		return repuesto.getPrecio() * cantidad;
	}
}
