package med.voll.api.paciente;

import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import med.voll.api.endereco.DadosEndereco;

public record DadosListagemPaciente(String nome,String email, String cpf){

            public DadosListagemPaciente(Paciente paciente){
            this(paciente.getNome(),paciente.getEmail(),paciente.getCpf());

            }
}
