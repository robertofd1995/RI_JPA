package uo.ri.amp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uo.ri.amp.model.types.FragmentoKey;


@Entity
@IdClass(FragmentoKey.class)
@Table(name = "TFragmento")
public class Fragmento {

    @Id
    @ManyToOne
    private Curso curso;

    @Id
    @ManyToOne
    private TipoVehiculo tipo;


    private Double horas;

    public Fragmento() {
    }

    public Fragmento(TipoVehiculo tipo, Curso curso) {
        Association.Fragmentar.link(curso, this, tipo);
    }

    public Fragmento(Curso curso, TipoVehiculo tipo, Double horas) {
        this(tipo, curso);
        this.horas = horas;
    }

    @Override
    public String toString() {
        return "Fragmento [curso=" + curso.getNombre() + ", tipoVehiculo=" +
                tipo.getNombre() + ", horas=" + horas.doubleValue() + "]";
    }

    public Double getHoras() {
        return horas;
    }

    public void setHoras(Double horas) {
        this.horas = horas;
    }

    public Curso getCurso() {
        return curso;
    }

    protected void _setCurso(Curso curso) {
        this.curso = curso;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipo;
    }

    protected void _setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipo = tipoVehiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fragmento fragmento = (Fragmento) o;

        if (!getCurso().equals(fragmento.getCurso())) return false;
        return tipo.equals(fragmento.tipo);

    }

    @Override
    public int hashCode() {
        int result = getCurso().hashCode();
        result = 31 * result + tipo.hashCode();
        return result;
    }
}
