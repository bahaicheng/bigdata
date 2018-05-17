package com.bahc.saprk.javasql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

import java.util.Iterator;

public class SparkSQlJava {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().set("spark.driver.host", "localhost");
        SparkSession spark = SparkSession.builder().appName("").config(conf).master("local").getOrCreate();

        Encoder<Employee> encoder = Encoders.bean(Employee.class);

        Dataset<Employee> as = spark.read().json("D:\\data\\employees.json").as(encoder);
        as.show();

        Dataset<String> stringDataset = as.flatMap(new FlatMapFunction<Employee, String>() {
            @Override
            public Iterator<String> call(Employee employee) throws Exception {
                return null;
            }
        }, Encoders.STRING());
    }
}

class Employee {
    public String name;
    public Integer newsalary;

    public Employee(){}

    public Employee(String name,Integer salary){
        this.name=name;
        this.newsalary=salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return newsalary;
    }

    public void setSalary(Integer salary) {
        this.newsalary = salary;
    }
}
