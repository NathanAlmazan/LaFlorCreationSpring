package com.nathanael.florcreation.users.mappers;

import com.nathanael.florcreation.users.dtos.Client;
import com.nathanael.florcreation.users.dtos.Recipient;
import com.nathanael.florcreation.users.models.ClientTable;
import com.nathanael.florcreation.users.models.RecipientTable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersMapper {

    public Client clientTableToClient(ClientTable clientTable) {
        return new Client(clientTable);
    }

    public List<Client> clientTableToClientList(List<ClientTable> clientTableList) {
        List<Client> clients = new ArrayList<>(clientTableList.size());

        for (ClientTable client : clientTableList)
            clients.add(clientTableToClient(client));

        return clients;
    }

    public Recipient recipientTableToRecipient(RecipientTable recipientTable) {
        return new Recipient(recipientTable);
    }

    public List<Recipient> recipientTableToRecipientList(List<RecipientTable> recipientTables) {
        List<Recipient> recipients = new ArrayList<>(recipientTables.size());

        for (RecipientTable recipient : recipientTables)
            recipients.add(recipientTableToRecipient(recipient));

        return recipients;
    }
}
