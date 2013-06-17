package com.sandiegogdg.fragmentsdemo;

public final class Data {

	static class DetailsData {
		DetailsData(int ri, String tx) {
			resId = ri;
			text = tx;
		}

		int resId;
		String text;
	}

	final static DetailsData[] DETAILS = {
			new DetailsData(
					R.drawable.mercury,
					"Mercury (0.4 AU from the Sun) is the closest planet to the Sun and the smallest planet in the Solar System (0.055 Earth masses). Mercury has no natural satellites, and its only known geological features besides impact craters are lobed ridges or rupes, probably produced by a period of contraction early in its history. Mercury's almost negligible atmosphere consists of atoms blasted off its surface by the solar wind. Its relatively large iron core and thin mantle have not yet been adequately explained. Hypotheses include that its outer layers were stripped off by a giant impact and that it was prevented from fully accreting by the young Sun's energy."),
			new DetailsData(
					R.drawable.venus,
					"Venus (0.7 AU from the Sun) is close in size to Earth (0.815 Earth masses) and, like Earth, has a thick silicate mantle around an iron core, a substantial atmosphere, and evidence of internal geological activity. However, it is much drier than Earth, and its atmosphere is ninety times as dense. Venus has no natural satellites. It is the hottest planet, with surface temperatures over 400 °C (752°F), most likely due to the amount of greenhouse gases in the atmosphere. No definitive evidence of current geological activity has been detected on Venus, but it has no magnetic field that would prevent depletion of its substantial atmosphere, which suggests that its atmosphere is regularly replenished by volcanic eruptions."),
			new DetailsData(
					R.drawable.earth,
					"Earth (1 AU from the Sun) is the largest and densest of the inner planets, the only one known to have current geological activity, and the only place where life is known to exist. Its liquid hydrosphere is unique among the terrestrial planets, and it is also the only planet where plate tectonics has been observed. Earth's atmosphere is radically different from those of the other planets, having been altered by the presence of life to contain 21% free oxygen. It has one natural satellite, the Moon, the only large satellite of a terrestrial planet in the Solar System."),
	};
}
