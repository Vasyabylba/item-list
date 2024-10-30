package ru.clevertec.service;

import ru.clevertec.dto.ItemRequest;
import ru.clevertec.dto.ItemResponse;
import ru.clevertec.entity.Item;
import ru.clevertec.mapper.ItemMapper;
import ru.clevertec.repository.ItemRepository;
import ru.clevertec.repository.ItemRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class ItemServiceImpl implements ItemService {
    private static final ItemServiceImpl INSTANCE = new ItemServiceImpl();

    private final ItemRepository itemRepository = ItemRepositoryImpl.getInstance();

    public static ItemServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public ItemResponse getOne(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isEmpty()) return null;
        Item item = optionalItem.get();
        return ItemMapper.INSTANCE.toItemResponse(item);
    }

    @Override
    public ItemResponse create(ItemRequest itemRequest) {
        Item item = ItemMapper.INSTANCE.toItem(itemRequest);
        Item savedItem = itemRepository.save(item);
        return ItemMapper.INSTANCE.toItemResponse(savedItem);
    }

    @Override
    public ItemResponse update(Long id, ItemRequest itemRequest) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isEmpty()) return null;
        Item item = optionalItem.get();
        ItemMapper.INSTANCE.updateWithNull(itemRequest, item);
        Item savedItem = itemRepository.update(item);
        return ItemMapper.INSTANCE.toItemResponse(savedItem);
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<ItemResponse> getList() {
        List<Item> users = itemRepository.findAll();
        return users.stream()
                .map(ItemMapper.INSTANCE::toItemResponse)
                .toList();
    }
}
