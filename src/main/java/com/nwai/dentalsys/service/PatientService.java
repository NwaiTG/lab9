package com.nwai.dentalsys.service;

import com.nwai.dentalsys.dto.request.*;
import com.nwai.dentalsys.dto.response.*;
import java.util.Optional;
import java.util.List;

public interface PatientService {
    //Create
    Optional<PatientResponseDto> createBook(PatientRequestDto patientRequestDto);

    //Update
    Optional<PatientResponseDto> updateBook(String isbn, PatientRequestDto patientRequestDto);

    //Find by isbn
    Optional<PatientResponseDto> findBookByIsbn(String isbn);

    //Delete by isbn
    void deleteBookByIsbn(String isbn);

    //Find all
    List<PatientResponseDto> findAllBooks();
}
