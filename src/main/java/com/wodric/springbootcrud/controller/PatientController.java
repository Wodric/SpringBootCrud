package com.wodric.springbootcrud.controller;

import com.wodric.springbootcrud.exception.PatientNotFoundException;
import com.wodric.springbootcrud.model.Patient;
import com.wodric.springbootcrud.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientRepository repository;

    public PatientController(PatientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatient(){
        List<Patient> patientList = repository.findAll();
        return new ResponseEntity<>(patientList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable("id") Long id) throws PatientNotFoundException {
        Patient patient = repository.findById(id).orElseThrow(PatientNotFoundException::new);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient){
        Patient patientReturned = repository.save(patient);
        return  new ResponseEntity<>(patientReturned, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient){
        Patient patientReturned = repository.save(patient);
        return  new ResponseEntity<>(patientReturned, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") Long id){
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.GONE);
    }
}
