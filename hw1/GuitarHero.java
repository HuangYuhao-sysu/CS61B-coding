/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
import edu.princeton.cs.introcs.StdAudio;
import es.datastructur.synthesizer.GuitarString;

import java.util.HashMap;
import java.util.Map;

public class GuitarHero {
    public static void main(String[] args) {
        /**
         * Initial keys and GuitarStrings.
         * */
        Map<Character,GuitarString> keysSound = new HashMap<>();
        String keys = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        for (int i = 0; i < keys.length(); i += 1) {
            keysSound.put(keys.charAt(i),new GuitarString(440*Math.pow(2,((i-24)/12))));
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                keysSound.get(key).pluck();
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (char c : keysSound.keySet()) {
                sample += keysSound.get(c).sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (char c : keysSound.keySet()) {
                keysSound.get(c).tic();
            }
        }
    }
}
