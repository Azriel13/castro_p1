package dev.castro.p1.UtilitiesTests;

import dev.castro.p1.Utilities.Logger;
import jdk.jfr.internal.LogLevel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class LoggerTest {
    @Disabled
    @Test
    void info_log_test(){
        Logger.log("Hello", LogLevel.INFO);
        Logger.log("Wassup", LogLevel.DEBUG);
    }
}
