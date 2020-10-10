package com.wodric.springbootcrud.controller;

import com.wodric.springbootcrud.exception.AdressNotFundException;
import com.wodric.springbootcrud.exception.PatientNotFoundException;
import com.wodric.springbootcrud.model.Adress;
import com.wodric.springbootcrud.repository.AdressRepository;
import com.wodric.springbootcrud.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdressController {

    private final PatientRepository patientRepository;
    private final AdressRepository repository;

    public AdressController(AdressRepository repository, PatientRepository patientRepository) {
        this.repository = repository;
        this.patientRepository = patientRepository;
    }

    @GetMapping(value = "/patient/{patientId}/adress")
    public ResponseEntity<List<Adress>> getAllAdress(@PathVariable("patientId") Long patientId) {
        List<Adress> patientList = repository.findByPatientId(patientId);
        return new ResponseEntity<>(patientList, HttpStatus.OK);
    }

    @GetMapping(value = "/patient/{patientId}/adress/{id}")
    public ResponseEntity<Adress> getAdress(@PathVariable("id") Long id, @PathVariable("patientId") Long patientId)
            throws AdressNotFundException {
        Adress adress = repository.findById(id).orElseThrow(AdressNotFundException::new);
        return new ResponseEntity<>(adress, HttpStatus.OK);
    }

    @PostMapping(value = "/patient/{patientId}/adress")
    public ResponseEntity<Adress> addAdress(@RequestBody Adress adress, @PathVariable("patientId") Long patientId) throws PatientNotFoundException {
        Adress adressSaved = patientRepository.findById(patientId).map(patient -> {
            adress.setPatient(patient);
            return repository.save(adress);
        }).orElseThrow(PatientNotFoundException::new);
        return new ResponseEntity<>(adressSaved, HttpStatus.CREATED);
    }

    @PutMapping(value = "/patient/{patientId}/adress")
    public ResponseEntity<Adress> updateAdress(@RequestBody Adress adress, @PathVariable("patientId") Long patientId) throws AdressNotFundException {
        Adress adressSaved = patientRepository.findById(patientId).map(patient -> {
            adress.setPatient(patient);
            return repository.save(adress);
        }).orElseThrow(AdressNotFundException::new);

        return new ResponseEntity<>(adressSaved, HttpStatus.OK);
    }

    @DeleteMapping(value = "/patient/{patientId}/adress/{id}")
    public ResponseEntity<Void> deleteAdress(@PathVariable("id") Long id, @PathVariable("patientId") Long patientId) throws AdressNotFundException {
        return repository.findByIdAndPatientId(id,patientId).map(adress -> {
            repository.deleteById(adress.getId());
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }).orElseThrow(AdressNotFundException::new);
    }
}
