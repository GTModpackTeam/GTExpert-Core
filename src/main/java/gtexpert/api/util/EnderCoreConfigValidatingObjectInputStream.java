package gtexpert.api.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.Arrays;
import java.util.List;

public class EnderCoreConfigValidatingObjectInputStream extends ObjectInputStream {

    private static final List<String> WHITELIST = Arrays
            .asList("java.util.HashMap", "java.lang.Integer", "java.lang.Number", "java.lang.Boolean");

    private static final Logger logger = LogManager.getLogger();
    private static final Marker securityMarker = MarkerManager.getMarker("SuspiciousPackets");

    public EnderCoreConfigValidatingObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        String name = desc.getName();
        if (!WHITELIST.contains(name)) {
            logger.warn(securityMarker, "Received packet containing disallowed class: " + name);
            throw new RuntimeException();
        }
        return super.resolveClass(desc);
    }
}
