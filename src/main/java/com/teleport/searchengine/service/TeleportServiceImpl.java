package com.teleport.searchengine.service;

import com.google.gson.Gson;
import com.teleport.searchengine.dto.GetUrbanAreResponseDto;
import com.teleport.searchengine.http.HttpService;
import com.teleport.searchengine.dto.TeleportResponse;
import com.teleport.searchengine.repository.OrderRepository;
import com.teleport.searchengine.urbanareadto.*;
import com.teleport.searchengine.urbanareadto.images.ImagesDto;
import com.teleport.searchengine.urbanareadto.images.PhotosItem;
import com.teleport.searchengine.urbanareadto.salaries.SalariesDto;
import com.teleport.searchengine.urbanareadto.salaries.SalariesItem;
import com.teleport.searchengine.urbanareadto.scores.CategoriesItem;
import com.teleport.searchengine.urbanareadto.scores.ScoresDto;
import com.teleport.searchengine.utils.MappingHelper;
import com.teleport.searchengine.utils.ResponseHelper;
import com.teleport.searchengine.validation.OrderExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.teleport.searchengine.utils.Constants.*;
import static javax.management.remote.JMXConnectionNotification.FAILED;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeleportServiceImpl implements TeleportService {

    private final ResponseHelper responseHelper;
    private final Gson gson;

    @Value("${listUrbanAreaUrl}")
    private String listUrbanAreaUrl;

    @Value("${getUrbanAreaUrl}")
    private String getUrbanAreaUrl;

    private final HttpService httpService;

    private final GetUrbanAreResponseDto getUrbanAreResponseDto;

    private final CacheManager cacheManager;

    @Cacheable(cacheNames = {"teleportCache"}, key = "#country")
    @Override
    public TeleportResponse getArea(String country){
        try {
             //throw new OrderExistException("order no dey");
            log.info("retrieving urban area");
            String formatCountrySalaryUrl = getUrbanAreaUrl + country +"/salaries";
            String formatCountryScoreUrl = getUrbanAreaUrl + country +"/scores";
            String formatCountryPictureUrl = getUrbanAreaUrl + country +"/images";
            okhttp3.Response salaryData = getTeleportResponse(formatCountrySalaryUrl);
            okhttp3.Response scoreData = getTeleportResponse(formatCountryScoreUrl);
            okhttp3.Response imageData = getTeleportResponse(formatCountryPictureUrl);

            if(salaryData.code()!=200 || scoreData.code()!=200 || imageData.code()!=200){
                return responseHelper.getResponse(FAILED_CODE, FAILED, "Could not get positive response from external resource", HttpStatus.EXPECTATION_FAILED);
            }

            SalariesDto salaryResponse = gson.fromJson(salaryData.body().string(), SalariesDto.class);
            List<SalariesItem> getSalaries = salaryResponse.getSalaries();

            ScoresDto scoreResponse = gson.fromJson(scoreData.body().string(), ScoresDto.class);
            List<CategoriesItem> getScores = scoreResponse.getCategories();

            ImagesDto imageResponse = gson.fromJson(imageData.body().string(), ImagesDto.class);
            List<PhotosItem> getimage = imageResponse.getPhotos();

            getUrbanAreResponseDto.setSalary(getSalaries);
            getUrbanAreResponseDto.setScore(getScores);
            getUrbanAreResponseDto.setPicture(getimage);

            log.info("get urban area 2: {}", getUrbanAreResponseDto);
            return responseHelper.getResponse(SUCCESS_CODE, SUCCESS, getUrbanAreResponseDto, HttpStatus.OK);
        }
        catch (Exception e){
            log.info(e.getMessage());
        }
        return null;
    }

    @Override
    public TeleportResponse listAreas(){
        try{
            log.info("getting list of urban areas");
            okhttp3.Response responseData = getTeleportResponse(listUrbanAreaUrl);
            if(responseData.code()!=200){
                return responseHelper.getResponse(FAILED_CODE, FAILED, "Could not get positive response from external resourse", HttpStatus.EXPECTATION_FAILED);
            }
            ListUrbanAreaResponseDto apiResponse = gson.fromJson(responseData.body().string(), ListUrbanAreaResponseDto.class);
            List<UaItems> africanCountries = apiResponse.getLinks().getUaItems();
            log.info("LIST OF URBAN AREA RESPONSE1: {}", africanCountries);
            log.info("LIST OF URBAN AREA RESPONSE: {}", africanCountries);
            return  responseHelper.getResponse(SUCCESS_CODE, SUCCESS, africanCountries, HttpStatus.OK);
        }
        catch(Exception e) {
            log.info(e.getMessage());
            return responseHelper.getResponse(FAILED_CODE, FAILED, "", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private okhttp3.Response getTeleportResponse(String url) {
        try {
            Map<String, String> header = getHeaders();
            okhttp3.Response resp = httpService.get(header, null, url);
            return resp;
        }
        catch (Exception e){
            log.info(e.getMessage());
        }
        return null;
    }

//    private String getUrbanAreaRequest(){
//        String otpString = utility.generateRandomPassword(5);
//        log.info("generating Termii OTP request");
//        String messageText = "Hello, your OTP code is" + otpString;
//        Map<String, String> otpRequest = new HashMap<>();
//        otpRequest.put("to", receiverPhoneNumber);
//        otpRequest.put("from", "Medusa");
//        return gson.toJson(otpRequest);
//    }


    private Map<String, String> getHeaders() {
        log.info("Preparing headers");
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        return header;
    }
}
