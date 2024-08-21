package cz.itnetwork.controller.advice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;

@ControllerAdvice
public class EntityNotFoundExceptionAdvice {
    /**
     * Metoda přijímá globálně vyjímky z aplikace, zpracuje je a nakonec vrátí přednastavenou chybovou zprávu z
     * dané metody
     * @param ex - parametr vyjímek které se vyhodí kdekoli v aplikaci
     * @return Vrací objekt chybové zprávy
     */
    @ExceptionHandler({NotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity<String> handleEntityNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
