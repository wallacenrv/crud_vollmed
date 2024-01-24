package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService { // essa classe gera e valida os tokens

    @Value("${api.security.token.secret}") // essa informacao esta no properties
    private String secret;

    public String gerarToken(Usuario usuario) {
        System.out.println("esta trazendo o token " + secret);
        try {// biblioteca que adiocnamos no projeto
            var algoritmo = Algorithm.HMAC256(secret); // algoritmo para fazer assinatura digital desse token
            return JWT.create()
                    .withIssuer("API Voll.med") // identifica a api que é dona , quem esta gerando esse token
                    .withSubject(usuario.getLogin())// quem é pessoa relacionada com esse token
                    .withExpiresAt(dataExpiracao())
//                    .withClaim("id", usuario.getId()) //
                    .sign(algoritmo); // faz a assinatura
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token jwt", exception);



        }
    }

    private Instant dataExpiracao() { //código expira em 2 horas
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")); // confome fuso horario do brasil
    }
}
// é recomendado ter prazo de validade nesse token

//https://github.com/auth0/java-jwt