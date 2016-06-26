package uo.ri.amp.business;

import java.util.List;

import uo.ri.amp.model.Averia;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.Vehiculo;
import uo.ri.model.exception.BusinessException;

public interface ForemanService {

	/**
	 * Añadira una nueva averia a la base de datos
	 * @param averia
	 * @throws BusinessException
	 */
	void newAveria(Averia averia) throws BusinessException;

	/**
	 * Actualizara el valor de la averia por el  pasado en el parametro
	 * @param averia
	 * @throws BusinessException
	 */
	void updateAveria(Averia averia) throws BusinessException;

	/**
	 * Borrara de la base de datos la averia que se corresponda con el id , en caso de que su estado sea abierta
	 * @param idAveria
	 * @throws BusinessException
	 */
	void deleteAveria(Long idAveria) throws BusinessException;

	/**
	 * Retorna la averia que tenga por id  el parametro.
	 * @param id
	 * @return Averia
	 * @throws BusinessException
	 */
	Averia findAveriaById(Long id) throws BusinessException;

	/**
	 * Retorna todas las averias asociadas a una matricula
	 * @param matricula
	 * @return List<Averia>
	 * @throws BusinessException
	 */
	List<Averia> findAveriasByMatricula(String matricula) throws BusinessException;

	/**
	 * Retornara el vehiculo que tenga por id el parametro.
	 * @param vehiculo_id
	 * @return Vehiculo
	 * @throws BusinessException
	 */
	Vehiculo findVehiculoById(long vehiculo_id) throws BusinessException;

	/**
	 * Retornara una lista con todas las averias que existan en la base de datos
	 * @return List<Averia>
	 * @throws BusinessException
	 */
	List<Averia> listarAverias() throws BusinessException;

	List<Mecanico> listarExpertos(TipoVehiculo tipo) throws BusinessException;

	void asignarAveria(Averia Averia, long mecanico_id) throws BusinessException;

	
}
