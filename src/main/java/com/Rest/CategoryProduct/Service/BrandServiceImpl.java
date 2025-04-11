package com.Rest.CategoryProduct.Service;

import com.Rest.CategoryProduct.Entity.Brand;
import com.Rest.CategoryProduct.Repositories.BrandRepositories;
/*import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService{

    @Autowired
    private BrandRepositories brandRepo;

//    @PersistenceContext
//    private EntityManager entityManager;

    @Override
    public Brand getBrandById(Long id) {
        Optional<Brand> findById = brandRepo.findById(id);

        if (findById.isPresent()){
            return findById.get();
        }

        return null;
    }

    @Override
    public List<Brand> getAllBrand() {
        return brandRepo.findAll();
    }

//    @Override
//    public String insertBrand(String name, Long categoryId) {
//        return "Data inserted successfully";
//    }

    @Override
    public String insertBrand(Brand brand) {
        Optional<Brand> brandExist = brandRepo.findByBrandName(brand.getBrandName());
        if(brandExist.isPresent()){
            return "Brand already exists";
        }
        brandRepo.save(brand);
        return "Data inserted successfully";
    }


    @Override
    public String updateBrand(Brand brand, Long id) {
        Optional<Brand> brandExist = brandRepo.findById(id);
//        if(!brandExist.isPresent()){
//            return "There is no such brand";
//        }



        Brand brand1 = brandExist.get();
        brand1.setBrandName(brand.getBrandName());
        brandRepo.save(brand1);
        return "Data updated successfully";
    }

    @Override
    public String deleteBrand(Long id) {
        Optional<Brand> brandExist = brandRepo.findById(id);
        if(!brandExist.isPresent()){
            return "There is no such brand";
        }
        brandRepo.deleteById(id);
//        resetAutoIncrement();
        return "Brand deleted successfully";
    }

/*    @Transactional
    public void resetAutoIncrement(){
        entityManager.createNativeQuery("ALTER TABLE brand AUTO_INCREMENT = 1").executeUpdate();
    }*/
}

//                                          Status
//Add some custom finder methods  -         Already Done
//use logging framework : log4j, slf4j -
// use exception handling                   Done
// use @ControllerAdvice                    Done
//Unit testing
//User module and apply jwt or oAuth2       Done
//apply splunk
// Method level Authorization               Done