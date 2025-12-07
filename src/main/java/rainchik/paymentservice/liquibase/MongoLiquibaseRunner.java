package rainchik.paymentservice.liquibase;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;

import liquibase.ext.mongodb.database.MongoConnection;
import liquibase.ext.mongodb.database.MongoLiquibaseDatabase;
import liquibase.resource.ClassLoaderResourceAccessor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.stereotype.Component;

@Component
public class MongoLiquibaseRunner implements ApplicationRunner {

    @Value("${MONGO_URL}")
    private String url;

    @Value("${MONGO_USERNAME}")
    private String username;

    @Value("${MONGO_PASSWORD}")
    private String password;

    private String changeLog = "db/changelog/db.changelog-master.xml";

    @Override
    public void run(ApplicationArguments args) throws Exception {

        ConnectionString cs = new ConnectionString(url);

        MongoClient client = MongoClients.create(cs);

        MongoDatabase db = client.getDatabase(cs.getDatabase());


        MongoLiquibaseDatabase database = new MongoLiquibaseDatabase();
        MongoConnection mongoConnection = new MongoConnection();
        mongoConnection.setConnectionString(new ConnectionString(url));
        mongoConnection.setMongoClient(client);
        mongoConnection.setMongoDatabase(db);
        database.setConnection(mongoConnection);

        Liquibase liquibase = new Liquibase(
                changeLog,
                new ClassLoaderResourceAccessor(),
                database
        );

        liquibase.update(new Contexts(), new LabelExpression());
    }
}
