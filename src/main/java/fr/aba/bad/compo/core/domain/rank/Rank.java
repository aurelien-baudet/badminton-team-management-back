package fr.aba.bad.compo.core.domain.rank;

public enum Rank {
	N1(12),
	N2(11),
	N3(11),
	R4(10),
	R5(9),
	R6(8),
	D7(7),
	D8(6),
	D9(5),
	P10(4),
	P11(3),
	P12(2),
	NC(1);
	
	private final int value;

	private Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
