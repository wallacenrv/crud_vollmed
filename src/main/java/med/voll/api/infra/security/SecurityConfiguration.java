package med.voll.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    
    @Bean // serve para exportar uma classe para o spring, fazendo com que ela consiga carregala e realize a sua inkecao de de dependencia em outras classes
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ // COM ISSO ensinei o spring a utilizar esse algoritmo para criptografia Bcrypt de hash de senha
        return new BCryptPasswordEncoder();
    }

}

/*
 -------------------------- Metodo Antigo ------------------------------

    // tem que ser esse metodo
    @Bean // essa anotacao serve para ler e expor o retorno do metodo / estou develvednoo objeto para o spring
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().build();

    }


csrf().disable() desabilitando protecao contra CSRF (CROSS-SITE REQUEST FORGERY). O PROPRIO TOKEN JA É UMA PROTECAO
csrf cobra uma exception entao vamso lancar ela

.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
.and().build(); aqui estou desabilitando aquele formulario e c=olocando a autenticacao como stateless pq aqui é uma api rest

 a partir de agora o spring nao da mais a tela e nao vai mais bloquear as URL

    O método securityFilterChain deveria ter sido anotado com @Bean.


        Sem essa anotação no método, o objeto SecurityFilterChain não será exposto como um bean para o Spring.

*/
