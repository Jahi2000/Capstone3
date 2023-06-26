package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

@RestController
// add the annotation to make this controller the endpoint for the following url
    // http://localhost:8080/categories
@RequestMapping("categories")
@CrossOrigin
public class CategoriesController
{
    private CategoryDao categoryDao;
    private ProductDao productDao;


    @Autowired
    public CategoriesController(CategoryDao CategoryDao , ProductDao ProductDao){
        this.categoryDao = CategoryDao;
        this.productDao = ProductDao;
    }

    @GetMapping("")
    // add the appropriate annotation for a get action
    public List<Category> getAll()
    {
      return this.categoryDao.getAllCategories();
    }

    @GetMapping("{id}")
    // add the appropriate annotation for a get action
    public Category getById(@PathVariable int id)
    {
       return this.categoryDao.getById(id);
    }

    // the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products
    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
         return this.productDao.listByCategoryId(categoryId);

    }


    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Category addCategory(@RequestBody Category category)
    {
        try
        {
            return categoryDao.create(category);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }


    // add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateCategory(@PathVariable int id, @RequestBody Category category) {
        try
        {
            categoryDao.update(id,category);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }

    }


    // add annotation to call this method for a DELETE action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCategory(@PathVariable int id)
    {
        try
        {
            var product = categoryDao.getById(id);

            if(product == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            categoryDao.delete(id);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}
