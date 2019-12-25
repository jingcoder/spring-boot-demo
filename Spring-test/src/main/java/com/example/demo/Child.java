package com.example.demo;

/**
 * @Author xu.xiaojing
 * @Date 2019/2/14 11:09
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public class Child extends Parent{
    private String name;

    private String adddress;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getAdddress() {
        return adddress;
    }

    public void setAdddress(String adddress) {
        this.adddress = adddress;
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.setName("aa");

        System.out.println(child.getName());
        System.out.println(child.name);
    }
}
