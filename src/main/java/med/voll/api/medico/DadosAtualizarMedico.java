package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosEndereco;

public record DadosAtualizarMedico(

        @NotNull
        Long id,
        String telefone,
        String nome,
        DadosEndereco endereco


        //sao todos opcionais com excecao do Id
) {
}
