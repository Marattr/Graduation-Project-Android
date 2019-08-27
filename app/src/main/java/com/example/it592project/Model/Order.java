package com.example.it592project.Model;

import java.io.Serializable;

public class Order implements Serializable {
    private String customerNameLastname;
    private String customerPhone;
    private String date;
    private String doctorCode;
    private String doctorName;
    private String hospital;
    private int id;
    private String orderdate;
    private String paymentType;
    private String saleType;
    private double totalbill;

    public Order(){

    }

    public Order(String customerNameLastname, String customerPhone, String date, String doctorCode, String doctorName, String hospital, int id, String orderdate, String paymentType, String saleType, double totalbill) {
        this.customerNameLastname = customerNameLastname;
        this.customerPhone = customerPhone;
        this.date = date;
        this.doctorCode = doctorCode;
        this.doctorName = doctorName;
        this.hospital = hospital;
        this.id = id;
        this.orderdate = orderdate;
        this.paymentType = paymentType;
        this.saleType = saleType;
        this.totalbill = totalbill;
    }

    public String getCustomerNameLastname() {
        return customerNameLastname;
    }

    public void setCustomerNameLastname(String customerNameLastname) {
        this.customerNameLastname = customerNameLastname;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }



    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalbill() {
        return totalbill;
    }

    public void setTotalbill(double totalbill) {
        this.totalbill = totalbill;
    }
}
