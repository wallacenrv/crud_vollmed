package med.voll.api.medico;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

//mapeando entidades
@Table(name = "medicos")
@Entity(name = "Medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded // pra ele ficar numa classe separada, mas no banco siginifca quw eta na mesma tabela
    private Endereco endereco;
    private String telefone;
    private boolean ativo;


    // criei um construtor para receber dados
    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm= dados.crm();
        this.endereco = new Endereco(dados.endereco()); // criei um construtor la na classe endereco
        this.especialidade= dados.especialidade();
    }

    public void atualizarInformacoes(DadosAtualizarMedico dados) {

        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.telefone() != null){
            this.telefone= dados.telefone();
        }
        if(dados.endereco() != null)
        this.endereco.atualizarInformacoes(dados.endereco());
    }


    public void excluir() {
        this.ativo = false;
    }
}

