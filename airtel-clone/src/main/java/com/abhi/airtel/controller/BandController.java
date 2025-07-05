package com.abhi.airtel.controller;

import com.abhi.airtel.entity.Band;
import com.abhi.airtel.service.BandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bands")
@Slf4j
public class BandController {

    @Autowired
    private BandService bandService;

    @PostMapping
    public Band createBand(@RequestBody Band band) {
        return bandService.saveBand(band);
    }

    @GetMapping("/{id}")
    public Band getBandById(@PathVariable Long id) {
        return bandService.getBandById(id);
    }

    @GetMapping
    public List<Band> getAllBands() {
        return bandService.getAllBands();
    }

    @PutMapping("/{id}")
    public Band updateBand(@PathVariable Long id, @RequestBody Band band) {
        return bandService.updateBand(id, band);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBand(@PathVariable Long id) {
        bandService.deleteBand(id);
        return ResponseEntity.noContent().build();
    }
}

