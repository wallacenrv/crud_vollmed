package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratandoDeErros {


    //Trata erros de requisicao
    @ExceptionHandler(EntityNotFoundException.class) // requisicao nao encontrada
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

// valida os campos
    @ExceptionHandler(MethodArgumentNotValidException.class) // tratamento de validacao.. tenho que retornar alguma coisa no corpo
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){ // aqui eu capturo a exceptionv
        var erros = ex.getFieldErrors(); // esse metodo gera uma lista
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());// aqui estou convertendo para uma lista de DadosErroValidacao, mas preciso do construtor
        // vou ter que criar um dto para receber apenas os campos que o spring devolve do erro
    }


    //CrieI um DTO
    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }


    }




}
