package com.connected.car.vehicle.Exception;

import com.connected.car.vehicle.exceptions.*;
import com.connected.car.vehicle.response.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    @Mock
    private ResourceNotFoundException resourceNotFoundException;

    @Mock
    private CarNotFoundException carNotFoundException;

    @Mock
    private EngineAlreadyActiveException engineAlreadyActiveException;

    @Mock
    private InvalidvalueException invalidvalueException;

    @Mock
    private RecordDeactivatedException recordDeactivatedException;

    @Mock
    private TripDetailsNotFoundException tripDetailsNotFoundException;

    @Mock
    private EngineAlreadyInActiveException engineAlreadyInActiveException;

    @Mock
    private Exception genericException;

    @Mock
    private RecordAlreadyExistsException recordAlreadyExistsException;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void testHandlerResourceNotFoundException() {
    	String errorMessage = "Resource not found!";
        when(resourceNotFoundException.getMessage()).thenReturn(errorMessage);
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handlerResourceNotFoundException(resourceNotFoundException);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Resource not found", response.getBody().getResponseMessage());
    }

    @Test
    void testHandlerUserNotFoundException() {
        when(carNotFoundException.getMessage()).thenReturn("Car not found");
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handlerUserNotFoundException(carNotFoundException);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Car not found", response.getBody().getResponseMessage());
    }

    // Similar tests for other exception handlers...

    @Test
    void testHandlerException() {
        when(genericException.getMessage()).thenReturn("Internal Server Error");
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handlerException(genericException);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred: Internal Server Error", response.getBody().getResponseMessage());
    }

    @Test
    void testHandlerRecordAlreadyExistsException() {
        when(recordAlreadyExistsException.getMessage()).thenReturn("Record already exists");
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handlerRecordAlreadyExistsException(recordAlreadyExistsException);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Record already exists", response.getBody().getResponseMessage());
    }
}
