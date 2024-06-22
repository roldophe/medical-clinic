package dev.radom.medicalclinic.base;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface BaseMapper<T, U> {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    T mapAddNewDtoToEntity(U dto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    void updateEntityWithDto(@MappingTarget T entity, U dto);
}
