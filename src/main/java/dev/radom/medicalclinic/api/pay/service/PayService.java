package dev.radom.medicalclinic.api.pay.service;

import dev.radom.medicalclinic.api.pay.dto.AddPayDTO;
import dev.radom.medicalclinic.api.pay.dto.PayDTO;
import dev.radom.medicalclinic.api.pay.dto.PayListDTO;
import dev.radom.medicalclinic.api.pay.dto.UpdatePayDTO;

import java.util.List;
import java.util.UUID;
public interface PayService {
    void save(AddPayDTO addPayDTO);
    PayDTO findById(UUID payId);
    List<PayListDTO> findAll();
    void update(UUID payId,UpdatePayDTO updatePayDTO);
    void delete(UUID payId);
}
