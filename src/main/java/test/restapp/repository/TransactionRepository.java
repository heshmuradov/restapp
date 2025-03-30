package test.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.restapp.entity.Transaction;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByUserId(Integer userId);

    /**
     * Get transactions created between two dates
     * @param startDate
     * @param endDate
     * @return
     */
    List<Transaction> findByCreatedAtBetween(Date startDate, Date endDate);

    /**
     * Get transactions created after a date
     * @param startDate
     * @return
     */
    List<Transaction> findByCreatedAtAfter(Date startDate);
}
