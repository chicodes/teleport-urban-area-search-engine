package com.teleport.searchengine.dto;

import com.teleport.searchengine.urbanareadto.images.PhotosItem;
import com.teleport.searchengine.urbanareadto.salaries.SalariesItem;
import com.teleport.searchengine.urbanareadto.scores.CategoriesItem;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class GetUrbanAreResponseDto {
    private List<SalariesItem> salary;
    private List<CategoriesItem> score;
    private List<PhotosItem> picture;
}
