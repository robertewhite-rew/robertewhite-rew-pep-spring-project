package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;


@RestController
@RequestMapping
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer message_id){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.findByMessageId(message_id));
    }

    @PostMapping("login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.login(account.getUsername(), account.getPassword()));
    }

    @PostMapping("register")
    public ResponseEntity<Account> register(@RequestBody Account account){
        Account returnAcc = accountService.register(account);
        return ResponseEntity.ok(returnAcc);
    }

    @PostMapping("messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        Message returnMSG = messageService.createMessage(message);
        return ResponseEntity.ok(returnMSG);
    }
    
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable Integer message_id) {
        boolean deleted = messageService.deleteMessage(message_id);
        if (deleted) {
            return ResponseEntity.ok(1);
        } else {
            return ResponseEntity.ok().build(); 
        }
    }

    @PatchMapping("messages/{message_id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable Integer message_id, @RequestBody Map<String, String> body){
        String message_text = body.get("messageText");
        Integer rowsChanged = messageService.updateMessage(message_id, message_text);
        return ResponseEntity.ok(rowsChanged);
    }

     @GetMapping("accounts/{account_id}/messages")
     public ResponseEntity<List<Message>> getAllMessageByAccountId(@PathVariable Integer account_id){
        return ResponseEntity.ok(messageService.getAllMessageByAccountId(account_id));
     }


}
