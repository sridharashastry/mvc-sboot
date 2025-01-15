package com.bring.labs.repository;


import com.bring.labs.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AuthUser, Integer> {

    AuthUser findByName(String name);


}
