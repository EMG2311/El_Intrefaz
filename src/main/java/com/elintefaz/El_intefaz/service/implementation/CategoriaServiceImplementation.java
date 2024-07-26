package com.elintefaz.El_intefaz.service.implementation;

import com.elintefaz.El_intefaz.dto.CategoryDto;
import com.elintefaz.El_intefaz.mapper.CategoryMapper;
import com.elintefaz.El_intefaz.model.Category;
import com.elintefaz.El_intefaz.repository.CategoryRepository;
import com.elintefaz.El_intefaz.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoriaServiceImplementation implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final static Logger LOGGER=  LoggerFactory.getLogger(CategoriaServiceImplementation.class);

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        try {
            LOGGER.info("----------Creando categoria" + categoryDto.getName() + "----------");
            categoryRepository.save(Category.builder().name(categoryDto.getName()).build());
            return categoryDto;
        }catch (Exception e){
            LOGGER.error("----------Error creando categoria "+ e.getMessage()+ "----------");
            return null;
        }
    }

    @Override
    public List<CategoryDto> toListCategory() {
        try{
            List<CategoryDto> categoryDtos = new ArrayList<>();
            LOGGER.info("----------Buscando todas las categorias----------");
            List<Category> categories = categoryRepository.findAll();
            for (Category c: categories){
                categoryDtos.add(CategoryDto.builder().name(c.getName()).build());
            }
            return categoryDtos;
        }catch (Exception e){
            LOGGER.error("----------Error buscando todas las categorias "+ e.getMessage() + "----------");
            return null;
        }
    }

    @Override
    public void deleteCategory(Integer id) {
        try{
            LOGGER.info("----------Buscando categoria a eliminar----------");
            Category category = categoryRepository.findById(id)
                    .orElseThrow(()->new EntityNotFoundException("El id "+ id+" de categoria no existe"));
            LOGGER.info("----------Eliminando categoria"+ category.getName() +"----------");
            categoryRepository.deleteById(id);
        }catch (EntityNotFoundException n){
            LOGGER.error(n.getMessage());
        }
        catch (Exception e){
            LOGGER.error("----------Error al eliminar categoria: "+id+ ", " +e.getMessage() +"----------");
        }




    }
}
