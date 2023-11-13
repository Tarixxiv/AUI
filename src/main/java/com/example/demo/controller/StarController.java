package com.example.demo.controller;

import com.example.demo.dto.GetStarResponse;
import com.example.demo.dto.GetStarsResponse;
import com.example.demo.function.StarToResponseFunction;
import com.example.demo.function.StarsToResponseFunction;
import com.example.demo.service.StarService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@Log

public class StarController {
    private final StarService service;
    private final StarToResponseFunction starToResponse;
    private final StarsToResponseFunction starsToResponse;
    @Autowired
    public StarController(StarService service, StarToResponseFunction starToResponse, StarsToResponseFunction starsToResponse) {
        this.service = service;
        this.starToResponse = starToResponse;
        this.starsToResponse = starsToResponse;
    }

    @GetMapping("api/stars")
    @ResponseStatus(HttpStatus.OK)
    public GetStarsResponse getStarsResponse(){
        return starsToResponse.apply(service.findAll());
    }
    @GetMapping("/api/stars/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GetStarResponse getStarResponse(@PathVariable("id") UUID id){
        return service.find(id)
                .map(starToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/api/stars/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStar(@PathVariable("id") UUID id){
        service.find(id)
                .ifPresentOrElse(
                        profession -> service.delete(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );

    }

}