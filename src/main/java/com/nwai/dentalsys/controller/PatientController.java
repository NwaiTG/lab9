package com.nwai.dentalsys.controller;

import com.nwai.dentalsys.dto.request.PatientRequestDto;
import com.nwai.dentalsys.dto.response.PatientResponseDto;
import com.nwai.dentalsys.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adsweb/api/v1")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // 1. GET all patients sorted by last name
    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatientsSortedByLastName());
    }

    // 2. GET a patient by ID
    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable int id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. POST new patient
    @PostMapping("/patients")
    public ResponseEntity<PatientResponseDto> createPatient(@RequestBody PatientRequestDto requestDto) {
        return ResponseEntity.ok(patientService.createPatient(requestDto));
    }

    // 4. PUT update patient
    @PutMapping("/patients/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable int id, @RequestBody PatientRequestDto requestDto) {
        return patientService.updatePatient(id, requestDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. DELETE patient
    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable int id) {
        boolean deleted = patientService.deletePatientById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // 6. SEARCH patients
    @GetMapping("/patient/search/{searchString}")
    public ResponseEntity<List<PatientResponseDto>> searchPatients(@PathVariable String searchString) {
        return ResponseEntity.ok(patientService.searchPatients(searchString));
    }
}
