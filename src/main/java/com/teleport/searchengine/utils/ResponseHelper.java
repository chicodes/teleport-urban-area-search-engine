package com.teleport.searchengine.utils;

import com.teleport.searchengine.dto.TeleportResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseHelper <T> {

    public TeleportResponse getResponse (String failedCode, String message, T body, HttpStatus httpStatus) {
        TeleportResponse response = new TeleportResponse();
        response.setRespCode(failedCode);
        response.setRespDescription(message);
        response.setRespBody(body);
        response.setHttpStatus(httpStatus);
        return response;
    }
}
