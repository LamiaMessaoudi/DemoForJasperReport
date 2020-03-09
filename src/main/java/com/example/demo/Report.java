package com.example.demo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Report {
    public ArrayList<String> parameters;
    public ArrayList<String> fields;
    public String query;
}
