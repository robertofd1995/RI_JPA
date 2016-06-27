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

	public Cargo()
	{
	}

	public Cargo(Factura factura, MedioPago medioPago)
	{
		Association.Cargar.link(this, factura, medioPago);
	}

	public Cargo(Factura factura, MedioPago medioPago, double importe)
	{
		this(factura,medioPago);
		this.importe = importe;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Cargo cargo = (Cargo) o;

		if (!getFactura().equals(cargo.getFactura())) return false;
		return getMedioPago().equals(cargo.getMedioPago());

	}

	@Override
	public int hashCode() {
		int result = getFactura().hashCode();
		result = 31 * result + getMedioPago().hashCode();
		return result;
	}
}
