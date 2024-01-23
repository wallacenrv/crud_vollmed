package med.voll.api.domain.medico;

public record DadosListagemMedico(

        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {

    public DadosListagemMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(),medico.getEspecialidade());
    }
}
// aqui estap os dados que vamos devolver para o cliente da aplicacao
