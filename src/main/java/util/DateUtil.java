package util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author artur
 */
public class DateUtil {

    public static Timestamp convertStringToTimestamp(String dateTimeStr) {
        // Define o formato do timestamp com offset
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        // Converte a string para OffsetDateTime
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateTimeStr, formatter);

        // Converte OffsetDateTime para Instant e depois para Timestamp
        Instant instant = offsetDateTime.toInstant();
        return Timestamp.from(instant);
    }
}
