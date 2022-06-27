package com.nathanael.florcreation.users.dtos;

import com.nathanael.florcreation.users.models.ClientTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class Client {
    private Long clientId;

    @NotNull
    @Length(min = 1, max = 30)
    private String accountUid;

    @NotNull
    @Length(min = 1, max = 50)
    private String clientName;

    @NotNull
    @Length(min = 10, max = 12)
    private String clientContact;

    public Client(ClientTable clientTable) {
        clientId = clientTable.getClientId();
        accountUid = clientTable.getAccountUid();
        clientName = clientTable.getClientName();
        clientContact = clientTable.getClientContact();
    }
}
