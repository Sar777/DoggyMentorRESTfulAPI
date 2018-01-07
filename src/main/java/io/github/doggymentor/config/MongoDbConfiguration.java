package io.github.doggymentor.config;

import com.mongodb.Mongo;
import io.github.doggymentor.config.token.OAuth2AuthenticationReadConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoDbConfiguration extends AbstractMongoConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(MongoDbConfiguration.class);
    private static final String MONGO_DB_SERVER = "spring.data.mongodb.host";
    private static final String MONGO_DB_PORT = "spring.data.mongodb.port";
    private static final String MONGO_DB_NAME = "spring.data.mongodb.database";
    private static final String MONGO_DB_LOGON = "spring.data.mongodb.username";
    private static final String MONGO_DB_PASSWORD = "spring.data.mongodb.password";

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${" + MONGO_DB_SERVER + "}")
    private String mongoServer;
    @Value("${" + MONGO_DB_PORT + "}")
    private int mongoPort;
    @Value("${" + MONGO_DB_NAME + "}")
    private String mongoDBName;
    @Value("${" + MONGO_DB_LOGON + "}")
    private String mongoDbLogin;
    @Value("${" + MONGO_DB_PASSWORD + "}")
    private String mongoDbPassword;

    @Override
    protected String getDatabaseName() {
        return mongoDBName;
    }

    @Override
    @Bean
    public Mongo mongo() {
        return new Mongo(mongoServer, mongoPort);
    }

    @Override
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        if (!StringUtils.isEmpty(mongoDbLogin)) {
            LOG.info("Configuring mongoTemplate with credentials.");
            MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo(), mongoDBName, new UserCredentials(mongoDbLogin, mongoDbPassword));
            return new MongoTemplate(mongoDbFactory, mappingMongoConverter());
        } else {
            LOG.info("Configuring mongoTemplate without credentials.");
            MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo(), mongoDBName);
            return new MongoTemplate(mongoDbFactory, mappingMongoConverter());
        }
    }

    @Override
    @Bean
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
        OAuth2AuthenticationReadConverter converter = new OAuth2AuthenticationReadConverter();
        converterList.add(converter);
        return new CustomConversions(converterList);
    }

    private String getContextProperty(final String propertyKey) {
        return applicationContext.getEnvironment().getProperty(propertyKey);
    }
}