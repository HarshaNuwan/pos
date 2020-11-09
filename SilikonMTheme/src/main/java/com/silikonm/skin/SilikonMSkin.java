package com.silikonm.skin;

import javax.swing.plaf.synth.SynthLookAndFeel;

public class SilikonMSkin {

	SynthLookAndFeel lookAndFeel;

	public SilikonMSkin() {
		try {

			lookAndFeel = new SynthLookAndFeel();
			lookAndFeel.load(
					SilikonMSkin.class.getResourceAsStream("synth.xml"),
					SilikonMSkin.class);
		} catch (Exception e) {
			System.err.println("Error in " + SilikonMSkin.class
					+ " when initializing");
			e.printStackTrace();
		}
	}

	public SynthLookAndFeel getLookAndFeel() {
		return lookAndFeel;
	}
}
