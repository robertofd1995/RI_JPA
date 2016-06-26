package uo.ri.amp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import uo.ri.amp.business.impl.Constantes;
import uo.ri.amp.model.types.AsistenciaKey;
import uo.ri.amp.model.types.AsistenciaStatus;

@Entity
@IdClass(AsistenciaKey.class)
@Table(name="TAsistencias")
public class Asistencia {

	private Double pasistencia;
	
	@Id @ManyToOne private Curso curso;
	@Id @ManyToOne private Mecanico mecanico;
	
	@Id @Temporal(TemporalType.DATE) private Date fInicio;
	
	@Temporal(TemporalType.DATE)
	private Date fFinal; 
	
	@Enumerated(EnumType.STRING)
	private AsistenciaStatus status;
	
	public Asistencia() {}
	
	public Asistencia(Mecanico mecanico ,Curso curso , Date finicio){

		Association.Asistir.link(curso,this,mecanico);
		this.fInicio=finicio;
	}

	public Asistencia(Mecanico mecanico , Curso curso , Date finicio, Date ffinal,double porcentaje,AsistenciaStatus status){

		this(mecanico, curso, finicio);

		setfFinal(ffinal);
		setStatus(status);
		setAsistencia(porcentaje);
	}
	
	
	@Override
	public String toString() {
		return "Asistencia [asistencia=" + pasistencia.doubleValue() + ", curso=" + curso.getNombre() + ", mecanico=" + mecanico.getDni() + ", fInicio="
				+ fInicio + ", fFinal=" + fFinal + ", status=" + status + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Asistencia that = (Asistencia) o;

		if (!getCurso().equals(that.getCurso())) return false;
		if (!getMecanico().equals(that.getMecanico())) return false;
		return getfInicio().equals(that.getfInicio());

	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((fInicio == null) ? 0 : fInicio.hashCode());
		result = prime * result + ((mecanico == null) ? 0 : mecanico.hashCode());
		return result;
	}

	//GETTERS AND SETTERS
	public Double getAsistencia() {
		return pasistencia;
	}

	public void setAsistencia(Double asistencia) {

		if (asistencia<=Constantes.MINIMO_ASISTENCIA) {
			this.status=AsistenciaStatus.NO_APTO;
		}
		this.pasistencia = asistencia;
	}

	public Curso getCurso() {
		return curso;
	}

	protected void _setCurso(Curso curso) {
		this.curso = curso;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	protected void _setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	public Date getfInicio() {
		return new Date(fInicio.getTime());
	}
	
	protected Date _getfInicio() {
		return fInicio;
	}

    protected void _setfInicio(Date fInicio) {
		this.fInicio = fInicio;
	}

	public Date getfFinal() {
		return fFinal;
	}

	public void setfFinal(Date fFinal) {
		this.fFinal = fFinal;
	}

	public AsistenciaStatus getStatus() {
		return status;
	}

	public void setStatus(AsistenciaStatus status) {
		this.status = status;
	}
	
}
