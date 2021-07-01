package uz.mk.appmoneytransferwithspringsecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mk.appmoneytransferwithspringsecurity.entity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Integer> {

}
