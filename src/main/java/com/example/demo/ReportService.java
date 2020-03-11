package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    RepositoryRequette repositoryRequette;

public List<String> gettAllTables()
{
   return repositoryRequette.gettAllTables() ;
}
}
