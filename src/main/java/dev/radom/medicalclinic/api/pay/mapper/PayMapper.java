package dev.radom.medicalclinic.api.pay.mapper;

import dev.radom.medicalclinic.api.pay.dto.AddPayDTO;
import dev.radom.medicalclinic.api.pay.dto.PayDTO;
import dev.radom.medicalclinic.api.pay.dto.PayListDTO;
import dev.radom.medicalclinic.api.pay.dto.UpdatePayDTO;
import dev.radom.medicalclinic.api.pay.model.Pay;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PayMapper {
    Pay mapAddPayDTOToPay(AddPayDTO addPayDTO);
    void mapUpdatePayDtoToPay(@MappingTarget Pay pay, UpdatePayDTO updatePayDTO);
    List<PayListDTO> mapToPayListDTO(List<Pay> pays);
    PayDTO mapToPayDTO(Pay pay);
}
