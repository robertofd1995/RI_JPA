package uo.ri.amp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TTiposVehiculo")
public class TipoVehiculo
{
	@Id
	@GeneratedValue
	private Long id;

	private String nombre;
	private Double precioHora;
	private Double horasExperto;

	@OneToMany(mappedBy="tipo")
	private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();
	
	@OneToMany(mappedBy="tipo")
	private Set<Fragmento> fragmentos=new HashSet<Fragmento>();

	TipoVehiculo(){}

	public TipoVehiculo(String nombre)
	{
		this.nombre = nombre;
	}

	public TipoVehiculo(String nombre, double precioHora,double horasExperto)
	{
		this(nombre);
		this.precioHora = precioHora;
		this.horasExperto=horasExperto;
	}

	public Double getPrecioHora()
	{
		return precioHora;
	}

	public void setPrecioHora(Double precioHora)
	{
		this.precioHora = precioHora;
	}
	
	public Double getHorasExperto() {
		return horasExperto;
	}

	public void setHorasExperto(Double horasExperto) {
		this.horasExperto = horasExperto;
	}

	public String getNombre()
	{
		return nombre;
	}

	public Set<Vehiculo> getVehiculos()
	{
		return new HashSet<Vehiculo>(vehiculos);
	}

	protected Set<Vehiculo> _getVehiculos()
	{
		return vehiculos;
	}
	
	Set<Fragmento> _getFragmentos() {
		return fragmentos;
	}
	
	public Set<Fragmento> getFragmentos() {
		return new HashSet<Fragmento>(fragmentos);
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		TipoVehiculo other = (TipoVehiculo) obj;
		if (nombre == null)
		{
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "TipoVehiculo [ id="+id+", nombre=" + nombre + ", precioHora=" + precioHora.longValue() +", horas experto :"+ horasExperto.doubleValue()+ "]";
	}

}
