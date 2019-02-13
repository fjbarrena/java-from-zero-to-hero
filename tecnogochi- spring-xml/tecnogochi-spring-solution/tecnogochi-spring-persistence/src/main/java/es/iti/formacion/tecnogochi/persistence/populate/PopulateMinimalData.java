/**
 * 27-feb-2015 - PopulateMinimalData.java © Instituto Tecnológico de Informática, All Rights Reserved
 */
package es.iti.formacion.tecnogochi.persistence.populate;

import es.iti.formacion.tecnogochi.model.GochiType;
import es.iti.formacion.tecnogochi.model.Item;
import es.iti.formacion.tecnogochi.model.ItemType;
import es.iti.formacion.tecnogochi.persistence.dao.GochiTypeDAO;
import es.iti.formacion.tecnogochi.persistence.dao.ItemDAO;
import es.iti.formacion.tecnogochi.persistence.dao.ItemTypeDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Francisco Javier Barrena (twitter: @DogDeveloper)
 */
public class PopulateMinimalData {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PopulateMinimalData.class);

    public static void populateGochiTypes() {
        LOGGER.info("populateGochiTypes");
        GochiTypeDAO gochiTypeDao = new GochiTypeDAO();
        if (gochiTypeDao.getGochiTypeCount() == 0) {
            List<String> gochiTypeNames = new ArrayList<>();
            gochiTypeNames.add("HOMER");
            gochiTypeNames.add("MARGE");
            gochiTypeNames.add("KRUSTY");
            gochiTypeNames.add("RALPH");
            gochiTypeNames.add("FLANDERS");
            gochiTypeNames.add("APU");

            for (String name : gochiTypeNames) {
                // Ponemos el try local al bucle para que si falla la inserción de 
                // un elemento no impida que se intenten guardar los elementos que
                // quedan
                try {
                    GochiType typeToSave = new GochiType();
                    typeToSave.setName(name);

                    // Establecemos a lista vacía para evitar NullPointerExceptions no deseados
                    typeToSave.setMyGochisList(Collections.EMPTY_LIST);
                    gochiTypeDao.create(typeToSave);

                    LOGGER.info("Se ha guardado el GochiType " + name);
                } catch (Exception ex) {
                    LOGGER.error("No hemos podido guardar el GochiType " + name, ex);
                }
            }
        }
    }

    public static void populateItemTypes() {
        LOGGER.info("populateItemTypes");
        ItemTypeDAO itemTypeDao = new ItemTypeDAO();

        if (itemTypeDao.getItemTypeCount() == 0) {
            List<String> itemTypeNames = new ArrayList<>();
            itemTypeNames.add("COMER");
            itemTypeNames.add("BEBER");
            itemTypeNames.add("DIVERTIR");
            itemTypeNames.add("SOCIAL");

            for (String name : itemTypeNames) {
                // Ponemos el try local al bucle para que si falla la inserción de 
                // un elemento no impida que se intenten guardar los elementos que
                // quedan
                try {
                    ItemType typeToSave = new ItemType();
                    typeToSave.setName(name);

                    // Establecemos a lista vacía para evitar NullPointerExceptions no deseados
                    typeToSave.setItemList(Collections.EMPTY_LIST);
                    itemTypeDao.create(typeToSave);
                    LOGGER.info("Se ha guardado el ItemType " + name);
                } catch (Exception ex) {
                    LOGGER.error("No hemos podido guardar el ItemType " + name, ex);
                }
            }
        }
    }

    public static void populateItems() {
        LOGGER.info("populateItems");
        if (new ItemDAO().getItemCount() < 12) {
            ItemTypeDAO itemTypeDao = new ItemTypeDAO();

            ItemType comerType = itemTypeDao.findByName("COMER");

            Map<String, Integer> itemsPaComer = new HashMap<>(3);
            itemsPaComer.put("PIZZA", 20);
            itemsPaComer.put("BURGER", 25);
            itemsPaComer.put("BROCOLI", 10);

            populateItemsByTypeAndMap(comerType, itemsPaComer);

            ItemType beberType = itemTypeDao.findByName("BEBER");

            Map<String, Integer> itemsPaBeber = new HashMap<>(3);
            itemsPaBeber.put("AGUA", 20);
            itemsPaBeber.put("CERVEZA", 40);
            itemsPaBeber.put("WHISKY", 10);

            populateItemsByTypeAndMap(beberType, itemsPaBeber);

            ItemType divertirseType = itemTypeDao.findByName("DIVERTIR");

            Map<String, Integer> itemsPaDivertirse = new HashMap<>(3);
            itemsPaDivertirse.put("PELOTA", 20);
            itemsPaDivertirse.put("CONSOLA", 30);
            itemsPaDivertirse.put("TABLET", 40);

            populateItemsByTypeAndMap(divertirseType, itemsPaDivertirse);

            ItemType socialType = itemTypeDao.findByName("SOCIAL");

            Map<String, Integer> itemsPaSocializar = new HashMap<>(3);
            itemsPaSocializar.put("MOVIL", 50);
            itemsPaSocializar.put("FACEBOOK", 20);
            itemsPaSocializar.put("TWITTER", 30);

            populateItemsByTypeAndMap(socialType, itemsPaSocializar);
        }
    }

    private static void populateItemsByTypeAndMap(ItemType type, Map<String, Integer> itemsToSave) {
        LOGGER.info("populateItemsByTypeAndMap");
        ItemDAO itemDao = new ItemDAO();

        for (String key : itemsToSave.keySet()) {
            try {
                Item comidaToSave = new Item();

                comidaToSave.setEffect(itemsToSave.get(key));
                comidaToSave.setItemType(type);
                comidaToSave.setName(key);

                itemDao.create(comidaToSave);
                // Establecemos las relaciones que dan como resultado una lista
                // a una lista vacia para evitar NullPointerExceptions
                comidaToSave.setActionList(Collections.EMPTY_LIST);
                LOGGER.info("Se ha guardado el item " + key);
            } catch (Exception ex) {
                LOGGER.error("No hemos podido guardar el item " + key, ex);
            }
        }

    }
}
