package com.teste.acdnb.infrastructure.persistence.jpa.censo;

import com.teste.acdnb.core.domain.censo.Censo;
import com.teste.acdnb.core.domain.shared.valueobject.DataInclusao;
import com.teste.acdnb.infrastructure.dto.censo.CensoDTO;
import org.springframework.stereotype.Component;

@Component
public class CensoEntityMapper {
    public static CensoEntity toEntity(Censo censo) {
        if (censo == null) return null;

        return new CensoEntity(
                censo.getId(),
                censo.getNome_municipio(),
                censo.getNum_responsaveis(),
                censo.getNum_habitantes(),
                censo.getVar_num_habitantes(),
                censo.getRenda_media_responsavel(),
                censo.getVar_renda_responsavel(),
                censo.getLatitude(),
                censo.getLongitude(),
                censo.getDataInclusao().getValue()
        );
    }

    public static Censo toDomain(CensoEntity censoEntity) {
        if (censoEntity == null) return null;

        return new Censo(
                censoEntity.getId(),
                censoEntity.getNome_municipio(),
                censoEntity.getNum_responsaveis(),
                censoEntity.getNum_habitantes(),
                censoEntity.getVar_num_habitantes(),
                censoEntity.getRenda_media_responsavel(),
                censoEntity.getVar_renda_responsavel(),
                censoEntity.getLatitude(),
                censoEntity.getLongitude(),
                DataInclusao.of(censoEntity.getDataInclusao())
        );
    }

    public static CensoDTO toDTO(CensoEntity censoEntity) {
        return new CensoDTO(
                censoEntity.getId(),
                censoEntity.getNome_municipio(),
                censoEntity.getNum_responsaveis(),
                censoEntity.getNum_habitantes(),
                censoEntity.getVar_num_habitantes(),
                censoEntity.getRenda_media_responsavel(),
                censoEntity.getVar_renda_responsavel(),
                censoEntity.getLatitude(),
                censoEntity.getLongitude()
                );
    }
}
