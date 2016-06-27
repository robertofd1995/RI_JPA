package uo.ri.amp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import alb.util.date.DateUtil;
import uo.ri.amp.model.types.AsistenciaStatus;
import uo.ri.amp.model.types.CertificadoKey;
import uo.ri.model.exception.BusinessException;

@Entity
@IdClass(CertificadoKey.class)
@Table(name="TCertificados")
public class Certificado {
	
	
	@ManyToOne
	@Id private Mecanico mecanico;
	
	@ManyToOne
	@Id private TipoVehiculo tipo;
	
	@Temporal(value = TemporalType.DATE)
	private Date fecha;
	
	public Certificado() {}
	

	/**
	 * Constructor en la fecha del certificado sera la de hoy
	 * @param mecanico
	 * @param tipo
	 * @throws BusinessException
	 */
	public Certificado(Mecanico mecanico,TipoVehiculo tipo) throws BusinessException{

		this.fecha=DateUtil.today();
		
		if (esValido(mecanico, tipo)) {
			Association.Certificar.link(this,mecanico,tipo);
		}
	}
	
	public Certificado(Mecanico mecanico,TipoVehiculo tipo,Date fecha) throws BusinessException{
		this(mecanico, tipo);
		this.fecha=fecha;
	}
	

	/**
	 * En caso de que el mecanico cumpla con los requisitos para obtener el certificado se retorna true
	 * , en caso contrario false
	 * @param mecanico
	 * @param tipo
	 * @return true si es valido , false en caso contrario
	 */
	public boolean esValido(Mecanico mecanico , TipoVehiculo tipo){
		
		double horas=tipo.getHorasExperto();

		//Comprobamos que el mecanico ya haya obtenido el certificado
		for (Certificado cert:mecanico._getCertificados()) {
				if(cert.getTipo().equals(tipo)) {
					return true;
				}
		}
		
		//Comprueba la cantidad de horas que ha trabajado sobre un vehiculo en la que ha sido valido
		for (Asistencia asistencia : mecanico.getAsitencias()) { 
			
			if (asistencia.getStatus()==AsistenciaStatus.APTO ) {
			
				for (Fragmento fragmento : asistencia.getCurso()._getFragmentos()) {
					if (fragmento.getTipoVehiculo()==tipo) {
						horas-=fragmento.getHoras();
					}
				}	
			}	
		}
		
		if (horas<=0)
			return true;
		else
			return false;
	}
	
	
	@Override
	public String toString() {
		return "Certificado [mecanico= " + mecanico + ", tipo= " + tipo + ", fecha= " + fecha + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mecanico == null) ? 0 : mecanico.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Certificado other = (Certificado) obj;
		if (mecanico == null)
		{
			if (other.mecanico != null)
				return false;
		} else if (!mecanico.equals(other.mecanico))
			return false;
		if (tipo == null)
		{
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	//GETTERS AND SETTERS
	public Mecanico getMecanico() {
		return mecanico;
	}

	protected void _setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	public TipoVehiculo getTipo() {
		return tipo;
	}

	protected  void _setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	

}
