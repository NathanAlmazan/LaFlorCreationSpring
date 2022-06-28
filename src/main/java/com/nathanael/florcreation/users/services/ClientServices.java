package com.nathanael.florcreation.users.services;

import com.nathanael.florcreation.users.dtos.Client;
import com.nathanael.florcreation.users.mappers.UsersMapper;
import com.nathanael.florcreation.users.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServices {
    @Autowired private ClientRepository clientRepository;
    @Autowired private UsersMapper usersMapper;

    public List<Client> getAllClient() {
        return usersMapper.clientTableToClientList(
                clientRepository.findAllClient()
        );
    }

    public Client getClientByAccount(String accountUid) {
        return usersMapper.clientTableToClient(
                clientRepository.findByClientAccount(accountUid)
        );
    }

    public Client getClientById(long clientId) {
        return usersMapper.clientTableToClient(
                clientRepository.findByClientById(clientId)
        );
    }

    public Client addNewClient(Client newClient) {
        if (clientRepository.findByClientAccount(newClient.getAccountUid()) != null)
            return null;

        return usersMapper.clientTableToClient(
                clientRepository.createClient(
                        newClient.getClientName(),
                        newClient.getClientContact(),
                        newClient.getAccountUid()
                )
        );
    }
}
