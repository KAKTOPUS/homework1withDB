package animals;

public abstract class Animal {
    private String name = " ";
    private int age = 0;
    private int weight = 0;
    private String color = " ";

    Animal(String name, int age, int weight, String color) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = color;
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
}