package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager; // dispara o processo de autenticacao / é necessario criar um metodo na classe SecurityConfiguracao

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {// PASSO O LOGIN E SENHA QUE VEM DO FRONT END
        var token = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());//2 // CRIA O DTO DO SPRING
        var authentication = manager.authenticate(token); //1 temos que criar na linha de cima a variavel token

        return ResponseEntity.ok(""); // DEVOLVEDNO O CÓDIOG 200 COMO REPOSTA AO USUARIO



    }

}

// esse token é o login e senha, porem ele ja esta representado no DTO Dadosautenticacao
// o spring tem o dto dele, nos temos o nosso = teremos que fazer a covnersao