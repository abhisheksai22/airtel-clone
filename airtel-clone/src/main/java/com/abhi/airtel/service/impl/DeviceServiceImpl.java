package com.abhi.airtel.service.impl;

import com.abhi.airtel.entity.Device;
import com.abhi.airtel.repository.DeviceRepository;
import com.abhi.airtel.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public Device updateDevice(Long id, Device device) {
        if (deviceRepository.existsById(id)) {
            device.setDeviceId(id);
            return deviceRepository.save(device);
        }
        return null;
    }

    @Override
    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }
}

