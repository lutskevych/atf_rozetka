package framework.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:db.properties")
public interface DataBaseProperties extends Config {
    @Key("db.url")
    String url();

    @Key("db.user")
    String user();

    @Key("db.password")
    String password();
}
