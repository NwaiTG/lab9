package com.nwai.dentalsys.service;

import com.nwai.dentalsys.dto.request.*;
import com.nwai.dentalsys.dto.response.*;
import java.util.Optional;
import java.util.List;

public interface AppointmentService {
    //Create
    Optional<AppointmentResponseDto> createBook(AppointmentRequestDto appointmentRequestDto);

    //Update
    Optional<AppointmentResponseDto> updateBook(String isbn, AppointmentRequestDto appointmentRequestDto);

    //Find by isbn
    Optional<AppointmentResponseDto> findBookByIsbn(String isbn);

    //Delete by isbn
    void deleteBookByIsbn(String isbn);

    //Find all
    List<AppointmentResponseDto> findAllBooks();
}
