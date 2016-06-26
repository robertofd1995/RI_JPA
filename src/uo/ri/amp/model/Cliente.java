package uo.ri.amp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uo.ri.model.types.Address;

@Entity
@Table(name="TClientes")
public class Cliente
{
	@Id
	@GeneratedValue
	private Long id;

	private String nombre;
	private String apellidos;
	private String dni;
	private Address address;

	@OneToMany(mappedBy="cliente")
	private Set<Vehiculo> vehiculos = new HashSet<>();
	@OneToMany(mappedBy="cliente")
	private Set<MedioPago> mediosPago = new HashSet<>();

	Cliente()
	{
	}

	public Cliente(String dni)
	{
		super();
		this.dni = dni;
	}

	public Cliente(String dni, String nombre, String apellidos)
	{
		this(dni);
		this.nombre = nombre;
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

	public String getApellidos()
	{
		return apellidos;
	}

	public void setApellidos(String apellidos)
	{
		this.apellidos = apellidos;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	public String getDni()
	{
		return dni;
	}

	public Set<Vehiculo> getVehiculos()
	{
		return new HashSet<Vehiculo>(vehiculos);
	}

	protected Set<Vehiculo> _getVehiculos()
	{
		return vehiculos;
	}

	public Set<MedioPago> getMediosPago()
	{
		return new HashSet<MedioPago>(mediosPago);
	}

	protected Set<MedioPago> _getMediosPago()
	{
		return mediosPago;
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
		Cliente other = (Cliente) obj;
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
		return "Cliente [nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + "]";
	}

	public Long getId()
	{
		return id;
	}

}
