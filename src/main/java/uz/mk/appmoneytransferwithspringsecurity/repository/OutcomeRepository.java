package uz.mk.appmoneytransferwithspringsecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mk.appmoneytransferwithspringsecurity.entity.Outcome;

@Repository
public interface OutcomeRepository extends JpaRepository<Outcome,Integer> {

}
