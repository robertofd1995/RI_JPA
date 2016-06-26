package uo.ri.amp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uo.ri.model.types.CargoKey;

@Entity
@IdClass(CargoKey.class)
@Table(name="TCargos")
public class Cargo
{

	@Id @ManyToOne  private Factura factura;
	@Id @ManyToOne  private MedioPago medioPago;
	private double importe = 0.0;

	Cargo()
	{
	}

	public Cargo(Factura factura, MedioPago medioPago)
	{
		this.factura = factura;
		this.medioPago = medioPago;
		Association.Cargar.link(this, factura, medioPago);
	}

	public Cargo(Factura factura, MedioPago medioPago, double importe)
	{
		// this(factura,medioPago);
		this.importe = importe;
		this.factura = factura;
		this.medioPago = medioPago;
		Association.Cargar.link(this, factura, medioPago);
	}

	public Factura getFactura()
	{
		return factura;
	}

	protected void _setFactura(Factura factura)
	{
		this.factura = factura;
	}

	public MedioPago getMedioPago()
	{
		return medioPago;
	}

	protected void _setMedioPago(MedioPago medioPago)
	{
		this.medioPago = medioPago;
	}

	public double getImporte()
	{
		return importe;
	}

	public void setImporte(double importe)
	{
		this.importe = importe;
	}
}
