package med.voll.api.paciente;

import jakarta.persistence.Embedded;
import med.voll.api.endereco.DadosEndereco;

public record DadosDetalhamentoPaciente(String nome, String email, String telefone, String cpf, DadosEndereco endereco


) {
    public DadosDetalhamentoPaciente(Paciente paciente) {

        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());

    }
}
