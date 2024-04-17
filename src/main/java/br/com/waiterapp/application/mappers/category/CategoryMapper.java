package br.com.waiterapp.application.mappers.category;

import br.com.waiterapp.application.domain.category.Category;
import br.com.waiterapp.application.dtos.category.CategoryRequestDto;
import br.com.waiterapp.application.dtos.category.CategoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper Instance = Mappers.getMapper(CategoryMapper.class);


    CategoryResponseDto mapCategoryEntityToDto(Category category);
    @Mapping(target = "id", ignore = true)
    @Mapping(target="createdAt", ignore = true)
    @Mapping(target="updatedAt", ignore = true)
    Category mapDtoToEntity(CategoryRequestDto requestDto);
}
