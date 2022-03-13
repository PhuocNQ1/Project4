package com.example.estateagency1.Repository;

import com.example.estateagency1.Models.Property;
import com.example.estateagency1.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property,Integer> {
    List<Property> getAllByUser(User user);

    Page<Property> findAll(Pageable pageable);
    
    Optional<Property> getPropertyById(Integer id);

//
    Page<Property> findAllByUser(Pageable pageable,User user);
//
    Page<Property> findByNameContaining(Pageable pageable,String name);


}
