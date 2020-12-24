package by.home.hospital.service.impl;

import by.home.hospital.domain.Diagnosis;
import by.home.hospital.service.IDiagnosisService;
import by.home.hospital.service.repository.DiagnosisJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DiagnosisService implements IDiagnosisService {

    @Autowired
    private  DiagnosisJpaRepository diagnosisJpaRepository;

    @Override
    public List<Diagnosis> findAll() {
        return this.diagnosisJpaRepository.findAll();
    }

    public void save(Diagnosis diagnosis){
        this.diagnosisJpaRepository.save(diagnosis);
    }
}
