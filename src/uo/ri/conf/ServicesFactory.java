package uo.ri.conf;

import uo.ri.amp.business.AdminService;
import uo.ri.amp.business.ForemanService;
import uo.ri.amp.business.impl.AdminServiceImpl;
import uo.ri.amp.business.impl.ForemanServiceImpl;
import uo.ri.business.MechanicService;

public class ServicesFactory {

	public static AdminService getAdminService() {
		return new AdminServiceImpl();
	}

	public static MechanicService getMechanicService() {
		return null;
	}

	public static ForemanService getForemanService() {
		return new ForemanServiceImpl();
	}
}
