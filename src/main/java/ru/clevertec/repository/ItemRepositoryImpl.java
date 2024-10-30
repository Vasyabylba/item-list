package ru.clevertec.repository;

import ru.clevertec.entity.Item;

import java.util.List;
import java.util.Optional;

public class ItemRepositoryImpl extends CrudRepositoryImpl<Item, Long> implements ItemRepository {
    private static final ItemRepositoryImpl INSTANCE = new ItemRepositoryImpl();

    public static ItemRepositoryImpl getInstance() {
        return INSTANCE;
    }

    private ItemRepositoryImpl() {
        super(Item.class);
    }

    @Override
    public <S extends Item> S save(S entity) {
        return super.save(entity);
    }

    @Override
    public <S extends Item> S update(S entity) {
        return super.update(entity);
    }

    @Override
    public Optional<Item> findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public List<Item> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }
}
