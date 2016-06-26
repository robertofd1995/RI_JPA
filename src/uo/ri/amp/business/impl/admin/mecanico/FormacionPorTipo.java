package uo.ri.amp.business.impl.admin.mecanico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.persistence.MecanicoFinder;
import uo.ri.amp.persistence.TipoVehiculoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class FormacionPorTipo implements Command {

	@Override
	public List<HashMap<String, Object>> execute() throws BusinessException {
		
		List<HashMap<String, Object>> formaciones = new ArrayList<HashMap<String,Object>>();
		
		
		List<TipoVehiculo> tipos = TipoVehiculoFinder.findAll();
		
		for (TipoVehiculo tipoVehiculo : tipos) {
			
			HashMap<String,Object> formacion=new HashMap<String,Object>();
			
			formacion.put("tipo", tipoVehiculo.getNombre());
			
			 List<Object[]> formacionesMecanicos = MecanicoFinder.findFormacionPorTipo(tipoVehiculo.getId());
			 
			 List<HashMap<String, Object>> mecanicos=new ArrayList<HashMap<String,Object>>();
			 for (Object[] objects : formacionesMecanicos) {
				HashMap<String, Object> mecanico=new HashMap<>();
				mecanico.put("nombre",(String)objects[0]);
				mecanico.put("horas", (Double) objects[1]);
				mecanicos.add(mecanico);
			}
			formacion.put("mecanicos", mecanicos);
			formaciones.add(formacion);
		}
		
		
		 
		 return formaciones;
	}

}
