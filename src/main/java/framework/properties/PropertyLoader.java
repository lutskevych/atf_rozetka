package framework.properties;

import org.aeonbits.owner.ConfigFactory;

public class PropertyLoader {
    public static InitialProperties initProperties = ConfigFactory.create(InitialProperties.class);
    public static DataBaseProperties dbProperties = ConfigFactory.create(DataBaseProperties.class);
}
