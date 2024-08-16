package model;

import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author artur
 */
@Data
public class ProductToUpdate {
    int code;
    BigDecimal quantity = BigDecimal.ZERO;
}
