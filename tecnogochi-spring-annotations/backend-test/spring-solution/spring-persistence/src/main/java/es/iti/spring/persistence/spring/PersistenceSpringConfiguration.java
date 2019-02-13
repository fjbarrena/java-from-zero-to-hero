package es.iti.spring.persistence.spring;

import com.jolbox.bonecp.BoneCPDataSource;
import es.iti.spring.persistence.context.IPersistenceContext;
import es.iti.spring.persistence.context.impl.DevelopmentPersistenceContext;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;

/**
 * Clase que configura el contexto de Spring
 * 
 * @author Francisco Javier Barrena
 */
@Configuration
@ComponentScan(basePackages = {"es.iti.spring.persistence.dao"})
@EnableLoadTimeWeaving
public class PersistenceSpringConfiguration {
   
    // Faltaba esto
    @Bean 
    public InstrumentationLoadTimeWeaver loadTimeWeaver() throws Throwable { 
        InstrumentationLoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver(); 
        return loadTimeWeaver; 
    }
    
    @Bean
    DataSource dataSource() {
        BoneCPDataSource ds = new BoneCPDataSource(); 
        
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/tamagochi?zeroDateTimeBehavior=convertToNull");        // set the JDBC url
        ds.setUsername("root");
        ds.setPassword("n0tiene");
        ds.setDriverClass("com.mysql.jdbc.Driver");
        
        ds.setMinConnectionsPerPartition(1);
        ds.setMaxConnectionsPerPartition(50);
        ds.setAcquireIncrement(5);
        ds.setPartitionCount(4);

        return ds;
    }
    
    /**
     * create the EntityManagerFactory
     */
    @DependsOn("dataSource")
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean 
                = new LocalContainerEntityManagerFactoryBean();
        
        entityManagerFactoryBean.setDataSource(dataSource);

        entityManagerFactoryBean.setJpaVendorAdapter(new EclipseLinkJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("es.iti.spring.model.entities");

        // Y faltaba esto
        Properties jpaProperties = new Properties();
        jpaProperties.put("eclipselink.weaving", "false");
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        
        entityManagerFactoryBean.setPersistenceUnitName("backend-persistence-unit");
        
        return entityManagerFactoryBean;
    }
    
    @DependsOn("entityManagerFactory")
    @Bean
    IPersistenceContext getPersistenceContext() {
        return new DevelopmentPersistenceContext();
    }

    
    
}
