package es.iti.gochi.osgi.itemtype;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import es.iti.gochi.osgi.datasource.service.DatasourceService;
import es.iti.gochi.osgi.itemtype.service.ItemTypeService;
import es.iti.gochi.osgi.itemtype.service.ItemTypeServiceImpl;

public class ItemTypeActivator implements BundleActivator {

	private static BundleContext context;
	private static DatasourceService service;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		ItemTypeActivator.context = bundleContext;
		System.out.println("Starting ItemTypeActivator");
		
		// Obtengo la referencia al servicio
		ServiceReference<DatasourceService> serviceReference = 
			context.getServiceReference(DatasourceService.class);
		
		// Obtengo la instancia del servicio
		ItemTypeActivator.service = context.getService(serviceReference);
		
		registryServices();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		ItemTypeActivator.context = null;
		ItemTypeActivator.service = null;
		System.out.println("Stopping ItemTypeActivator");
	}
	
	private void registryServices() {
		System.out.println("Registring ItemTypeService Service");
		
		ItemTypeService instancia = new ItemTypeServiceImpl(ItemTypeActivator.service);
		getContext().registerService(ItemTypeService.class, instancia, null);
	}

}
