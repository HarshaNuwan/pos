package com.silikonm.pos.item.extended;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class FontService {

	private static Font sinhalaFont;
	private static final String fontPath = "/com/silikonm/pos/resources/";

	public FontService() {

	}

	public static Font getSinhalaFont(int fontSize) {
		try {
			sinhalaFont = Font.createFont(
					Font.TRUETYPE_FONT,
					FontService.class.getResourceAsStream(fontPath
							+ "amalee.TTF"));
			sinhalaFont = new Font(sinhalaFont.getName(), Font.PLAIN, fontSize);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sinhalaFont;
	}

}
