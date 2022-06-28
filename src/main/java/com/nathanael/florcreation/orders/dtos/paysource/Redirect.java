package com.nathanael.florcreation.orders.dtos.paysource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Redirect {
    @SerializedName("checkout_url")
    @Expose
    private String checkoutUrl;
    @SerializedName("failed")
    @Expose
    private String failed;
    @SerializedName("success")
    @Expose
    private String success;
}
