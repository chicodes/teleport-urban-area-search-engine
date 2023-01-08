package com.teleport.searchengine.http;
import java.io.IOException;
import java.util.Map;

public interface HttpService {
    okhttp3.Response get(Map<String, String> headerList, Map<String, Object> params, String url) throws IOException;

    Integer postOkhttp(Map<String, String> headerList, String requestBody, String url);

}
