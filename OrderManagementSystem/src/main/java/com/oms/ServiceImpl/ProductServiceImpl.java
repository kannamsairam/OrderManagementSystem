package com.oms.ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.DTO.ProductDTO;
import com.oms.Entity.ProductPojo;
import com.oms.Entity.VendorPojo;
import com.oms.Repo.ProductRepo;
import com.oms.Service.ProductService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo pr;
	
	@Autowired
	private ModelMapper mm;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	//private final static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Override
	@Transactional
	public ProductDTO saveProduct(ProductDTO p) {
		//logger.info("I am at ProductServiceImpl saveProduct method started. "+p);
		ProductPojo pp = mm.map(p, ProductPojo.class);
		log.info(pp.toString());
		
		// Detach handling for OrderProcessPojo
        if (pp.getVpo() != null) {
            List<VendorPojo> detachedProcesses = new ArrayList<>();
            for (VendorPojo vendorPojo : pp.getVpo()) {
                if (vendorPojo.getVid() != null) {
                    // Merge existing entities
                    detachedProcesses.add(entityManager.merge(vendorPojo));
                } else {
                    // Persist new entities
                    entityManager.persist(vendorPojo);
                    detachedProcesses.add(vendorPojo);
                }
            }
            pp.setVpo(detachedProcesses);
        }

        // Use merge to handle detached entities
        if (pp.getPid() != null && pr.existsById(pp.getPid())) {
            pp = entityManager.merge(pp);
        } else {
            entityManager.persist(pp);
        }
        
        // Save the entity using entityManager to ensure proper state handling
        // Synchronize the persistence context with the database
        entityManager.flush();
		//pr.save(pp);
		//logger.info("I am at ProductServiceImpl saveProduct method completed. "+p);
		return p;
	}

	@Override
	public ProductDTO updateProduct(ProductDTO p) {
		//logger.info("I am at ProductServiceImpl updateProduct method started. "+p);
		ProductPojo pp = mm.map(p, ProductPojo.class);
		pr.save(pp);
		//logger.info("I am at ProductServiceImpl updateProduct method completed. "+p);
		return p;
	}

	@Override
	public Object deleteProduct(Integer pid) {
		//logger.info("I am at ProductServiceImpl deleteProduct method started. "+pid);
		pr.deleteById(pid);
		//logger.info("I am at ProductServiceImpl deleteProduct method completed. "+pid);
		return pid;
	}

	@Override
	public ProductDTO getByProductId(Integer pid) {
		//logger.info("I am at ProductServiceImpl getbyproductid method started. ");
		Optional<ProductPojo> k = pr.findById(pid);
		ProductPojo d = k.get();
		ProductDTO pd = mm.map(d, ProductDTO.class);
		log.info(pd.toString());
		//logger.info("I am at ProductServiceImpl getbyproductid method completed. ");
		return pd;
	}

	@Override
	public List<ProductDTO> getByProductName(String pname) {
		//logger.info("I am at ProductServiceImpl getByProductName method started. ");
		List<ProductPojo> k = pr.getByPname(pname);
		ProductDTO[] d = mm.map(k, ProductDTO[].class);
		List<ProductDTO> u = Arrays.asList(d);
		for(ProductDTO t : u) {
			log.info(t.toString());
		}
		//logger.info("I am at ProductServiceImpl getByProductName method completed. ");	
		return u;
	}

	@Override
	public List<ProductDTO> getAll() {
		//logger.info("I am at ProductServiceImpl getAll method started. ");
		List<ProductPojo> k = pr.findAll();
		ProductDTO[] d = mm.map(k, ProductDTO[].class);
		List<ProductDTO> y = Arrays.asList(d);
		for(ProductDTO t : y) {
			log.info(t.toString());
		}
		//logger.info("I am at ProductServiceImpl getAll method completed. ");
		return y;
	}
}