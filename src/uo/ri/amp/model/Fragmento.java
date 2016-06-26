package uo.ri.amp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uo.ri.amp.model.types.FragmentoKey;


@Entity
@IdClass(FragmentoKey.class)
@Table(name="TFragmento")
public class Fragmento {
	
	@Id
	@ManyToOne private Curso curso;
	
	@Id
	@ManyToOne private TipoVehiculo tipo;
	
	
	private Double horas;
	
	public Fragmento() {
	}
	
	public Fragmento(TipoVehiculo tipo , Double horas){
		this.curso=null;
		this.tipo=tipo;
		this.horas=horas;	
	}
	
	public Fragmento(Curso curso,TipoVehiculo tipo , Double horas){
		
		this.curso=curso;
		this.tipo=tipo;
		this.horas=horas;
		
		Association.Fragmentar.link(curso,this);
	}
	
	@Override
	public String toString() {
		return "Fragmento [curso=" + curso.getNombre() + ", tipoVehiculo=" + tipo.getNombre() + ", horas=" + horas.doubleValue() + "]";
	}

	public void asignarFragmento(Curso curso){
		this.curso=curso;
		Association.Fragmentar.link(curso,this);
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
	
	

}
