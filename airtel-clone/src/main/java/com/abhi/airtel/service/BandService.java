package com.abhi.airtel.service;

import com.abhi.airtel.entity.Band;
import java.util.List;

public interface BandService {
    Band saveBand(Band band);
    Band getBandById(Long id);
    List<Band> getAllBands();
    Band updateBand(Long id, Band band);
    void deleteBand(Long id);

    void updateBandPassword(Band band);
}

