package com.nttdata.dockerized.postgresql.repository.ProductRepository;

import com.nttdata.dockerized.postgresql.model.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductRepositoryEmImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Product save(Product product) {
        em.persist(product);
        return product;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(em.find(Product.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    @Override
    public Product update(Product product) {
        return em.merge(product);
    }

    @Override
    public void deleteById(Long id) {
        Product ref = em.find(Product.class, id);
        if (ref != null) {
            em.remove(ref);
        }
    }

    @Override
    public List<Product> findByCategoryName(String categoryName) {
        return em.createQuery(
                        "SELECT p FROM Product p WHERE p.category.name = :name",
                        Product.class
                ).setParameter("name", categoryName)
                .getResultList();
    }
}