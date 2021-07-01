package uz.mk.appmoneytransferwithspringsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mk.appmoneytransferwithspringsecurity.payload.ApiResponse;
import uz.mk.appmoneytransferwithspringsecurity.payload.CardDto;
import uz.mk.appmoneytransferwithspringsecurity.service.CardService;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping
    public HttpEntity<?> getReports() {
        return ResponseEntity.ok("CARD PAGE");
    }

    //CREATE CARD TO CARD TABLE
    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody CardDto cardDto) {
        ApiResponse response = cardService.add(cardDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    //DEPOSIT MONEY ON CARD
    @PostMapping("/deposit")
    public HttpEntity<?> deposit(@RequestParam Integer userId,
                                 @RequestParam Integer cardId,
                                 @RequestParam Double amount) {
        ApiResponse response = cardService.deposit(userId, cardId, amount);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    //WITHDRAW MONEY FROM CARD
    @PostMapping("/withdraw")
    public HttpEntity<?> withdraw(@RequestParam Integer userId,
                                  @RequestParam Integer cardId,
                                  @RequestParam Double amount) {
        ApiResponse response = cardService.withdraw(userId, cardId, amount);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    //TRANSFER MONEY TO ANOTHER CARD
    @PostMapping("/transfer")
    public HttpEntity<?> transfer(@RequestParam Integer senderId,
                                  @RequestParam Integer senderCardId,
                                  @RequestParam Integer recipientCardId,
                                  @RequestParam Double amount) {
        ApiResponse response = cardService.transfer(senderId, senderCardId, recipientCardId, amount);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }


}
