package voll.med.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import voll.med.api.medico.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(
        @RequestBody
        @Valid
        DadosCadastroMedico dados,
        UriComponentsBuilder uriBuilder
    ) {
        Medico medico = new Medico(dados);
        this.repository.save(medico);

        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(
        @PageableDefault(size = 10, sort = {"nome"})
        Pageable paginacao
    ) {
        Page<DadosListagemMedico> result = this.repository
            .findAllByAtivoTrue(paginacao)
            .map(DadosListagemMedico::new);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarPorId(
        @PathVariable
        Long id
    ) {
        Medico medico = this.repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(
        @PathVariable
        Long id,
        @RequestBody
        @Valid
        DadosAtualizacaoMedico dados
    ) {
        Medico medico = this.repository.getReferenceById(id);
        medico.atualizarDados(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(
        @PathVariable
        Long id
    ) {
        Medico medico = this.repository.getReferenceById(id);
        medico.exluir();
        return ResponseEntity.noContent().build();
    }

}
