package com.example.SpringBoot.Entities;

import com.example.SpringBoot.Services.GradeServices;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Setter
@Getter
public class Statistics {

    private double Median;
    private double Average;
    private double Min;
    private double Max;

}
