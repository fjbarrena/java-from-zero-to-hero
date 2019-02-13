package es.iti.curso.osgi.math.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import es.iti.curso.osgi.math.service.impl.MathService;
import es.iti.curso.osgi.math.service.impl.MathServiceImpl;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("Starting MathematicalService");
		
		registryServices();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("Stopping MathematicalService");
	}
	
	private void registryServices() {
		System.out.println("Registring MathService Service");
		MathService instancia = new MathServiceImpl();
		getContext().registerService(MathService.class, instancia, null);
	}

}
