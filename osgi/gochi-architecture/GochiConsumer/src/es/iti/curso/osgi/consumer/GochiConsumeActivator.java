package es.iti.curso.osgi.consumer;

import java.util.List;
import java.util.Random;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import es.iti.gochi.osgi.itemtype.model.ItemType;
import es.iti.gochi.osgi.itemtype.service.ItemTypeService;

public class GochiConsumeActivator implements BundleActivator {

	private static BundleContext context;

	
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		GochiConsumeActivator.context = bundleContext;
		
		// Obtengo la referencia al servicio
		ServiceReference<ItemTypeService> serviceReference = 
			context.getServiceReference(ItemTypeService.class);
		
		// Obtengo la instancia del servicio
		ItemTypeService service = context.getService(serviceReference);
		
		Random random = new Random();
		for(int i = 0; i < 5; i++) {
			ItemType item = new ItemType(random.nextInt()%100,
				String.valueOf(random.nextInt()));
			
			boolean res = service.addItem(item);
			
			if(!res) {
				System.out.println("Error añadiendo ItemType");
			}
		}
		
		List<Object> allItemTypes = service.getAll();
		
		for(Object o : allItemTypes) {
			System.out.println("ItemType: " + ((ItemType)o).getName());
		}
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		GochiConsumeActivator.context = null;
	}

}
