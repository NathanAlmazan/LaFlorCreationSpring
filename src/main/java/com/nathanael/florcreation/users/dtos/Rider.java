package com.nathanael.florcreation.users.dtos;

import com.nathanael.florcreation.users.models.RiderTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class Rider {
    private Long riderId;

    @Length(min = 1, max = 30)
    private String accountUid;

    @NotNull
    @Length(min = 1, max = 50)
    private String riderName;

    @Length(min = 10, max = 12)
    private String riderContact;

    @NotNull
    @Length(min = 1, max = 15)
    private String riderCity;

    @NotNull
    @Length(min = 1, max = 15)
    private String riderProvince;

    @Length(min = 1, max = 200)
    private String riderImage;

    public Rider(RiderTable riderTable) {
        riderId = riderTable.getRiderId();
        accountUid = riderTable.getAccountUid();
        riderName = riderTable.getRiderName();
        riderContact = riderTable.getRiderContact();
        riderCity = riderTable.getRiderCity();
        riderProvince = riderTable.getRiderProvince();
        riderImage = riderTable.getRiderImage();
    }
}
