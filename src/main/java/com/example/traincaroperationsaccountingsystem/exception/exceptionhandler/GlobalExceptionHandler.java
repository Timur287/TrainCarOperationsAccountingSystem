package com.example.traincaroperationsaccountingsystem.exception.exceptionhandler;


import com.example.traincaroperationsaccountingsystem.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TrainCarNotFoundException.class)
    public ErrorMessage handleTrainCarNotFoundException(TrainCarNotFoundException exception){

        log.error(exception.getMessage(), exception);
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CargoNotFoundException.class)
    public ErrorMessage handleCargoNotFoundException(CargoNotFoundException exception){

        log.error(exception.getMessage(), exception);
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RailwayStationNotFoundException.class)
    public ErrorMessage handleRailwayStationNotFoundException(RailwayStationNotFoundException exception){

        log.error(exception.getMessage(), exception);
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RailwayNotFoundException.class)
    public ErrorMessage handleRailwayNotFoundException(RailwayNotFoundException exception){

        log.error(exception.getMessage(), exception);
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LoadedTrainCarNotFoundException.class)
    public ErrorMessage handleLoadedTrainCarNotFoundException(LoadedTrainCarNotFoundException exception){

        log.error(exception.getMessage(), exception);
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncorrectTrainCarLocationException.class)
    public ErrorMessage handleIncorrectTrainCarLocationException(IncorrectTrainCarLocationException exception){

        log.error(exception.getMessage(), exception);
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CargoIsAlreadyInUseException.class)
    public ErrorMessage handleCargoIsAlreadyInUseException(CargoIsAlreadyInUseException exception){

        log.error(exception.getMessage(), exception);
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TrainCarIsAlreadyInUseException.class)
    public ErrorMessage handleTrainCarIsAlreadyInUseException(TrainCarIsAlreadyInUseException exception){

        log.error(exception.getMessage(), exception);
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExceedingLoadCapacityException.class)
    public ErrorMessage handleExceedLoadCapacityException(ExceedingLoadCapacityException exception){

        log.error(exception.getMessage(), exception);
        return new ErrorMessage(exception.getMessage());
    }

}
