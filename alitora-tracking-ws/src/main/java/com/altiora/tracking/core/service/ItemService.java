package com.altiora.tracking.core.service;

import com.altiora.tracking.client.common.TrackingUtils;
import com.altiora.tracking.client.entity.ItemEntity;
import com.altiora.tracking.client.exception.ExistException;
import com.altiora.tracking.client.exception.NotFoundException;
import com.altiora.tracking.client.exception.PersistException;
import com.altiora.tracking.client.mapper.ItemMapper;
import com.altiora.tracking.client.repository.IItemRepository;
import com.altiora.tracking.client.service.IItemService;
import com.altiora.tracking.vo.ItemVo;
import com.altiora.tracking.vo.NextCodeVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

import static com.altiora.tracking.client.common.TrackingConstants.PREFIX_ITEM;

/**
 * @author jyepez on 7/9/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService implements IItemService {

    private final IItemRepository itemRepository;
    private final ItemMapper itemMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ItemVo create(ItemVo itemVo) {
        if (this.itemRepository.existsByItemCode(itemVo.getItemCode())) {
            throw new ExistException("Error the item already exists");
        }
        try {
            ItemEntity item = this.itemRepository.save(this.itemMapper.toEntity(itemVo));
            return this.itemMapper.toItemVo(item);
        } catch (Exception e) {
            throw new PersistException("A problem occurred, the item could not be saved");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemVo findByItemCode(String itemCode) {
        ItemEntity item = findItemEntityByItemCode(itemCode);
        return this.itemMapper.toItemVo(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<ItemVo> findAll() {
        Collection<ItemEntity> items = this.itemRepository.findAllByOrderByCreateDateDesc();
        return this.itemMapper.toCollectionItemVo(items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NextCodeVo getNextItemCode() {
        String code = TrackingUtils.generateRandomStringCode(PREFIX_ITEM);
        return NextCodeVo.builder().code(code).build();
    }

    @Override
    public ItemEntity findItemEntityByItemCode(String itemCode) {
        return this.itemRepository.findByItemCode(itemCode)
                .orElseThrow(() -> new NotFoundException("No customer found with the identification " + itemCode));
    }

    @Override
    public void reduceStock(ItemEntity item) {
        this.itemRepository.save(item);
    }
}
