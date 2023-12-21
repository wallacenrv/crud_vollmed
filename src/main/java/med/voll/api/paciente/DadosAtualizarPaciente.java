package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosEndereco;

public record DadosAtualizarPaciente(

        @NotNull
        Long id,
        String telefone,
        String nome,
        @Valid DadosEndereco endereco
) {
}
