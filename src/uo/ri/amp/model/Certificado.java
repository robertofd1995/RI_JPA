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
	
	public Certificado(TipoVehiculo tipo){
		this.mecanico=null;
		this.tipo=tipo;
	}
	
	/**
	 * Constructor en la fecha del certificado sera la de hoy
	 * @param mecanico
	 * @param tipo
	 * @throws BusinessException
	 */
	public Certificado(Mecanico mecanico,TipoVehiculo tipo) throws BusinessException{
		this.mecanico=mecanico;
		this.tipo=tipo;
		this.fecha=DateUtil.today();
		
		if (esValido(mecanico, tipo)) {
			Association.Certificar.link(this,mecanico);
		}else{
			throw new BusinessException("El mecanico con dni ( "+mecanico.getDni()+" ) no cumple los requisitos para obtener un certificado ");
		}	
	}
	
	public Certificado(Mecanico mecanico,TipoVehiculo tipo,Date fecha) throws BusinessException{
		this.mecanico=mecanico;
		this.tipo=tipo;
		this.fecha=fecha; 
		
		
		Association.Certificar.link(this,mecanico);
		
		
	}
	
	
	public void generarCertificado(Mecanico mecanico) throws BusinessException{
		this.mecanico=mecanico;
		this.fecha=DateUtil.today();
		
		if (esValido(mecanico, tipo)) {
			Association.Certificar.link(this,mecanico);
		}else{
			throw new BusinessException("El mecanico con dni ( "+mecanico.getDni()+" ) no cumple los requisitos para obtener un certificado ");
		}
	}
	
	/**
	 * En caso de que el mecanico cumpla con los requisitos para obtener el certificado se retorna true , en caso contrario false
	 * @param mecanico
	 * @param tipo
	 * @return true si es valido , false en caso contrario
	 */
	public boolean esValido(Mecanico mecanico , TipoVehiculo tipo){
		
		double horas=tipo.getHorasExperto();
		
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

	//GETTERS AND SETTERS
	public Mecanico getMecanico() {
		return mecanico;
	}

	public void setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	public TipoVehiculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	

}
