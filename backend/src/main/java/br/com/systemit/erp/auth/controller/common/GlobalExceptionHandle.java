package br.com.systemit.erp.auth.controller.common;

import br.com.systemit.erp.auth.dto.ErroCampo;
import br.com.systemit.erp.auth.dto.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErroCampo> erros  = e.getFieldErrors().stream().map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage())).collect(Collectors.toList());
        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro validação",
                erros);
    }
}
