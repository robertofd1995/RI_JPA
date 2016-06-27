package uo.ri.amp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import uo.ri.model.types.AveriaStatus;

@Entity
@Table(name = "TAverias")
public class Averia {
    @Id
    @GeneratedValue
    private Long id;

    private String descripcion;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private Double importe;

    @Enumerated(EnumType.STRING)
    private AveriaStatus status = AveriaStatus.ABIERTA;

    @OneToMany(mappedBy = "averia")
    private Set<Intervencion> intervenciones = new HashSet<Intervencion>();
    @ManyToOne
    private Mecanico mecanico;
    @ManyToOne
    private Factura factura;
    @ManyToOne
    private Vehiculo vehiculo;

    public Averia() {
    }

    public Averia(Date fecha, Vehiculo vehiculo) {
        super();
        this.fecha = fecha;
        Association.Averiar.link(vehiculo, this);
    }

    public Averia(Date fecha, Vehiculo vehiculo, String descripcion, Double importe) {
        this(fecha, vehiculo);
        this.descripcion = descripcion;
        this.importe = importe;
    }

    public Averia(Vehiculo vehiculo, String descripcion, Double importe) {
        this(new Date(), vehiculo, descripcion, importe);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public AveriaStatus getStatus() {
        return status;
    }

    public void setStatus(AveriaStatus status) {
        this.status = status;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    protected void _setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    protected void _setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public Factura getFactura() {
        return factura;
    }

    protected void _setFactura(Factura factura) {
        this.factura = factura;
    }

    public Set<Intervencion> getIntervenciones() {
        return new HashSet<Intervencion>(intervenciones);
    }

    protected Set<Intervencion> _getIntervenciones() {
        return intervenciones;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
        result = prime * result + ((vehiculo == null) ? 0 : vehiculo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Averia other = (Averia) obj;
        if (fecha == null) {
            if (other.fecha != null)
                return false;
        } else if (!fecha.equals(other.fecha))
            return false;
        if (vehiculo == null) {
            if (other.vehiculo != null)
                return false;
        } else if (!vehiculo.equals(other.vehiculo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Averia [descripcion=" + descripcion + ", fecha=" + fecha + ", importe=" + importe + ", status="
                + status + ", vehiculo=" + vehiculo + "]";
    }

    public void calcularImporte() {
        double result = 0;
        for (Intervencion intervencion : intervenciones)
            result += intervencion.getImporte();
        this.importe = result;
    }

    public Long getId() {
        return id;
    }

}
