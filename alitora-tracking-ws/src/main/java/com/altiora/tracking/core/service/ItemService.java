package com.altiora.tracking.core.service;

import com.altiora.tracking.client.entity.ItemEntity;
import com.altiora.tracking.client.exception.ExistException;
import com.altiora.tracking.client.exception.NotFoundException;
import com.altiora.tracking.client.exception.PersistException;
import com.altiora.tracking.client.mapper.ItemMapper;
import com.altiora.tracking.client.repository.IItemRepository;
import com.altiora.tracking.client.service.IItemService;
import com.altiora.tracking.vo.ItemVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

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
        ItemEntity item = this.itemRepository.findByItemCode(itemCode)
                .orElseThrow(() -> new NotFoundException("No customer found with the identification " + itemCode));
        return this.itemMapper.toItemVo(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<ItemVo> findAll() {
        Collection<ItemEntity> items = this.itemRepository.findAll();
        return this.itemMapper.toCollectionItemVo(items);
    }
}
