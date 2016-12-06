package backend;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jorge
 */
public abstract class Employee {

    private String uid;
    private String name;
    private String address;
    private String phoneNumber;

    private String salary;

    public Employee(String uid, String name, String phoneNumber, String address, String salary) {
        this.uid = uid;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.salary = salary;
    }

    public Employee(String uid, String name, String address, String phoneNumber) {
        this.uid = uid;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;

    }

    public boolean updatePersonalInfo(String fieldToUpdate, String newInfo) {
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "uid=" + uid + ", name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
