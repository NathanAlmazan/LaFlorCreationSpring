package com.nathanael.florcreation.orders.dtos.payintent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Attributes {
    @SerializedName("amount")
    @Expose
    public Long amount;
    @SerializedName("capture_type")
    @Expose
    public String captureType;
    @SerializedName("client_key")
    @Expose
    public String clientKey;
    @SerializedName("currency")
    @Expose
    public String currency;
}
