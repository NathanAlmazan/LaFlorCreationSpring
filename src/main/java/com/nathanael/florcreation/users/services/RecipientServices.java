package com.nathanael.florcreation.users.services;

import com.nathanael.florcreation.users.dtos.Recipient;
import com.nathanael.florcreation.users.mappers.UsersMapper;
import com.nathanael.florcreation.users.repository.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipientServices {
    @Autowired private RecipientRepository recipientRepository;
    @Autowired private UsersMapper usersMapper;

    public Recipient getRecipientById(Long recipientId) {
        return usersMapper.recipientTableToRecipient(
                recipientRepository.findByRecipientId(recipientId)
        );
    }
}
