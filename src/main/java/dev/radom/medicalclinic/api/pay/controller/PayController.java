package dev.radom.medicalclinic.api.pay.controller;

import dev.radom.medicalclinic.api.pay.dto.AddPayDTO;
import dev.radom.medicalclinic.api.pay.dto.PayDTO;
import dev.radom.medicalclinic.api.pay.dto.PayListDTO;
import dev.radom.medicalclinic.api.pay.dto.UpdatePayDTO;
import dev.radom.medicalclinic.api.pay.service.PayService;
import dev.radom.medicalclinic.pagination.PayloadApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pays")
public class PayController {

    private final PayService payService;

    @GetMapping
    public PayloadApi<List<PayListDTO>> getPayLists() {
        List<PayListDTO> pays = payService.findAll();
        return new PayloadApi<>(true, 200, "Successfully retrieved all pays.", null, pays);
    }

    @GetMapping("/{payId}")
    public ResponseEntity<PayloadApi<PayDTO>> findPayById(@PathVariable UUID payId) {
        PayDTO pay = payService.findById(payId);
        return ResponseEntity.ok(new PayloadApi<>(true, 200, "Successfully retrieved pay.", null, pay));
    }

    @PostMapping
    public ResponseEntity<PayloadApi<Void>> createPay(@RequestBody AddPayDTO addPayDTO) {
        payService.save(addPayDTO);
        return ResponseEntity.status(201).body(new PayloadApi<>(true, 201, "Successfully created pay.", null, null));
    }

    @PutMapping("/{payId}")
    public ResponseEntity<PayloadApi<Void>> updatePay(@PathVariable UUID payId, @RequestBody UpdatePayDTO updatePayDTO) {
        payService.update(payId, updatePayDTO);
        return ResponseEntity.ok(new PayloadApi<>(true, 200, "Successfully updated pay.", null, null));
    }

    @DeleteMapping("/{payId}")
    public ResponseEntity<PayloadApi<Void>> deletePay(@PathVariable UUID payId) {
        payService.delete(payId);
        return ResponseEntity.ok(new PayloadApi<>(true, 200, "Successfully deleted pay.", null, null));
    }
}
