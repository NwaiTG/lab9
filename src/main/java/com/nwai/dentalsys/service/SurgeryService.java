package com.nwai.dentalsys.service;

import com.nwai.dentalsys.dto.request.*;
import com.nwai.dentalsys.dto.response.*;
import java.util.Optional;
import java.util.List;

public interface SurgeryService {
    //Create
    Optional<SurgeryResponseDto> createBook(SurgeryRequestDto surgeryRequestDto);

    //Update
    Optional<SurgeryResponseDto> updateBook(String isbn, SurgeryRequestDto surgeryRequestDto);

    //Find by isbn
    Optional<SurgeryResponseDto> findBookByIsbn(String isbn);

    //Delete by isbn
    void deleteBookByIsbn(String isbn);

    //Find all
    List<SurgeryResponseDto> findAllBooks();
}
