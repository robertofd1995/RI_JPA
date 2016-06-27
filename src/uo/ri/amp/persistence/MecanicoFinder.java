package uo.ri.amp.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class MecanicoFinder {

	public static Mecanico findById(Long id) {
	
		return Jpa.getManager().find(Mecanico.class, id);
	}

	public static List<Mecanico> findAll() {
	
		List<Mecanico> mecanicos=Jpa.getManager().createNamedQuery("Mecanico.findAll",Mecanico.class).getResultList();
		
		for (Mecanico mecanico : mecanicos) {
			mecanico.getAsitencias().size();
			mecanico.getCertificados().size();
		}
		
		return mecanicos;
	}

	public static Mecanico findByDni(String dni) throws BusinessException {
		
		Mecanico m=null;

		m= Jpa.getManager().createNamedQuery("Mecanico.findByDni",Mecanico.class).setParameter(1, dni)
				.getResultList().get(0);


		
		m.getAsitencias().size();
		
		return m;
	
	}

	public static List<Mecanico> findExpertosTipo(TipoVehiculo tipoVehiculo) throws BusinessException {
		
		List<Mecanico> mecanicos=null;
		
		
			mecanicos=Jpa.getManager().createNamedQuery("Mecanico.findExpertosTipo",Mecanico.class)
					.setParameter(1, tipoVehiculo).getResultList();
			
			if(mecanicos==null)
				throw new BusinessException("No se ha encontrado a ningun experto");

		return mecanicos;
		
	}

	public static HashMap<String, Object> findFormacion(Long mecanicoId) {
		
		Object[] aux= (Object[]) Jpa.getManager().createNamedQuery("Mecanico.formacionPorIdI")
				.setParameter(1, mecanicoId).getSingleResult();
		
		HashMap<String, Object> certi=new HashMap<>();
		
		certi.put("horasCurso", aux[0]);
		certi.put("horasAsistidas", aux[1]);
		
		@SuppressWarnings("unchecked")
		List<Object[]> aux2=  Jpa.getManager().createNamedQuery("Mecanico.formacionPorId2")
				.setParameter(1, mecanicoId).getResultList();
		
		List<HashMap<String, Object>> tipos=new ArrayList<HashMap<String,Object>>();
		
		for (Object[] tv : aux2) {
			HashMap<String, Object> tipo=new HashMap<String,Object>();
			tipo.put("nombre", tv[0]);
			tipo.put("horas", tv[1]);
			tipos.add(tipo);
		}
		
		certi.put("tipos", tipos);
		
		return certi;
	}
	

	public static List<Object[]> findFormacionPorTipo(Long tipo) {
	
			
		@SuppressWarnings("unchecked")
		List<Object[]> aux=  Jpa.getManager().createNamedQuery("Mecanico.formacionPorTipo")
				.setParameter(1, tipo).getResultList();

		return aux;
	}

}
