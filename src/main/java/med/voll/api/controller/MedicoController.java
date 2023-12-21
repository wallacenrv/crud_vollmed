package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.DadosAtualizarMedico;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos") //esta mapeando a url medicos
public class MedicoController {


  /*  @PostMapping // requisicao post
    public void cadastrar(@RequestBody String json){ // @ResquestBody a requicao vem do corpo
        System.out.println(json);
    }*/

    // injecao de dependecia, ele ira instanciar para nos
    @Autowired
    private MedicoRepository repository;

    @PostMapping // requisicao post
    @Transactional // transacao ativa com o banco de dados
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){ // @ResquestBody a requicao vem do corpo
        repository.save(new Medico(dados)); // convertendo a string para objeto / criei um contrutor dentro da entidade medico
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size=10, sort ={"nome"} ) Pageable paginacao) { // fazendo a paginacao
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico :: new);
    }
// ele esta retornado a entidade medico, preciso transformar para DadosListagemMedico
//http://localhost:8080/medicos?size=1 para controlar o numero de registros
    //http://localhost:8080/medicos?size=1&page=1 qual a pagina que eu quer ocarregar
//


    //ordenacao
    // pelo nome : http://localhost:8080/medicos?sort=nome
    // http://localhost:8080/medicos?sort=crm,desc
    //http://localhost:8080/medicos?sort=crm,desc&size=2&page=1

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

    }
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
    medico.excluir();

    }
    // aqui é uma exclusao de verdade
    // o id ta vindo na url @PathVariable





}
