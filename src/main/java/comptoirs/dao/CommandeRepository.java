package comptoirs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import comptoirs.entity.Commande;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    @Query(
    """
    SELECT SUM(l.produit.prixUnitaire * l.quantite * (1 - l.commande.remise)) FROM Ligne l WHERE l.commande.numero = :numeroCommande
    """
    )
    BigDecimal montantArticles(Integer numeroCommande);

    // Requête N° Client
    @Query(
            """
            SELECT l.commande.numero as numeroCommande, 
            l.commande.port as port, 
            SUM(l.produit.prixUnitaire * l.quantite) * (1 - l.commande.remise) as prixCommande 
            From Ligne l 
            WHERE l.commande.client.code = :codeClient
            GROUP BY l.commande.numero
            """
    )
    List<NombreCommandeParClient> commandesParClient(String codeClient);
}
