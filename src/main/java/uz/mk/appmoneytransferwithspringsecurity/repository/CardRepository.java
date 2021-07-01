package uz.mk.appmoneytransferwithspringsecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.mk.appmoneytransferwithspringsecurity.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    boolean existsByNumber(String number);

    Card getCardById(Integer id);


    //    @Query(value = "update card set money=?1 where id=?2 returning money", nativeQuery = true)
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update card set money=?1 where id=?2 ", nativeQuery = true)
    void editMoney(Double money, Integer cardId);

    @Query(value = "select money from card where id=?1", nativeQuery = true)
    Double getMoney(Integer cardId);
}
