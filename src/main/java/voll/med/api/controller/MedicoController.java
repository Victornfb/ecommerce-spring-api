package voll.med.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import voll.med.api.endereco.Endereco;
import voll.med.api.medico.DadosCadastroMedico;
import voll.med.api.medico.DadosListagemMedico;
import voll.med.api.medico.Medico;
import voll.med.api.medico.MedicoRepository;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(
        @RequestBody
        @Valid
        DadosCadastroMedico dados
    ) {
        this.repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(
        @PageableDefault(size = 10, sort = {"nome"})
        Pageable paginacao
    ) {
        return this.repository
            .findAll(paginacao)
            .map(DadosListagemMedico::new);
    }

}
