package br.com.devcave.jpa.repository;

import br.com.devcave.jpa.domain.Purchase;
import br.com.devcave.jpa.domain.response.IUserHistoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query("select u.name AS name, sum(p.price) AS total from User u join u.purchaseList c join c.productList p group by u.name")
    List<IUserHistoryResponse> findHistory();
}
