package uo.ri.amp.ui.admin;

import alb.util.menu.BaseMenu;
import uo.ri.amp.ui.admin.action.mecanicos.ListFormacionAction;
import uo.ri.amp.ui.admin.action.mecanicos.ListFormacionPorTipoAction;
import uo.ri.ui.admin.action.ListMechanicsAction;

public class MecanicosMenu extends BaseMenu {

	public MecanicosMenu() {
		menuOptions = new Object[][] { 
			{"Administrador > Gestión de mecánicos", null},
		
			{ "Listar mecanicos", 				ListMechanicsAction.class },
			{ "Listar formacion mecanico", 				ListFormacionAction.class },
			{ "Listar formacion mecanicos por tipo", 				ListFormacionPorTipoAction.class },
			
		};
	}

}
