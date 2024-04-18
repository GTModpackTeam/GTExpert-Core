package gtexpert.test;

import gtexpert.api.util.GTELog;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GTESimpleTest {
    @Test
    public void helloWorld() {
        GTELog.logger.info("Hello world from GTESimpleTest!!!!");
    }
}
