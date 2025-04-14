package com.nwai.dentalsys.service;

import com.nwai.dentalsys.dto.request.*;
import com.nwai.dentalsys.dto.response.*;
import java.util.Optional;
import java.util.List;

public interface DentistService {
    //Create
    Optional<DentistResponseDto> createBook(DentistRequestDto dentistRequestDto);

    //Update
    Optional<DentistResponseDto> updateBook(String isbn, DentistRequestDto dentistRequestDto);

    //Find by isbn
    Optional<DentistResponseDto> findBookByIsbn(String isbn);

    //Delete by isbn
    void deleteBookByIsbn(String isbn);

    //Find all
    List<DentistResponseDto> findAllBooks();
}
