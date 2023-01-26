package voll.med.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import voll.med.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(
    @NotBlank(message = "Nome é obrigatório")
    String nome,

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    String email,

    @NotBlank(message = "CRM é obrigatório")
    @Pattern(regexp = "\\d{4,6}", message = "CRM deve ter entre 4 e 6 dígitos")
    String crm,

    @NotNull(message = "Especialidade é obrigatório")
    EnumEspecialidade especialidade,

    @NotBlank(message = "Telefone é obrigatório")
    String telefone,

    @NotNull
    @Valid
    DadosEndereco endereco
) {
}
