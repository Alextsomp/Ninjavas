//package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Inserter {
    public void createAndInsertCitiesAndDistances(DB db) {
        // SQL query για τη δημιουργία του πίνακα cities
        String createCitiesTableSQL = """
            CREATE TABLE IF NOT EXISTS cities (
                id INTEGER PRIMARY KEY,
                name VARCHAR(100) NOT NULL
            );
        """;

        // SQL query για τη δημιουργία του πίνακα distances
        String createDistancesTableSQL = """
            CREATE TABLE IF NOT EXISTS distances (
                from_city INTEGER NOT NULL,
                to_city INTEGER NOT NULL,
                distance INTEGER NOT NULL,
                PRIMARY KEY (from_city, to_city)
            );
        """;

        // SQL query για την εισαγωγή των δεδομένων των πόλεων
        String insertCitiesSQL = """
            INSERT OR IGNORE INTO cities (id, name) VALUES
                (0, 'Athens'),
                (1, 'Thessaloniki'),
                (2, 'Patras'),
                (3, 'Ioannina'),
                (4, 'Tirana'),
                (5, 'Skopje'),
                (6, 'Sofia'),
                (7, 'Podgorica'),
                (8, 'Pristina'),
                (9, 'Bucharest'),
                (10, 'Belgrade'),
                (11, 'Sarajevo'),
                (12, 'Zagreb'),
                (13, 'Chisinau'),
                (14, 'Ljubljana');
        """;

        // SQL query για την εισαγωγή των δεδομένων των αποστάσεων
        String insertDistancesSQL = """
            INSERT OR IGNORE INTO distances (from_city, to_city, distance) VALUES
                (0, 0, 0),
                (1, 1, 0),
                (2, 2, 0),
                (3, 3, 0),
                (4, 4, 0),
                (5, 5, 0),
                (6, 6, 0),
                (7, 7, 0),
                (8, 8, 0),
                (9, 9, 0),
                (10, 10, 0),
                (11, 11, 0),
                (12, 12, 0),
                (13, 13, 0),
                (14, 14, 0),
                (1, 0, 494),
                (2, 0, 213),
                (3, 0, 412),
                (4, 0, 678),
                (5, 0, 698),
                (6, 0, 798),
                (7, 0, 862),
                (8, 0, 787),
                (9, 0, 1184),
                (10, 0, 1095),
                (11, 0, 1061),
                (12, 0, 1485),
                (13, 0, 1651),
                (14, 0, 1623),
                (2, 1, 468),
                (3, 1, 261),
                (4, 1, 397),
                (5, 1, 234),
                (6, 1, 292),
                (7, 1, 550),
                (8, 1, 323),
                (9, 1, 679),
                (10, 1, 631),
                (11, 1, 820),
                (12, 1, 1023),
                (13, 1, 1140),
                (14, 1, 1162),
                (3, 2, 222),
                (4, 2, 538),
                (5, 2, 622),
                (6, 2, 756),
                (7, 2, 672),
                (8, 2, 744),
                (9, 2, 1143),
                (10, 2, 1057),
                (11, 2, 903),
                (12, 2, 1300),
                (13, 2, 1604),
                (14, 2, 1438),
                (4, 3, 316),
                (5, 3, 415),
                (6, 3, 553),
                (7, 3, 449),
                (8, 3, 504),
                (9, 3, 940),
                (10, 3, 850),
                (11, 3, 680),
                (12, 3, 1164),
                (13, 3, 1407),
                (5, 4, 223),
                (6, 4, 473),
                (7, 4, 158),
                (8, 4, 257),
                (9, 4, 858),
                (10, 4, 603),
                (11, 4, 389),
                (12, 4, 786),
                (13, 4, 1330),
                (14, 4, 924),
                (6, 5, 236),
                (7, 5, 330),
                (8, 5, 91),
                (9, 5, 629),
                (10, 5, 432),
                (11, 5, 507),
                (12, 5, 825),
                (13, 5, 1100),
                (14, 5, 963),
                (7, 6, 520),
                (8, 6, 333),
                (9, 6, 383),
                (10, 6, 392),
                (11, 6, 581),
                (12, 6, 785),
                (13, 6, 852),
                (14, 6, 924),
                (8, 7, 254),
                (9, 7, 822),
                (10, 7, 447),
                (11, 7, 230),
                (12, 7, 627),
                (13, 7, 1314),
                (14, 7, 773),
                (9, 8, 721),
                (10, 8, 459),
                (11, 8, 429),
                (12, 8, 883),
                (13, 8, 1193),
                (14, 8, 1024),
                (10, 9, 597),
                (11, 9, 877),
                (12, 9, 990),
                (13, 9, 474),
                (14, 9, 1128),
                (11, 10, 301),
                (12, 10, 396),
                (13, 10, 960),
                (14, 10, 534),
                (12, 11, 402),
                (13, 11, 1249),
                (14, 11, 540),
                (13, 12, 1335),
                (14, 12, 141),
                (14, 13, 1401);
        """;

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement()) {

            // Δημιουργία του πίνακα cities αν δεν υπάρχει
            stmt.executeUpdate(createCitiesTableSQL);
            System.out.println("Cities table created successfully.");

            // Δημιουργία του πίνακα distances αν δεν υπάρχει
            stmt.executeUpdate(createDistancesTableSQL);
            System.out.println("Distances table created successfully.");

            // Εισαγωγή των δεδομένων των πόλεων
            stmt.executeUpdate(insertCitiesSQL);
            System.out.println("Cities inserted successfully.");

            // Εισαγωγή των δεδομένων των αποστάσεων
            stmt.executeUpdate(insertDistancesSQL);
            System.out.println("Distances inserted successfully.");

        } catch (SQLException e) {
            System.err.println("Error creating or inserting data: " + e.getMessage());
        }
    }
}
