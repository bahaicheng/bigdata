package com.bahc.flink.fsql;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.table.api.TableEnvironment;

public class RegisterTableSource {
    public static void main(String[] args) {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        TableEnvironment TblEnv = TableEnvironment.getTableEnvironment(env);

        try {
            env.execute("execute_flink");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
