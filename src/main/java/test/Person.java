package test;

abstract class Person implements Animal{

    @Override
    public void eat() {
        System.out.println("eat");
    }

    public void sleep(){
        System.out.println("sleep");
    }
}
