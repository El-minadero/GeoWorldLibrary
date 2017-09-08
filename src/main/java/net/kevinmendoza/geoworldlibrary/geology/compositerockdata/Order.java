package net.kevinmendoza.geoworldlibrary.geology.compositerockdata;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Order {
	FIRST,SECOND,THIRD,FOURTH,FIFTH;

	private static final List<Order> VALUES =
			Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();

	public static Order randomOrder(Random random)  {
		return VALUES.get(random.nextInt(SIZE));
	}
}
