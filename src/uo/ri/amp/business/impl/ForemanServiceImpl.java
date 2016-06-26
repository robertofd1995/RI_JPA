package uo.ri.amp.business.impl;

import java.util.List;

import uo.ri.amp.business.ForemanService;
import uo.ri.amp.business.impl.foreman.AddAveria;
import uo.ri.amp.business.impl.foreman.AsignarAveria;
import uo.ri.amp.business.impl.foreman.DeleteAveria;
import uo.ri.amp.business.impl.foreman.FindAveriaById;
import uo.ri.amp.business.impl.foreman.FindAveriaByMatricula;
import uo.ri.amp.business.impl.foreman.FindExpertos;
import uo.ri.amp.business.impl.foreman.FindVehiculoById;
import uo.ri.amp.business.impl.foreman.ListarAverias;
import uo.ri.amp.business.impl.foreman.UpdateAveria;
import uo.ri.amp.model.Averia;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.Vehiculo;
import uo.ri.business.impl.CommandExecutor;
import uo.ri.model.exception.BusinessException;

public class ForemanServiceImpl implements ForemanService {

	CommandExecutor executor=new CommandExecutor();

	@Override
	public void newAveria(Averia averia) throws BusinessException {
		executor.execute(new AddAveria( averia ));
	}

	@Override
	public void updateAveria(Averia averia) throws BusinessException {
		executor.execute(new UpdateAveria( averia ));
	}

	@Override
	public void deleteAveria(Long idAveria) throws BusinessException {
		executor.execute(new DeleteAveria(idAveria));
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<Averia> findAllAveria() throws BusinessException {
		return (List<Averia>) executor.execute(new FindAllAveria());
	}*/

	@Override
	public Averia findAveriaById(Long id) throws BusinessException {
		return (Averia) executor.execute(new FindAveriaById(id));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Averia> findAveriasByMatricula(String matricula) throws BusinessException {
		return  (List<Averia>) executor.execute(new FindAveriaByMatricula(matricula));
	}

	@Override
	public Vehiculo findVehiculoById(long vehiculo_id) throws BusinessException {
		
		return (Vehiculo) executor.execute(new FindVehiculoById(vehiculo_id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Averia> listarAverias() throws BusinessException {
		
		return (List<Averia>) executor.execute(new ListarAverias());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mecanico> listarExpertos(TipoVehiculo tipo) throws BusinessException {
		return (List<Mecanico>) executor.execute(new FindExpertos(tipo));
	}

	@Override
	public void asignarAveria(Averia averia, long mecanico_id) throws BusinessException {
		executor.execute(new AsignarAveria(averia,mecanico_id));
		
	}
	
	
}
