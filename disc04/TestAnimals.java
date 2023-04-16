public class TestAnimals {
    public static void main(String[] args) {
        Animal a = new Animal("Pluto", 10);
        Cat c = new Cat("Garfield", 6);
        Dog d = new Dog("Fido", 4);
        //d = new Dog("Spot", 10);
        a.greet(); // Animal Pluto says: Huh?
        c.greet(); // Cat Garfield says: Meow!
        d.greet(); // Dog Fido says: WOOF!
        a = c;
        ((Cat) a).greet(); // Cat Pluto says: Huh? // Wrong, the right answer: Cat Garfield says: Meow!
        a.greet(); // Animal Pluto says: Huh? // Wrong, the right answer: Cat Garfield says: Meow!
    }

}


