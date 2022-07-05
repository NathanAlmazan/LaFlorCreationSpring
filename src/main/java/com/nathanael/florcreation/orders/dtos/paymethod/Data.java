package com.nathanael.florcreation.orders.dtos.paymethod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Data {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("type")
    @Expose
    public String type;
}
