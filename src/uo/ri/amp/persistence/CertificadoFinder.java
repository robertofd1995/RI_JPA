package uo.ri.amp.persistence;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import alb.util.date.DateUtil;
import uo.ri.amp.model.Certificado;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;



public class CertificadoFinder {
	
	public static HashSet<Certificado> generarNuevos() throws BusinessException{
		@SuppressWarnings("unchecked")
		List<Object[]> certi= Jpa.getManager().createNamedQuery("Certificado.generarNuevos").getResultList();
		
		HashSet<Certificado> certificados=new HashSet<Certificado>();
		
		
		for (Object[] certificado : certi) {

			Certificado certAux =new Certificado(MecanicoFinder.findById((Long)certificado[0]),
					TipoVehiculoFinder.findById((Long)certificado[1]), (Date)certificado[2]);

			if (certAux.getMecanico()!=null)
				certificados.add(certAux);
			
		}
		
		return certificados;
	}

	public static boolean existCertificado(Certificado certificado) {
		
		certificado.setFecha(DateUtil.today());
		
		Long count= Jpa.getManager().createNamedQuery("Certificado.exist",Long.class)
				.setParameter(1, certificado).getSingleResult();
		
		return count==1;
	}

}
