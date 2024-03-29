package uo.ri.amp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TVehiculos")
public class Vehiculo
{
	@Id
	@GeneratedValue
	private Long id;

	private String marca;
	private String matricula;
	private String modelo;

	@Column(name="num_averias")
	private int numAverias = 0;

	@ManyToOne private TipoVehiculo tipo;
	@ManyToOne private Cliente cliente;

	@OneToMany(mappedBy="vehiculo")
	private Set<Averia> averias = new HashSet<>();

	public Vehiculo(){}

	public Vehiculo(String matricula)
	{
		super();
		this.matricula = matricula;
	}

	public Vehiculo(String matricula, String marca, String modelo)
	{
		this(matricula);
		this.marca = marca;
		this.modelo = modelo;
	}

	public String getMarca()
	{
		return marca;
	}

	public void setMarca(String marca)
	{
		this.marca = marca;
	}

	public String getModelo()
	{
		return modelo;
	}

	public void setModelo(String modelo)
	{
		this.modelo = modelo;
	}

	public int getNumAverias()
	{
		return numAverias;
	}

	public void setNumAverias(int numAverias)
	{
		this.numAverias = numAverias;
	}

	public String getMatricula()
	{
		return matricula;
	}

	public TipoVehiculo getTipo()
	{
		return tipo;
	}

	protected void _setTipo(TipoVehiculo tipoVehiculo)
	{
		this.tipo = tipoVehiculo;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	protected void _setCliente(Cliente cliente)
	{
		this.cliente = cliente;
	}

	public Set<Averia> getAsignadas()
	{
		return new HashSet<Averia>(averias);
	}

	protected Set<Averia> _getAsignadas()
	{
		return averias;
	}

	public void removeAveria(Averia averia)
	{
		Association.Averiar.unlink(this, averia);

	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		Vehiculo other = (Vehiculo) obj;
		if (matricula == null)
		{
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Vehiculo [marca=" + marca + ", matricula=" + matricula + ", modelo=" + modelo + ", numAverias="
				+ numAverias + "]";
	}

	public Long getId()
	{
		return id;
	}

}
