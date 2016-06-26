package uo.ri.amp.model.types;

import java.io.Serializable;
import java.util.Date;

public class AsistenciaKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Long curso;
	Long mecanico;
	Date fInicio;
	
	public AsistenciaKey(){}
	
	public AsistenciaKey(Long curso,Long mecanico , Date fInicio){
		this.curso=curso;
		this.mecanico=mecanico;
		this.fInicio=fInicio;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((fInicio == null) ? 0 : fInicio.hashCode());
		result = prime * result + ((mecanico == null) ? 0 : mecanico.hashCode());
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
		AsistenciaKey other = (AsistenciaKey) obj;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (fInicio == null) {
			if (other.fInicio != null)
				return false;
		} else if (!fInicio.equals(other.fInicio))
			return false;
		if (mecanico == null) {
			if (other.mecanico != null)
				return false;
		} else if (!mecanico.equals(other.mecanico))
			return false;
		return true;
	}
	
	public Long getCurso() {
		return curso;
	}

	public Long getMecanico() {
		return mecanico;
	}

	public Date getfInicio() {
		return fInicio;
	}
	
	
	
	
	
	
	
	
	


}
