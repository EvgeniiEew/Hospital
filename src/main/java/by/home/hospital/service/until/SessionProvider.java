//package by.home.hospital.service.until;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.Metadata;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Component;
//
//import lombok.extern.log4j.Log4j;
//
//@Log4j
//@Component
//public class SessionProvider implements ISessionProvider, InitializingBean, DisposableBean {
//
//    private StandardServiceRegistry registry;
//    private SessionFactory sessionFactory;
//
//    public SessionFactory getEntityManager() {
//        return sessionFactory;
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        try {
//            // Create registry
//            registry = new StandardServiceRegistryBuilder().configure().build();
//
//            // Create MetadataSources
//            MetadataSources sources = new MetadataSources(registry);
//
//            // Create Metadata
//            Metadata metadata = sources.getMetadataBuilder().build();
//
//            // Create SessionFactory
//            sessionFactory = metadata.getSessionFactoryBuilder().build();
//
//        } catch (Exception e) {
//            log.error("Something went wrong", e);
//            if (registry != null) {
//                StandardServiceRegistryBuilder.destroy(registry);
//            }
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        if (registry != null) {
//            StandardServiceRegistryBuilder.destroy(registry);
//        }
//
//    }
//}