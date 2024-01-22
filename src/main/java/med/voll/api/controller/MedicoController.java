package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos") //Esta mapeando a url medicos
public class MedicoController {


  /*

  @PostMapping                                      // requisicao post
  public void cadastrar(@RequestBody String json){ // @ResquestBody a requicao vem do corpo
  System.out.println(json); }

  */

    // injecao de dependecia :  ele ira instanciar E
    @Autowired
    private MedicoRepository repository;


    //Padronizando código 201(um registro foi criado) devolve tambem um cabecalho do protocolo HTTP (LOCATION).
    @PostMapping // requisicao post 201
    @Transactional // transacao ativa com o banco de dados
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriComponentsBuilder) { // @ResquestBody a requicao vem do corpo  // UriComponentsBuilder cria a uri
        var medico = new Medico(dados);
        repository.save(medico); // convertendo a string para objeto / criei um contrutor dentro da entidade medico
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
        //return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) { // fazendo a paginacao
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);

    }
// ele esta retornado a entidade medico, preciso transformar para DadosListagemMedico
//http://localhost:8080/medicos?size=1 para controlar o numero de registros
    //http://localhost:8080/medicos?size=1&page=1 qual a pagina que eu quer ocarregar
//


    //ordenacao
    // pelo nome : http://localhost:8080/medicos?sort=nome
    // http://localhost:8080/medicos?sort=crm,desc
    //http://localhost:8080/medicos?sort=crm,desc&size=2&page=1

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados); // a JPA FAZ A ATUALIZAcao no banco sozinha.. pois a transacao esta ativa, não preciso salvar no banco.
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico)); // ao atualizar sempre é bom retornar
    }


    /*
    @DeleteMapping("/{id}") // abre e fecha chaves torna o id dinamico
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build(); // agora devolve o codigo 204
    }
    // aqui é uma exclusao de verdade
    // o id ta vindo na url @PathVariable

    */

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }


    @GetMapping("{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
       var medico = repository.getReferenceById(id);
       return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }



}
