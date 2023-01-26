package voll.med.api.domain.medico;

import voll.med.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
    String nome,
    String telefone,
    DadosEndereco endereco
) {
}
