package com.example.demo;

import org.eclipse.jdt.core.dom.NullLiteral;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Null;
import java.util.List;

@RepositoryRestResource
public interface RepositoryRequette  {
   // @Query("SELECT table_name FROM information_schema.tables WHERE table_schema = 'public' ORDER BY table_name")
    public List<String> gettAllTables();

}
