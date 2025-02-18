//package com.tgl.cmd.appointments.model;
//
//import java.time.LocalDate;
//
//
//import java.util.Random;
//
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
//import org.hibernate.id.IdentifierGenerator;
//
///**
// * Custom ID generator for generating unique identifiers for entities.
// */
//@SuppressWarnings("serial")
//public class IdGenerator implements IdentifierGenerator {
//    
//    /**
//     * Generate a unique identifier.
//     * 
//     * @param session The session implementation.
//     * @param object  The object for which the identifier is being generated.
//     * @return A unique identifier based on the current date and a random number.
//     */
//    @Override
//    public Object generate(SharedSessionContractImplementor session, Object object) {
//        // Generate a unique ID based on the current date and a random number
//        return "APT-"+ new Random().nextLong(1000) + "-" + LocalDate.now().getYear();
//    }
//}



package com.tgl.cmd.appointments.model;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.time.Year;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Custom ID generator for generating unique appointment identifiers.
 * The ID format is: APT-<random-number>-<current-year>
 */
public class IdGenerator implements IdentifierGenerator {

    private static final int RANDOM_MIN = 2001; // Minimum value for the random number
    private static final int RANDOM_MAX = 9999; // Maximum value for the random number
    private static final String ID_PREFIX = "APT"; // Prefix for the ID

    /**
     * Generates a unique appointment ID.
     * Format: APT-<random-number>-<current-year>
     *
     * @param session the Hibernate session context (not used here).
     * @param object  the entity instance for which the ID is being generated.
     * @return a unique identifier as a {@link String}.
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        // Generate a random number within the defined range
        long randomNum = ThreadLocalRandom.current().nextLong(RANDOM_MIN, RANDOM_MAX + 1);

        // Append the current year
        int currentYear = Year.now().getValue();

        // Construct and return the unique ID
        return String.format("%s_%d_%d", ID_PREFIX, randomNum, currentYear); // APT_1234_2024
    }
}
