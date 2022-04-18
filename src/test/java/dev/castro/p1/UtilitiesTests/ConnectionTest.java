package dev.castro.p1.UtilitiesTests;

import dev.castro.p1.Utilities.ConnectionsUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

    public class ConnectionTest {

        @Test
        void can_connect() {
            Connection conn = ConnectionsUtil.createConnection();
            Assertions.assertNotNull(conn);
        }

    }
