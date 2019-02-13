package es.iti.curso.osgi.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import es.iti.curso.osgi.math.service.impl.MathService;
import es.iti.curso.osgi.math.service.utils.MathUtils;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("MathematicalConsumer started");
		
		// Obtengo la referencia al servicio
		ServiceReference<MathService> serviceReference = 
			context.getServiceReference(MathService.class);
		
		// Obtengo la instancia del servicio
		MathService service = context.getService(serviceReference);
		
		System.out.println("[MathService] Sumando 4 + 5 = " + service.sum(4, 5));
		
		// Lo mismo con MathUtils
		System.out.println("[MathUtils] Restando 5 - 4 = " + MathUtils.minus(5, 4));
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("MathematicalConsumer stopped");
	}

}
