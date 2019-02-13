package es.iti.gochi.osgi.datasource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import es.iti.gochi.osgi.datasource.service.DatasourceImpl;
import es.iti.gochi.osgi.datasource.service.DatasourceService;

public class DatasourceActivator implements BundleActivator {

	private static BundleContext context;

	public static Map<Class, List<Object>> memoryDatabase;
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		DatasourceActivator.context = bundleContext;
		memoryDatabase = new HashMap<>();
		
		this.registryServices();
		
		System.out.println("DatasourceActivator started");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		DatasourceActivator.context = null;
		memoryDatabase = null;
		System.out.println("DatasourceActivator stopped");
	}
	
	private void registryServices() {
		System.out.println("Registring DatasourceService Service");
		DatasourceService instancia = new DatasourceImpl();
		getContext().registerService(DatasourceService.class, instancia, null);
	}

}
