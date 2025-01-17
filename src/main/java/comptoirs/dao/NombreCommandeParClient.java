package comptoirs.dao;

import java.math.BigDecimal;

public interface NombreCommandeParClient {
    Long getNumeroCommande();
    BigDecimal getPort();
    BigDecimal getPrixCommande();
}
