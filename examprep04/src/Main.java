public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public class Dog {
        public Dog() {

        }

        public void bark(Dog d) {

        }
    }

    public class Corgi extends Dog {
        public Corgi(){ /* C1 */ }
        public void bark(Corgi c) { /* Method B */ }
        @Override
        public void bark(Dog d) { /* Method C */ }
        public void play(Dog d) { /* Method D */ }
        public void play(Corgi c) { /* Method E */ }
    }
}