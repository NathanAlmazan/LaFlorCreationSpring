package com.nathanael.florcreation.users.dtos;

import com.nathanael.florcreation.users.models.RecipientTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class Recipient {
    private Long recipientId;

    @NotNull
    @Length(min = 1, max = 50)
    private String recipientName;

    @NotNull
    @Length(min = 10, max = 12)
    private String recipientContact;

    @NotNull
    @Length(min = 1, max = 50)
    private String recipientStreet;

    @NotNull
    @Length(min = 1, max = 50)
    private String recipientCity;

    @NotNull
    @Length(min = 1, max = 50)
    private String recipientProvince;

    private Double latitude;

    private Double longitude;

    public Recipient(RecipientTable recipientTable) {
        recipientId = recipientTable.getRecipientId();
        recipientName = recipientTable.getRecipientName();
        recipientContact = recipientTable.getRecipientContact();
        recipientStreet = recipientTable.getRecipientStreet();
        recipientCity = recipientTable.getRecipientCity();
        recipientProvince = recipientTable.getRecipientProvince();
        latitude = recipientTable.getLatitude();
        longitude = recipientTable.getLongitude();
    }
}
