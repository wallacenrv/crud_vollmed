package med.voll.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // spring carrega esse componente servico de autenticacao
public class AutenticacaoService implements UserDetailsService { // somos obrigados a implementar os metodos dessa classe

    @Autowired
    private UsuarioRepository usuarioRepository; //injetandoa dependencia aqui na classe para usar nesse metodo abaxio
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }
}
