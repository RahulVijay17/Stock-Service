package com.laderatech.stockservice.vo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockVo {

    private Long stockId;
    private Integer productQuantity;
    private Long productCode;
    private String location;
    private String zipCode;
    @JsonIgnore
    private LocalDateTime createdAt;
    @JsonIgnore
    private String createdBy;
    @JsonIgnore
    private LocalDateTime updatedAt;
    @JsonIgnore
    private String updatedBy;

}
