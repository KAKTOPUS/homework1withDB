package animals;

import data.AnimalTypeData;

public abstract class Animal {
    private AnimalTypeData data = null;
    private String name = " ";
    private int age = 0;
    private int weight = 0;
    private String color = " ";
    private int id = 0;

    protected Animal(String name, int age, int weight, String color, AnimalTypeData data) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = color;
        this.data = data;
    }

    protected Animal(int id, String name, int age, int weight, String color, AnimalTypeData data) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = color;
        this.data = data;
    }

    public void say() {
        System.out.println("Я говорю");
    }

    public void go() {
        System.out.println("Я иду");
    }

    public void drink() {
        System.out.println("Я пью");
    }

    public void eat() {
        System.out.println("Я ем");
    }

    public String toString() {
        return String.format("Привет! Меня зовут %s, мне %d %s, я вешу - %d кг, мой цвет - %s", name, age, getAgeCase(), weight, color);
    }

    private String getAgeCase() {
        if (age >= 11 && age <= 14) {
            return "лет";
        }
        int remainder = age % 10;

        if (remainder == 1) {
            return "год";
        }

        if (remainder >= 2 && remainder <=4) {
            return "года";
        }

        return "лет";
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    public AnimalTypeData getType() {
        return data;
    }

    public String returnId() {
        return "id = " + id;
    }

}