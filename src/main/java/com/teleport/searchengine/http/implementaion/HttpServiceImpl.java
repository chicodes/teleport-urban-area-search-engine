package com.teleport.searchengine.http.implementaion;

import com.teleport.searchengine.http.HttpService;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import static com.teleport.searchengine.utils.Constants.APPLICATION_JSON;

@RequiredArgsConstructor
@Service
public class HttpServiceImpl implements HttpService {

    private final OkHttpClient okHttpClient;

    private static final Logger logger = LoggerFactory.getLogger(HttpServiceImpl.class);

    @Override
    public Integer postOkhttp(Map<String, String> headerList, String requestBody, String url) {
        try {
            OkHttpClient client = new OkHttpClient();
            logger.info("initial request body {}", requestBody);
 RequestBody body = RequestBody.Companion.create(requestBody, MediaType.parse(APPLICATION_JSON));
            logger.info("OTP request body: {}", body);
            Request request = new Request.Builder()
                    .url(url)
                    //.method("POST", body)
                    .post(body)
                    .addHeader("Content-Type", APPLICATION_JSON)
                    .build();
            Response response = client.newCall(request).execute();
            logger.info(response.body().string());
            //String resp = response.body().string();
            //OtpResponseBaseDto bvnresponse = new Gson().fromJson(resp, OtpResponseBaseDto.class);
            logger.info("{}", response.code());
            return response.code();

        } catch (IOException e) {
            logger.info("{}", e.getMessage());
            //throw new Exception(e.getMessage());
        }
        return null;
    }

    @Override
    public Response get(Map<String, String> headerList, Map<String, Object> params, String url) throws IOException {
        logger.info("Making GET request with header {}, params {} and url {}", headerList, params, url);
//      URL httpUrl = UriComponentsBuilder.fromHttpUrl(url).queryParam("bvn",params.get("bvn")).build(params).toURL();
        //System.out.println(httpUrl);
        Request request = new Request.Builder().get().headers(Headers.of(headerList)).url(url).build();
        Call call = okHttpClient.newCall(request);
        return call.execute();
    }
}
