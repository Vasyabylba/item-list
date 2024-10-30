package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.clevertec.dto.ItemRequest;
import ru.clevertec.dto.ItemResponse;
import ru.clevertec.entity.Item;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    ItemResponse toItemResponse(Item item);

    Item toItem(ItemRequest itemRequest);

    Item updateWithNull(ItemRequest itemRequest, @MappingTarget Item item);
}
