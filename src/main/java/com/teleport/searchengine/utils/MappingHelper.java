package com.teleport.searchengine.utils;

import com.teleport.searchengine.model.UrbanArea;
import com.teleport.searchengine.dto.ResponseData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
    public class MappingHelper {

//        public ResponseData mapADateFromOrder(UrbanArea order) {
//
//            ResponseData data= returnData(order);
//            return data;
//        }


//        public List<ResponseData> returnDatalist(List<UrbanArea> orders){
//
//            List<ResponseData> response = null;
//
//            response =orders.stream().map(item->returnData(item)).collect(Collectors.toList());
//
//            return response;
//        }
//        private ResponseData returnData(UrbanArea order){
//
//            ResponseData data = new ResponseData();
//            data.setId(order.getId());
//            if(order.getDate() != null)
//                data.setDate(order.getDate().toString());
//                data.setAmount(order.getAmount().toString());
//                data.setCurrencyCode(order.getCurrencyCode());
//                data.setTransactionType(order.getTransactionType());
//
//            return data;
//        }
    }