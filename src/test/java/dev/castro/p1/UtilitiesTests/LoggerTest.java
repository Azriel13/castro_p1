package dev.castro.p1.UtilitiesTests;

import dev.castro.p1.Utilities.Logger;
import jdk.jfr.internal.LogLevel;
import org.junit.jupiter.api.Test;

public class LoggerTest {
    @Test
    void info_log_test(){
        Logger.log("Hello", LogLevel.INFO);
        Logger.log("Wassup", LogLevel.DEBUG);
        Logger.log("Konnichiwa", LogLevel.ERROR);
        Logger.log("Ciao", LogLevel.WARN);
    }
}
