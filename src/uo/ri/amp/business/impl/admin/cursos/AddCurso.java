package uo.ri.amp.business.impl.admin.cursos;

import java.util.HashMap;
import java.util.List;

import uo.ri.amp.model.Curso;
import uo.ri.amp.model.Fragmento;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.persistence.TipoVehiculoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class AddCurso implements Command {

	private Curso curso;
	private List<HashMap<String, Object>> fragmentos;

	public AddCurso(Curso curso,List<HashMap<String,Object>> fragmentos) {
		this.curso=curso;
		this.fragmentos=fragmentos;
	}

	@Override
	public Curso execute() throws BusinessException {
				
		Jpa.getManager().persist(curso);
		
		for (HashMap<String, Object> fragmento : fragmentos) {
			
			TipoVehiculo tipoVehiculo=TipoVehiculoFinder.findByNombre((String) fragmento.get("tipo"));
			if (tipoVehiculo==null) {
				throw new BusinessException("No se ha encontrado el tipo de vehiculo" + fragmento.get("tipo"));
			}
			Fragmento f=new Fragmento(curso, tipoVehiculo, (Double) fragmento.get("porcentaje") );
			Jpa.getManager().persist(f);
			
		}
		
		return curso;
	}

}
