package example;

class GP {

    int houseNum;
    String surName;

    public GP() {
        System.out.println("Default constructor: GP class created");
    }


    public GP(String surName, int houseNum) {
        this.surName = surName;
        this.houseNum = houseNum;
        System.out.println("Param constructor: GP class");
    }

    public void gpOne() {
        System.out.println("gpOne");
        System.out.println("Surname: " + surName);
        gpTwo();
    }

    public void gpTwo() {
        System.out.println("gpTwo");
        System.out.println("HouseNumber: " + houseNum);
    }
}

class Parent extends GP {

    int carNum;
    boolean isIndian;

    public Parent() {
        System.out.println("Default constructor: Parent class created");
    }

    public Parent(int carNum, boolean isIndian) {
        super("Chirammal", 19);
        this.carNum = carNum;
        this.isIndian = isIndian;
        System.out.println("Param constructor: Parent class");
    }

    public void parentOne() {
        super.gpOne();
        System.out.println("parentOne");
        System.out.println("Car Number: " + carNum);
        parentTwo();
    }

    public void parentTwo() {
        System.out.println("parentTwo");
        System.out.println("Indian: " + isIndian);
    }

}

class Child extends Parent {

    private int id;
    private String name;

    public Child() {
        System.out.println("Default constructor: Child class created");
    }

    public Child(int id, String name) {
        super(3333, true);
        this.id = id;
        this.name = name;
        System.out.println("Parameterized constructor: Child class");
        this.childOne();
    }

    public void childOne() {
        super.parentOne();
        System.out.println("childOne");
        System.out.println("ID: " + id);
        childTwo();
    }

    public void childTwo() {
        System.out.println("childTwo");
        System.out.println("Name:" + name);
    }
}
