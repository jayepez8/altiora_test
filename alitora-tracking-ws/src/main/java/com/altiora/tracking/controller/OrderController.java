package com.altiora.tracking.controller;

import com.altiora.tracking.client.service.IOrderService;
import com.altiora.tracking.vo.CustomerVo;
import com.altiora.tracking.vo.OrderVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

import static com.altiora.tracking.client.common.TrackingConstants.V1_API_VERSION;

/**
 * @author jyepez on 8/9/2024
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("order" + V1_API_VERSION)
public class OrderController {

    private final IOrderService orderService;

    /**
     * Create order
     *
     * @param orderVo OrderVo
     * @return OrderVo
     */
    @PostMapping()
    public ResponseEntity<OrderVo> create(@RequestBody @Valid OrderVo orderVo){
        OrderVo response =this.orderService.create(orderVo);
        return ResponseEntity.ok().body(response);
    }

    /**
     * Find All Orders
     *
     * @return Collection OrderVo
     */
    @GetMapping()
    public ResponseEntity<Collection<OrderVo>> findAll() {
        Collection<OrderVo> response = this.orderService.findAll();
        return ResponseEntity.ok().body(response);
    }

    /**
     * Find Order By OrderCode
     *
     * @param orderCode String
     * @return OrderVo
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderVo> findByOrderCode(@PathVariable("id") String orderCode){
        OrderVo response = this.orderService.findByOrderCode(orderCode);
        return ResponseEntity.ok().body(response);
    }
}
