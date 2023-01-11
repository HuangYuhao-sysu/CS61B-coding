public class TestBody {
    public static void main(String[] args) {
        Body a = new Body(1.0, 5.0, 10.0, 1.0, 5.0, "test1");
        Body b = new Body(2.0, 14.0, 13.0, -5.0, 70.0, "test2");
        System.out.print("The force between a and b is "+a.calcForceExertedBy(b));
    }
}
