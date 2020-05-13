package com.example.appliances.services;

import com.example.appliances.dao.ApplianceRepository;
import com.example.appliances.entities.Appliance;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApplianceService {
    private final ApplianceRepository applianceRepository;

    @Autowired
    public ApplianceService(ApplianceRepository applianceRepository) {
        this.applianceRepository = applianceRepository;
    }

    public Appliance addAppliance(Appliance appliance) {
        return applianceRepository.save(appliance);
    }

    public Iterable<Appliance> getAppliances() {
        return applianceRepository.findAll();
    }

    public void deleteAppliance(UUID id) {
        applianceRepository.deleteById(id);
    }

    public Optional<Appliance> getAppliance(UUID id) {
        return applianceRepository.findById(id);
    }

    public void patchAppliance(UUID id, Appliance patchedAppliance) {
        Appliance appliance = applianceRepository
                .findById(id)
                .orElseThrow(NoSuchElementException::new);
        BeanUtils.copyProperties(patchedAppliance, appliance, "id", null);
        applianceRepository.save(appliance);
    }
}
