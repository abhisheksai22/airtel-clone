package com.abhi.airtel.service.impl;

import com.abhi.airtel.entity.Band;
import com.abhi.airtel.exceptions.RouterNotFoundException;
import com.abhi.airtel.repository.BandRepository;
import com.abhi.airtel.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandServiceImpl implements BandService {

    @Autowired
    private BandRepository bandRepository;

    @Override
    public Band saveBand(Band band) {
        return bandRepository.save(band);
    }

    @Override
    public Band getBandById(Long id) {
        return bandRepository.findById(id)
                .orElseThrow(() -> new RouterNotFoundException("Band not found with Id : " + id));
    }

    @Override
    public List<Band> getAllBands() {
        return bandRepository.findAll();
    }

    @Override
    public Band updateBand(Long id, Band band) {
        if (bandRepository.existsById(id)) {
            band.setBandId(id);
            return bandRepository.save(band);
        } else {
            throw new RouterNotFoundException("Band not found with Id : " + id);
        }
    }

    @Override
    public void deleteBand(Long id) {
        bandRepository.deleteById(id);
    }

    @Override
    public void updateBandPassword(Band band) {
        bandRepository.save(band);
    }
}

