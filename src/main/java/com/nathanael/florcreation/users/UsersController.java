package com.nathanael.florcreation.users;

import com.nathanael.florcreation.orders.dtos.Orders;
import com.nathanael.florcreation.users.dtos.Client;
import com.nathanael.florcreation.users.dtos.Recipient;
import com.nathanael.florcreation.users.dtos.Rider;
import com.nathanael.florcreation.users.services.ClientServices;
import com.nathanael.florcreation.users.services.RecipientServices;
import com.nathanael.florcreation.users.services.RiderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UsersController {
    @Autowired private ClientServices clientServices;
    @Autowired private RecipientServices recipientServices;
    @Autowired private RiderServices riderServices;

    @QueryMapping
    public List<Client> allClient() {
        return clientServices.getAllClient();
    }

    @QueryMapping
    public List<Rider> riderByArea(@Argument String city, @Argument String province) {
        return riderServices.getRidersByArea(city, province);
    }

    @QueryMapping
    public Client clientByAccount(@Argument String uid) {
        return clientServices.getClientByAccount(uid);
    }

    @QueryMapping
    public List<Rider> allRiders() { return riderServices.getAllRiders(); }

    @MutationMapping
    public Client createClient(@Argument @Valid Client client) {
        return clientServices.addNewClient(client);
    }

    @MutationMapping
    public Rider createRider(@Argument @Valid Rider rider) { return riderServices.createNewRider(rider); }

    @MutationMapping
    public Rider updateRider(@Argument @Valid Rider rider) { return riderServices.updateRider(rider); }

    @MutationMapping
    public Rider deleteRider(@Argument Long riderId) { return riderServices.deleteRider(riderId); }

    @SchemaMapping(typeName = "Orders", field = "client")
    public Client getOrderClient(Orders orders) {
        return clientServices.getClientById(orders.getClientId());
    }

    @SchemaMapping(typeName = "Orders", field = "recipient")
    public Recipient getRecipient(Orders order) {
        return recipientServices.getRecipientById(order.getRecipientId());
    }

    @SchemaMapping(typeName = "Orders", field = "rider")
    public Rider getRider(Orders orders) { return riderServices.getRiderById(orders.getRiderId()); }
}
