package group5.eeet2580_project.controller;

import group5.eeet2580_project.dto.response.SprayOrderResponse;
import group5.eeet2580_project.entity.SprayOrder;
import group5.eeet2580_project.service.SprayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/spray-orders")
public class SprayOrderController {

    @Autowired
    private SprayOrderService sprayOrderService;

    @GetMapping
    public ResponseEntity<List<SprayOrderResponse>> getAllOrders() {
        List<SprayOrder> orders = sprayOrderService.getAllOrders();
        List<SprayOrderResponse> response = orders.stream()
                .map(order -> SprayOrderResponse.builder()
                        .id(order.getId())
                        .customerName(order.getUser().getUsername())
                        .orderDate(order.getDesiredDate())
                        .status(order.getStatus())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}