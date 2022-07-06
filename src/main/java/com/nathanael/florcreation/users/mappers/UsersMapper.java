package com.nathanael.florcreation.users.mappers;

import com.nathanael.florcreation.users.dtos.Client;
import com.nathanael.florcreation.users.dtos.Recipient;
import com.nathanael.florcreation.users.dtos.Rider;
import com.nathanael.florcreation.users.models.ClientTable;
import com.nathanael.florcreation.users.models.RecipientTable;
import com.nathanael.florcreation.users.models.RiderTable;
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

    public Rider riderTableToRider(RiderTable riderTable) {
        return new Rider(riderTable);
    }

    public List<Rider> riderTableListToRiderList(List<RiderTable> riderTable) {
        List<Rider> riders = new ArrayList<>(riderTable.size());

        for (RiderTable rider : riderTable)
            riders.add(riderTableToRider(rider));

        return riders;
    }
}
