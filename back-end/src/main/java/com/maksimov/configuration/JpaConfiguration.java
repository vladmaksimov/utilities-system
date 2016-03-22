package com.maksimov.configuration;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.AclAuthorizationStrategyImpl;
import org.springframework.security.acls.domain.ConsoleAuditLogger;
import org.springframework.security.acls.domain.ObjectIdentityRetrievalStrategyImpl;
import org.springframework.security.acls.domain.SpringCacheBasedAclCache;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.AclService;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Properties;

/**
 * Created on 20.03.2016.
 */
@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@ComponentScan({"com.maksimov.entity", "com.maksimov.persistence"})
@EnableJpaRepositories("com.maksimov.repository")
public class JpaConfiguration  {

    private static final String PROP_DATABASE_DRIVER = "db.driver";
    private static final String PROP_DATABASE_PASSWORD = "db.password";
    private static final String PROP_DATABASE_URL = "db.url";
    private static final String PROP_DATABASE_USERNAME = "db.username";
    private static final String PROP_HIBERNATE_DIALECT = "db.hibernate.dialect";
    private static final String PROP_HIBERNATE_SHOW_SQL = "db.hibernate.show_sql";
    private static final String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN = "db.entitymanager.packages.to.scan";
    private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "db.hibernate.hbm2ddl.auto";

    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty(PROP_DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROP_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROP_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROP_DATABASE_PASSWORD));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
        entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(PROP_ENTITYMANAGER_PACKAGES_TO_SCAN));

        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put(PROP_HIBERNATE_DIALECT, env.getRequiredProperty(PROP_HIBERNATE_DIALECT));
        properties.put(PROP_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));
        properties.put(PROP_HIBERNATE_HBM2DDL_AUTO, env.getRequiredProperty(PROP_HIBERNATE_HBM2DDL_AUTO));

        return properties;
    }

//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Autowired
//    private EntityManagerFactory entityManagerFactory;
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() throws NoSuchAlgorithmException {
//        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
//
//        secureRandom.setSeed(SecureRandom.getSeed(10));
//
//        return new BCryptPasswordEncoder(13, secureRandom);
//    }

//    @Bean
//    @Autowired
//    public MutableAclService aclService(@SuppressWarnings("SpringJavaAutowiringInspection") DataSource dataSource) {
//        ChatPermissionGrantingStrategy permissionGrantingStrategy = new ChatPermissionGrantingStrategy(new ConsoleAuditLogger());
//
//        AclAuthorizationStrategyImpl authorizationStrategy = new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority(RoleConstants.ROLE_ADMIN));
//
//        SpringCacheBasedAclCache aclCache = new SpringCacheBasedAclCache(new ConcurrentMapCache("aclCache"), permissionGrantingStrategy, authorizationStrategy);
//
//        BasicLookupStrategy lookupStrategy = new BasicLookupStrategy(dataSource, aclCache, authorizationStrategy, permissionGrantingStrategy);
//
//        JdbcMutableAclService aclService = new JdbcMutableAclService(dataSource, lookupStrategy, aclCache);
//
//        aclService.setClassIdentityQuery("SELECT currval(pg_get_serial_sequence('acl_class','id'))");
//        aclService.setSidIdentityQuery("SELECT currval(pg_get_serial_sequence('acl_sid','id'))");
//
//        return aclService;
//    }
//
//    @Bean
//    @Autowired
//    public ObjectIdentityRetrievalStrategyImpl objectIdentityRetrievalStrategy() {
//        return new ObjectIdentityRetrievalStrategyImpl();
//    }
//
//    @Override
//    public PlatformTransactionManager annotationDrivenTransactionManager() {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//
//    @Bean
//    @Autowired
//    public AclPermissionEvaluator aclPermissionEvaluator(AclService aclService) {
//        return new AclPermissionEvaluator(aclService);
//    }
//
//    @Bean
//    @Autowired
//    public TransactionTemplate transactionTemplate() {
//        return new TransactionTemplate(annotationDrivenTransactionManager());
//    }

}
