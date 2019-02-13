package es.iti.formacion.tecnogochi.portal.bean.request;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.event.ValueChangeEvent;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 *
 * @author Salvador Morera i Soler <smorera@iti.es>
 */
@ManagedBean
@RequestScoped
public class LifeCycleBean implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(LifeCycleBean.class);

    private HtmlInputText inputComponent;
    private String inputValue;
    private HtmlOutputText outputComponent;
    private String outputValue;

    // Constructors -------------------------------------------------------------------------------

    public LifeCycleBean() {
        log("constructed");
    }
    
    @PostConstruct
    public void init() {
        log("initialization");
    }

    // Actions ------------------------------------------------------------------------------------

    public void inputChanged(ValueChangeEvent event) {
        log(event.getOldValue() + " to " + event.getNewValue());
    }

    public void action() {
        outputValue = inputValue;
        log("succes");
    }

    // Getters/setters ----------------------------------------------------------------------------

    public HtmlInputText getInputComponent() {
        log(inputComponent);
        return inputComponent;
    }

    public void setInputComponent(HtmlInputText inputComponent) {
        log(inputComponent);
        this.inputComponent = inputComponent;
    }

    public String getInputValue() {
        log(inputValue);
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        log(inputValue);
        this.inputValue = inputValue;
    }

    public HtmlOutputText getOutputComponent() {
        log(outputComponent);
        return outputComponent;
    }

    public void setOutputComponent(HtmlOutputText outputComponent) {
        log(outputComponent);
        this.outputComponent = outputComponent;
    }

    public String getOutputValue() {
        log(outputValue);
        return outputValue;
    }

    // Helpers ------------------------------------------------------------------------------------

    private void log(Object object) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println("MyBean " + methodName + ": " + object);
    }
}
