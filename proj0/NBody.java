public class NBody {
    public static double readRadius(String txt_name) {
        In in = new In(txt_name);

        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

    public static int readNumber(String txt_name) {
        In in = new In(txt_name);
        return in.readInt();
    }

    public static Body[] readBodies(String txt_name) {
        In in = new In(txt_name);

        int number = in.readInt();
        double radius = in.readDouble();

        Body[] bodies = new Body[number];

        for (int index = 0; index < bodies.length; index++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String planet = in.readString();
            bodies[index] = new Body(xP, yP, xV, yV, m, planet);
        }

        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        int number = readNumber(filename);
        Body[] bodies = new Body[number];
        bodies = readBodies(filename);

        StdDraw.enableDoubleBuffering();
        /** Sets up the universe so it goes from
          * -100, -100 up to 100, 100 */
        StdDraw.setScale(-radius, radius);
        /* Clears the drawing window. */
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        StdDraw.show();
        for (int i = 0; i < bodies.length; i++) {
            bodies[i].draw();
            StdDraw.show();
            System.out.println("Drawing " + bodies[i].imgFileName);
        }

        int time = 0;
        while (time <= T) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].draw();
                System.out.println("Drawing " + bodies[i].imgFileName);
            }
            StdDraw.show();
            StdDraw.pause(10);
            time++;
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}
