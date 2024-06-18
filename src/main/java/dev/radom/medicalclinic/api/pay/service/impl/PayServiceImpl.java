package dev.radom.medicalclinic.api.pay.service.impl;

import dev.radom.medicalclinic.api.pay.dto.AddPayDTO;
import dev.radom.medicalclinic.api.pay.dto.PayDTO;
import dev.radom.medicalclinic.api.pay.dto.PayListDTO;
import dev.radom.medicalclinic.api.pay.dto.UpdatePayDTO;
import dev.radom.medicalclinic.api.pay.mapper.PayMapper;
import dev.radom.medicalclinic.api.pay.model.Pay;
import dev.radom.medicalclinic.api.pay.repository.PayRepository;
import dev.radom.medicalclinic.api.pay.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    private final PayRepository payRepository;
    private final PayMapper payMapper;
    @Override
    public void save(AddPayDTO addPayDTO) {
        Pay pay = payMapper.mapAddPayDTOToPay(addPayDTO);
        payRepository.save(pay);
    }

    @Override
    public PayDTO findById(UUID payId) {
        return payRepository.findById(payId)
                .map(payMapper::mapToPayDTO)
                .orElseThrow(() -> new IllegalArgumentException("Pay with ID " + payId + " not found"));
    }


    @Override
    public List<PayListDTO> findAll() {
        return payMapper.mapToPayListDTO(payRepository.findAll());
    }

    @Override
    public void update(UUID payId, UpdatePayDTO updatePayDTO) {
        Pay pay = payRepository.findById(payId)
                .orElseThrow(() -> new IllegalArgumentException("Pay with ID " + payId + " not found"));
        payMapper.mapUpdatePayDtoToPay(pay,updatePayDTO);
        payRepository.save(pay);
    }

    @Override
    public void delete(UUID payId) {
        if (!payRepository.existsById(payId)) {
            throw new IllegalArgumentException("Pay with ID " + payId + " not found");
        }
        payRepository.deleteById(payId);
    }
}
