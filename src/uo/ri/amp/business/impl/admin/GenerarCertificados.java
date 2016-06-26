package uo.ri.amp.business.impl.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import uo.ri.amp.model.Certificado;
import uo.ri.amp.persistence.CertificadoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class GenerarCertificados implements Command {

	@Override
	public List<Certificado> execute() throws BusinessException {
		
		/*List<Mecanico> mecanicos = MecanicoFinder.findAll();
		
		TipoVehiculoFinder.findAll();*/
		
		HashSet<Certificado> certificados=CertificadoFinder.generarNuevos();
		
		for (Certificado certificado : certificados) {
			if (CertificadoFinder.existCertificado(certificado)==false) {
				Jpa.getManager().persist(certificado);
			}
			
		}
		
		return new ArrayList<Certificado>(certificados);
	}

}
