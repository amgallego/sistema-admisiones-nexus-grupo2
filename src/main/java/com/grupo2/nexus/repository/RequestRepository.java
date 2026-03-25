package com.grupo2.nexus.repository;

import com.grupo2.nexus.model.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository <Request,Long>{
}
