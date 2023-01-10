import java.lang.Math;

public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        return Math.sqrt(Math.pow((this.xxPos - b.xxPos),2) + Math.pow((this.yyPos - b.yyPos),2));
    }

    public double calcForceExertedBy(Body b) {
        return Body.G*((this.mass*b.mass)/(Math.pow(this.calcDistance(b),2)));
    }

    public double calcForceExertedByX(Body b) {
        double netF = this.calcForceExertedBy(b);
        double dx = b.xxPos - this.xxPos;
        return netF*dx/this.calcDistance(b);
    }

    public double calcForceExertedByY(Body b) {
        double netF = this.calcForceExertedBy(b);
        double dy = b.yyPos - this.yyPos;
        return netF*dy/this.calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] bodies) {
        int index;
        double netF = 0;
        for (index = 0; index < bodies.length; index++) {
            if (!this.equals(bodies[index])) {
                netF += this.calcForceExertedByX(bodies[index]);
            }
        }
        return netF;
    }

    public double calcNetForceExertedByY(Body[] bodies) {
        int index;
        double netF = 0;
        for (index = 0; index < bodies.length; index++) {
            if (!this.equals(bodies[index])) {
                netF += this.calcForceExertedByY(bodies[index]);
            }
        }
        return netF;
    }
}
