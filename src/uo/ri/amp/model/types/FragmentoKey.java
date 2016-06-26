package uo.ri.amp.model.types;

import java.io.Serializable;

public class FragmentoKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Long curso;
	Long tipo;
	
	public FragmentoKey() {}
	
	public FragmentoKey(Long curso,Long tipoVehiculo){
		this.curso=curso;
		this.tipo=tipoVehiculo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		FragmentoKey other = (FragmentoKey) obj;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
	

}
