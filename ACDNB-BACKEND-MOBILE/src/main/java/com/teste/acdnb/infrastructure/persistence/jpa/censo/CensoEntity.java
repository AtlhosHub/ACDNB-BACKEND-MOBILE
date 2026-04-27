package com.teste.acdnb.infrastructure.persistence.jpa.censo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "censo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CensoEntity {
    @Id
    @Schema(description = "Código do municipio")
    private String id;
    @Schema(description = "Nome do municipio")
    private String nome_municipio;
    @Schema(description = "Número de pessoas responsáveis em domicílios particulares permanentes ocupados")
    private Float num_responsaveis;
    @Schema(description = "Número de moradores em domicílios particulares permanentes ocupados")
    private Float num_habitantes;
    @Schema(description = "Variância do número de moradores em domicílios particulares permanentes ocupados")
    private Float var_num_habitantes;
    @Schema(description = "Valor do rendimento nominal médio mensal das pessoas responsáveis com rendimentos por domicílios particulares permanentes ocupados")
    private Float renda_media_responsavel;
    @Schema(description = "Variância do rendimento nominal mensal das pessoas responsáveis com rendimentos por domicílios particulares permanentes ocupados")
    private Float var_renda_responsavel;
    @Schema(description = "Coordenada de latitude do municipio")
    private Float latitude;
    @Schema(description = "Coordenada de longitude do municipio")
    private Float longitude;
    @Schema(description = "Data de inclusão do dado")
    private LocalDateTime dataInclusao;
}
