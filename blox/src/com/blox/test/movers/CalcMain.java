package com.blox.test.movers;

import java.text.DecimalFormat;
import java.text.Format;

import com.blox.framework.v0.impl.Mover2;

public class CalcMain {
    private final static float dt = 1 / 60f;
    private final static Format format = new DecimalFormat("0.00");

	public static void main(String[] args) {
        float initialVelocity = 100;
        float totalTime = 30;
        float totalDistance = 100;

        testVelocity(initialVelocity, totalTime, totalDistance);
        System.out.println("----------------------------------------------");
        testAcceleration(initialVelocity, totalTime, totalDistance);
    }

    private static void testVelocity(float initialVelocity, float totalTime, float totalDistance) {

		Mover2.SlowDownCalculator calc = new Mover2.SlowDownCalculator();
        calc.update(initialVelocity, totalTime, totalDistance);

        float d = 0;

        for(float t = 0; t < totalTime + dt; t += dt) {
            float v = calc.calculateVelocity(t);
            d += v * dt;

            System.out.println(String.format("t = %s, v = %s, d = %s",
            		format.format(t),format.format(v),format.format(d)));
        }
    }

    private static void testAcceleration(float initialVelocity, float totalTime, float totalDistance) {
		Mover2.SlowDownCalculator calc = new Mover2.SlowDownCalculator();
        calc.update(initialVelocity, totalTime, totalDistance);


        float d = 0;
        float v = initialVelocity;

        for(float t = 0; t < totalTime + dt; t += dt) {
            float a = calc.calculateAcceleration(t);

            d += 0.5f * a * dt * dt + v * dt;
            v += a * dt;
            
            System.out.println(String.format("t = %s, v = %s, d = %s, a = %s",
            		format.format(t),format.format(v),format.format(d),format.format(a)));
        }
    }


}
