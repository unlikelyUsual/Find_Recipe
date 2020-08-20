package com.example.recipe.bootstrap;

import com.example.recipe.domain.Category;
import com.example.recipe.domain.UnitOfMeasure;
import com.example.recipe.repositories.CategoryRepository;
import com.example.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Profile({"dev","prod"})
@Slf4j
public class RecipeDataLoaderMySql implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeDataLoaderMySql(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if(categoryRepository.count() == 0){
            log.debug("Inserting Categories");
            insertCategories();
        }

        if(unitOfMeasureRepository.count() == 0){
            log.debug("Inserting UOMs");
            insertUom();
        }

    }

    void insertCategories(){
        Category american = new Category("American");
        Category mexican = new Category("Mexican");
        Category italian = new Category("Italian");
        Category fastFood = new Category("Fast Food");
        categoryRepository.save(american);
        categoryRepository.save(mexican);
        categoryRepository.save(italian);
        categoryRepository.save(fastFood);
    }

    void insertUom(){
        UnitOfMeasure teaspoon = new UnitOfMeasure("Teaspoon");
        UnitOfMeasure tablespoon = new UnitOfMeasure("Tablespoon");
        UnitOfMeasure cup = new UnitOfMeasure("Cup");
        UnitOfMeasure pinch = new UnitOfMeasure("pinch");
        unitOfMeasureRepository.save(teaspoon);
        unitOfMeasureRepository.save(tablespoon);
        unitOfMeasureRepository.save(cup);
        unitOfMeasureRepository.save(pinch);
    }

}
