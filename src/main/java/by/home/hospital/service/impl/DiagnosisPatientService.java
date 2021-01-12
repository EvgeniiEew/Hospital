package by.home.hospital.service.impl;

import by.home.hospital.domain.DiagnosisPatient;
import by.home.hospital.domain.PatientDetails;
import by.home.hospital.dto.ExaminationDoctorDto;
import by.home.hospital.service.IDiagnosisPatientService;
import by.home.hospital.service.repository.DiagnosisPatientJpaRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class DiagnosisPatientService implements IDiagnosisPatientService {

    @Autowired
    private DiagnosisService diagnosisService;
    @Autowired
    private PatientDetailsService patientDetailsService;
    @Autowired
    private DiagnosisPatientJpaRepository diagnosisPatientJpaRepository;

    public List< DiagnosisPatient> getDiagnosisPatient(Integer id) {
        return this.diagnosisPatientJpaRepository.getDiagnosisPatientByPatientDetailsId(id);
    }

    public DiagnosisPatient save(DiagnosisPatient diagnosisPatient) {
        return this.diagnosisPatientJpaRepository.save(diagnosisPatient);
    }

    public void saveDiagnosisPatientFromExaminationDoctorDto(ExaminationDoctorDto examinationDoctorDto) {
        DiagnosisPatient diagnosisPatient = new DiagnosisPatient();
        diagnosisPatient.setPatientDetails(this.patientDetailsService.setStarusCheckingByPatientId(examinationDoctorDto.getPatientId()));
        diagnosisPatient.setDiagnosis(this.diagnosisService.createDiagnosisFromExaminationDoctorDto(examinationDoctorDto));
        this.diagnosisPatientJpaRepository.save(diagnosisPatient);
    }

    public List<String> getAllIdDiagnosisFromListPatientDetailsId(Integer patientId) {
        PatientDetails patientDetails = this.patientDetailsService.getPatientDetailsByPatientId(patientId);
        List<Integer> idDiagnosisPatient = new ArrayList<>();
        for (DiagnosisPatient diagnosispatient : this.diagnosisPatientJpaRepository.findAllById(patientDetails.getId())) {
            idDiagnosisPatient.add(diagnosispatient.getId());
        }
        return this.diagnosisService.findAllPatientDiagnosis(idDiagnosisPatient);

    }
}
