package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.MessageRegistrationException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;


@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }


    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message findByMessageId(Integer message_id) {
        return messageRepository.findByMessageId(message_id);
    }


    public Message createMessage(Message message) {
        if (message.getMessageText().isBlank()){
            throw new MessageRegistrationException("Message must not be blank.");
        }
        if (message.getMessageText().length() > 255){
            throw new MessageRegistrationException("Message must be less than 255 characters.");
        }
        Account account = accountRepository.findByaccountId(message.getPostedBy());
        if (account == null) {
            throw new MessageRegistrationException("No account found for the user posting the message.");
        }
        Integer accountdbId = account.getAccountId();
        Integer messagedbid = message.getPostedBy();
        if (!accountdbId.equals(messagedbid)){
            throw new MessageRegistrationException("Message must be posted by a valid user.");
        }

        return messageRepository.save(message);
    }

    public Boolean deleteMessage(Integer messageId) {
        Message message = messageRepository.findByMessageId(messageId);
        if (message != null) {
            messageRepository.delete(message);
            return true;
        }
        return false;
    }


    public Integer updateMessage(Integer message_id, String message_text) {
        Message message = messageRepository.findByMessageId(message_id);
        if (message == null || message_text == null){
            throw new MessageRegistrationException("Message id not found.");
        }

        if (message_text.isBlank() || message_text.isEmpty()){
            throw new MessageRegistrationException("Message must not be blank.");
        }
        if (message_text.length() > 255){
            throw new MessageRegistrationException("Message must be less than 255 characters.");
        }

        message.setMessageText(message_text);
        messageRepository.save(message);
        return 1;

    }

    public List<Message> getAllMessageByAccountId(Integer account_id) {
        return messageRepository.findAllByPostedBy(account_id);
    }

}
