package com.nathanael.florcreation.orders.dtos.paysource;

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
    private Long amount;
    @SerializedName("billing")
    @Expose
    private Object billing;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("livemode")
    @Expose
    private Boolean livemode;
    @SerializedName("redirect")
    @Expose
    private Redirect redirect;
    @SerializedName("statement_descriptor")
    @Expose
    private Object statementDescriptor;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("created_at")
    @Expose
    private Long createdAt;
    @SerializedName("updated_at")
    @Expose
    private Long updatedAt;
}
