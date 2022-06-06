package com.rsd.service;

import com.rsd.bean.Calculator;
import com.rsd.util.JDBCUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalculatorService {

    private final Logger logger = Logger.getLogger(CalculatorService.class);


    public Float oper(Calculator calculator) {
        Float result = null;

        Float count1 = Float.parseFloat(calculator.getNumber1());
        Float count2 = Float.parseFloat(calculator.getNumber2());

        switch (calculator.getOperator()) {
            case "+":
                result = count1 + count2;
                break;
            case "-":
                result = count1 - count2;
                break;
            case "*":
                result = count1 * count2;
                break;
            case "/":
                result = count1 / count2;
                break;
        }

        return result;
    }

    public List<String> querryAllRecord() {
        List<String> list = new ArrayList<>();

        Connection connection = null;
        try {
            connection = JDBCUtil.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from bu_calculator order by id desc ");

            while (rs.next()) {
//                int id = rs.getInt("id");
                String history = rs.getString("history");

                list.add(history);
            }

        } catch (SQLException e) {
            logger.error("SQL系统计算器查询1异常！" + e.getMessage());
        }

        return list;
    }

    public List<Calculator> querryAllRecord2() {
        List<Calculator> list = new ArrayList<>();

        Connection connection = null;
        try {
            connection = JDBCUtil.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from bu_log order by id desc ");


            ResultSetMetaData metaData = rs.getMetaData();

            while (rs.next()) {
                Calculator newInstance = (Calculator) Class.forName("com.rsd.bean.Calculator").newInstance();

                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);

                    BeanUtils.setProperty(newInstance, columnName, value);
                }
                list.add(newInstance);
            }


//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String number1 = rs.getString("number1");
//                String operator = rs.getString("operator");
//                String number2 = rs.getString("number2");
//                String result = rs.getString("result");
//
//                Calculator calculator = new Calculator();
//                calculator.setNumber1(number1);
//                calculator.setOperator(operator);
//                calculator.setNumber2(number2);
//                calculator.setResult(result);
//
//                list.add(calculator);
//
//            }

        } catch (SQLException e) {
            logger.error("SQL系统计算器查询2异常！" + e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error("SQL系统计算器beanutil异常！" + e.getMessage());
        } catch (InstantiationException e) {
            logger.error("SQL系统计算器beanutil异常！" + e.getMessage());
        } catch (IllegalAccessException e) {
            logger.error("SQL系统计算器beanutil异常！" + e.getMessage());
        } catch (InvocationTargetException e) {
            logger.error("SQL系统计算器beanutil异常！" + e.getMessage());
        }

        return list;
    }

    public void insert(Calculator calculator) {
        String record = calculator.getNumber1() + " " +
                calculator.getOperator() + " " + calculator.getNumber2() + " = " + calculator.getResult();
        logger.debug(record);

        String sql = "insert into bu_calculator values (null,'" + record + "')";
        JDBCUtil.execute(sql);
    }

    public void insert2(Calculator calculator) {
        String sql = "insert into bu_log values (null," + calculator.getNumber1() + ",'" + calculator.getOperator() + "'," + calculator.getNumber2() + "," + calculator.getResult() + ")";
        logger.debug("--------------------------------" + sql);

        JDBCUtil.execute(sql);
    }

    public void clear() {
        String sql = "delete from bu_calculator";
        JDBCUtil.execute(sql);
    }

    public void clear2() {
        String sql = "delete from bu_log";
        JDBCUtil.execute(sql);
    }


}
