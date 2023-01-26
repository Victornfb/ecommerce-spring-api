package voll.med.api.medico;

import voll.med.api.endereco.DadosEndereco;

public record CadastroMedicoDTO(
        String nome,
        String email,
        String crm,
        EnumEspecialidade especialidade,
        DadosEndereco endereco
) {
}
