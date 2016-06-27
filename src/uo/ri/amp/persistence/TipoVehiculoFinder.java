package uo.ri.amp.persistence;

import java.util.List;

import uo.ri.amp.model.TipoVehiculo;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class TipoVehiculoFinder {
	
	public static TipoVehiculo findById(Long idTipoVehiculo) {
		TipoVehiculo tipo= Jpa.getManager().find(TipoVehiculo.class, idTipoVehiculo);
		return tipo;
	}
	
	
	public static TipoVehiculo findByNombre(String nombreTipo) throws BusinessException {
		TipoVehiculo tipo= (TipoVehiculo) Jpa.getManager().createNamedQuery("TipoVehiculo.findByNombre")
				.setParameter(1, nombreTipo).getSingleResult();
		
		if (tipo==null) {
			throw new BusinessException("El tipo de vehiculo introducido no existe ");
		}
		
		return tipo;
	}
	
	public static List<TipoVehiculo> findAll() {
		List<TipoVehiculo> tipos= Jpa.getManager().createNamedQuery("TipoVehiculo.findAll",TipoVehiculo.class)
				.getResultList();
		return tipos;
	}

}
