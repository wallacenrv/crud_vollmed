package med.voll.api.paciente;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.DadosEndereco;

@Table(name = "pacientes")
@Entity(name = "Pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean ativo;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private DadosEndereco endereco;



    // Estou criando um objeto recebendo as informacoes do DdosCadastropaciente
    public Paciente(DadosCadastroPaciente dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = dados.endereco();
    }

    public void atualizarInformacoes(@Valid DadosAtualizarPaciente dados) {

        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.telefone() != null){
            this.telefone= dados.telefone();
        }
        if(dados.endereco() != null)
            this.endereco.atualizarInformacoes(dados.endereco());
    }

    public void inativar() {
        this.ativo = false;
    }


}
