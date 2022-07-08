package com.nathanael.florcreation.orders.dtos;

import com.nathanael.florcreation.orders.repository.ProvinceRankingProj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProvinceRankDto {
    private String province;
    private Integer orderCount;

    public ProvinceRankDto(ProvinceRankingProj provinceRankingProj) {
        province = provinceRankingProj.getProvince();
        orderCount = provinceRankingProj.getOrderCount();
    }
}
