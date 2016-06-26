package uo.ri.business.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class CommandExecutor
{
	public Object execute(Command command) throws BusinessException
	{
		EntityManager em = Jpa.createEntityManager();
		EntityTransaction trx = em.getTransaction();

		trx.begin();
		Object res = null;
		try
		{
			res=command.execute();
			trx.commit();
		} catch (RuntimeException r)
		{
			if(trx.isActive())
				trx.rollback();
			throw r;
		} catch (BusinessException e)
		{
			if(trx.isActive())
				trx.rollback();
			throw e;
		} finally
		{
			if(em.isOpen())
				em.close();
		}

		return res;
	}
}
