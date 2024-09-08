package com.altiora.tracking.controller;

import com.altiora.tracking.client.service.IItemService;
import com.altiora.tracking.vo.CustomerVo;
import com.altiora.tracking.vo.ItemVo;
import com.altiora.tracking.vo.NextCodeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

import static com.altiora.tracking.client.common.TrackingConstants.V1_API_VERSION;

/**
 * @author jyepez on 7/9/2024
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("item" + V1_API_VERSION)
public class ItemController {

    private final IItemService itemService;

    /**
     * Create Item
     *
     * @param itemVo ItemVo
     * @return ItemVo
     */
    @PostMapping()
    public ResponseEntity<ItemVo> create(@RequestBody @Valid ItemVo itemVo){
        ItemVo response =this.itemService.create(itemVo);
        return ResponseEntity.ok().body(response);
    }

    /**
     * Find All Items
     *
     * @return Collection ItemVo
     */
    @GetMapping()
    public ResponseEntity<Collection<ItemVo>> findAll() {
        Collection<ItemVo> response = this.itemService.findAll();
        return ResponseEntity.ok().body(response);
    }

    /**
     * Find Item By ItemCode
     *
     * @param itemCode String
     * @return ItemVo
     */
    @GetMapping("/{id}")
    public ResponseEntity<ItemVo> findByItemCode(@PathVariable("id") String itemCode){
        ItemVo response = this.itemService.findByItemCode(itemCode);
        return ResponseEntity.ok().body(response);
    }

    /**
     * Get Next Item Code
     *
     * @return String
     */
    @GetMapping("/getNextItemCode")
    public ResponseEntity<NextCodeVo> getNextItemCode(){
        NextCodeVo response = this.itemService.getNextItemCode();
        return ResponseEntity.ok().body(response);
    }
}
