package framework.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:init.properties")
public interface InitialProperties extends Config {
    String url();
    String environment();
    boolean windowMaximize();
    int defaultWait();
}
