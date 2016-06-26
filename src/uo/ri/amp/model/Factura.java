package uo.ri.amp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import alb.util.date.DateUtil;
import alb.util.math.Round;
import uo.ri.model.exception.BusinessException;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;

@Entity
@Table(name="TFacturas")
public class Factura
{
	@Id
	@GeneratedValue
	private Long id;

	private Long numero;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	private double importe;
	private double iva=0;
	
	@Enumerated(EnumType.STRING)
	private FacturaStatus status = FacturaStatus.SIN_ABONAR;

	@OneToMany(mappedBy="factura")
	private Set<Averia> averias = new HashSet<>();
	@OneToMany(mappedBy="factura")
	private Set<Cargo> cargos = new HashSet<>();

	public Factura() {
	}

	public Factura(Long numero)
	{
		super();
		this.numero = numero;
	}

	public Factura(long numero, Date fecha)
	{
		this(numero);
		this.fecha = fecha;
		calcularIva();
	}

	public Factura(long numero, List<Averia> averias) throws BusinessException
	{
		this(numero);
		checkFacturasTerminadas(averias);
		this.averias = new HashSet<Averia>(averias);
		facturarAverias(averias);
	}

	public Factura(long numero, Date fecha, List<Averia> averias) throws BusinessException
	{
		this(numero);
		checkFacturasTerminadas(averias);
		this.fecha = fecha;
		this.averias = new HashSet<Averia>(averias);
		facturarAverias(averias);
	}



	private void checkFacturasTerminadas(List<Averia> averias) throws BusinessException
	{
		for (Averia averia : averias)
		{
			if (averia.getStatus() != AveriaStatus.TERMINADA)
				throw new BusinessException("No se puede facturar averias NO terminadas");
		}
	}

	private void facturarAverias(List<Averia> averias)
	{
		for (Averia averia : averias)
			averia.setStatus(AveriaStatus.FACTURADA);
		recalcularImporte();
	}
	
	private void calcularIva()
	{
		if (fecha != null && fecha.before(DateUtil.fromString("1/7/2012")))
			this.iva= 1.18;
		else
			this.iva= 1.21;
	}

	private void recalcularImporte()
	{
		if(this.iva==0)
			calcularIva();
		double result = 0;
		for (Averia averia : averias)
		{
			averia.calcularImporte();
			result += averia.getImporte();
		}
		result*=this.iva;
		this.importe = Round.twoCents(result);
	}

	public Date getFecha()
	{
		return fecha;
	}

	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
	}

	public double getImporte()
	{
		return importe;
	}

	public double getIva()
	{
		return iva;
	}

	public void setIva(double iva)
	{
		this.iva = iva;
	}

	public FacturaStatus getStatus()
	{
		return status;
	}

	public void setStatus(FacturaStatus status)
	{
		this.status = status;
	}

	public Long getNumero()
	{
		return numero;
	}

	public Set<Averia> getAsignadas()
	{
		return new HashSet<Averia>(averias);
	}

	protected Set<Averia> _getAsignadas()
	{
		return averias;
	}

	public void addAveria(Averia averia) throws BusinessException
	{
		if (averia.getStatus() != AveriaStatus.TERMINADA)
			throw new BusinessException("No se puede facturar averias NO terminadas");
		Association.Facturar.link(this, averia);
		averia.setStatus(AveriaStatus.FACTURADA);
		if(averias.isEmpty())
			recalcularImporte();
		else
		{
			calcularIva();
			averia.calcularImporte();
			this.importe+=Round.twoCents(averia.getImporte()*this.iva);
		}
		// averias.add(averia);
	}

	public void removeAveria(Averia averia)
	{
		Association.Facturar.unlink(this, averia);
		recalcularImporte();
		// averias.remove(averia);
	}

	public Set<Cargo> getCargos()
	{
		return new HashSet<Cargo>(cargos);
	}

	protected Set<Cargo> _getCargos()
	{
		return cargos;
	}
	
	public long getId()
	{
		return id;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Factura other = (Factura) obj;
		if (numero == null)
		{
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Factura [numero=" + numero + ", fecha=" + fecha + ", importe=" + importe + ", iva=" + iva + ", status="
				+ status + "]";
	}


}
