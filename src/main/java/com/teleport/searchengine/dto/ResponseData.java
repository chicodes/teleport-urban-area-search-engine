package com.teleport.searchengine.dto;

import com.teleport.searchengine.utils.TranTypeEnum;
import lombok.Data;

@Data
public class ResponseData {
        private long id;
        private String date;
        private String amount;
        private String currencyCode;
        private TranTypeEnum transactionType;
}
