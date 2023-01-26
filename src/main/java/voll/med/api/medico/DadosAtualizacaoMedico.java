package voll.med.api.medico;

import voll.med.api.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
    String nome,
    String telefone,
    DadosEndereco endereco
) {
}
