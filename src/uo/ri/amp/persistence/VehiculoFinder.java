package uo.ri.amp.persistence;

import uo.ri.amp.model.Vehiculo;
import uo.ri.persistence.util.Jpa;

public class VehiculoFinder {
	
	public static Vehiculo findById(Long idVehiculo) {
		Vehiculo vehiculo= Jpa.getManager().find(Vehiculo.class, idVehiculo);
		
		vehiculo.getAsignadas().size();
		
		return vehiculo;
	}

}
