package br.com.waiterapp.application.services.impl;

import br.com.waiterapp.application.domain.category.Category;
import br.com.waiterapp.application.dtos.category.CategoryRequestDto;
import br.com.waiterapp.application.dtos.category.CategoryResponseDto;
import br.com.waiterapp.application.mappers.category.CategoryMapper;
import br.com.waiterapp.application.repositories.CategoryRepository;
import br.com.waiterapp.application.services.CategoryService;
import br.com.waiterapp.application.services.exceptions.EntityAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl  implements CategoryService {

    private final CategoryRepository repository;

    @Override
    @Transactional
    public CategoryResponseDto create(CategoryRequestDto dto) {
       var categoryAlreadyExists = repository.findCategoriesByName(dto.getName());
       if(categoryAlreadyExists.isPresent()) throw new EntityAlreadyExistsException("This name is already taken!");
       var categoryIconAlreadyExists = repository.findByIcon(dto.getIcon());
       if(categoryIconAlreadyExists.isPresent()) throw new EntityAlreadyExistsException("This icon is already taken!");
       Category createdCategory = CategoryMapper.Instance.mapDtoToEntity(dto);
       repository.save(createdCategory);
       return CategoryMapper.Instance.mapCategoryEntityToDto(createdCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDto> findAll() {
        return repository.findAll().stream().map(CategoryMapper.Instance::mapCategoryEntityToDto).toList();
    }

    @Cacheable("categories")
    @Transactional
    public List<CategoryResponseDto> findAllCached(){
        return findAll();
    }
}
