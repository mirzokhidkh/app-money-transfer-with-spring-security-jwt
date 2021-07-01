package uz.mk.appmoneytransferwithspringsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.mk.appmoneytransferwithspringsecurity.entity.Card;
import uz.mk.appmoneytransferwithspringsecurity.entity.Income;
import uz.mk.appmoneytransferwithspringsecurity.entity.Outcome;
//import uz.mk.appmoneytransferwithspringsecurity.entity.User;
import uz.mk.appmoneytransferwithspringsecurity.payload.ApiResponse;
import uz.mk.appmoneytransferwithspringsecurity.payload.CardDto;
import uz.mk.appmoneytransferwithspringsecurity.repository.CardRepository;
import uz.mk.appmoneytransferwithspringsecurity.repository.IncomeRepository;
import uz.mk.appmoneytransferwithspringsecurity.repository.OutcomeRepository;
//import uz.mk.appmoneytransferwithspringsecurity.repository.UserRepository;

import java.util.Optional;

@Service
public class CardService {
//    @Autowired
//    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    OutcomeRepository outcomeRepository;


    //CREATE CARD TO CARD TABLE
    public ApiResponse add(CardDto cardDto) {
        boolean existsByNumber = cardRepository.existsByNumber(cardDto.getNumber());
        if (existsByNumber) {
            return new ApiResponse("Card with such a number already exists", false);
        }
        Card card = new Card();
        card.setUsername(cardDto.getUsername());
        card.setNumber(cardDto.getNumber());
        card.setExpiredDate(cardDto.getExpiredDate());

//        Optional<User> optionalUser = userRepository.findById(cardDto.getUserId());
//        if (optionalUser.isEmpty()) {
//            return new ApiResponse("User not found", false);
//        }

//        card.setUser(optionalUser.get());
        Card savedCard = cardRepository.save(card);
        return new ApiResponse("Saved", true, savedCard);
    }

    //DEPOSIT MONEY ON CARD
    public ApiResponse deposit(Integer cardId, Double amount) {
        //        User currentUser =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean existsById = cardRepository.existsById(cardId);
        if (!existsById) {
            return new ApiResponse("Card with such a id not found", false);
        }

        Double money = cardRepository.getMoney(cardId);
        cardRepository.editMoney(money + amount, cardId);

        Income income = new Income();
        income.setAmount(amount);
        income.setToCard(cardRepository.getCardById(cardId));
        Income savedIncome = incomeRepository.save(income);

        return new ApiResponse("Added", true, savedIncome);
    }

    //WITHDRAW MONEY FROM CARD
    public ApiResponse withdraw(Integer cardId, Double amount) {
//        User currentUser =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean existsById = cardRepository.existsById(cardId);
        if (!existsById) {
            return new ApiResponse("Card with such a id not found", false);
        }

        Double commissionAmount = amount * 0.01;
        Double amountWithCommission = amount * 1.01;
        Double money = cardRepository.getMoney(cardId);
        if (money < amountWithCommission) {
            return new ApiResponse("There is not enough money to transfer to another card", false);
        }
        cardRepository.editMoney(money - amountWithCommission, cardId);

        Outcome outcome = new Outcome();
        outcome.setAmount(amount);
        outcome.setCommissionAmount(commissionAmount);
        outcome.setFromCard(cardRepository.getCardById(cardId));
        Outcome savedOutcome = outcomeRepository.save(outcome);

        return new ApiResponse("Money was withdrawn from the plastic card", true, savedOutcome);
    }


    //TRANSFER MONEY TO ANOTHER CARD
    public ApiResponse transfer(Integer senderCardId, Integer recipientCarId, Double amount) {
        //        User currentUser =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean existsById = cardRepository.existsById(senderCardId);
        if (!existsById) {
            return new ApiResponse("Card with such a id not found", false);
        }

        boolean existsById1 = cardRepository.existsById(recipientCarId);
        if (!existsById1) {
            return new ApiResponse("Recipient card not found", false);
        }

        Double commissionAmount = amount * 0.01;
        Double amountWithCommission = amount * 1.01;
        Double money = cardRepository.getMoney(senderCardId);
        if (money < amountWithCommission) {
            return new ApiResponse("There is not enough money to transfer to another card", false);
        }

        cardRepository.editMoney(money - amountWithCommission, senderCardId);
        Double recipientMoney = cardRepository.getMoney(recipientCarId);
        cardRepository.editMoney(recipientMoney + amount, recipientCarId);

        Income income = new Income();
        income.setFromCard(cardRepository.getCardById(senderCardId));
        income.setToCard(cardRepository.getCardById(recipientCarId));
        income.setAmount(amount);
        incomeRepository.save(income);

        Outcome outcome = new Outcome();
        outcome.setAmount(amount);
        outcome.setCommissionAmount(commissionAmount);
        outcome.setFromCard(cardRepository.getCardById(senderCardId));
        outcome.setToCard(cardRepository.getCardById(recipientCarId));
        Outcome savedOutcome = outcomeRepository.save(outcome);

        return new ApiResponse("Transfer successfully executed ", true, savedOutcome);

    }


}
