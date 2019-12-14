package com.owneroftime.enums;


public enum NumberEnum implements EnumInterface{
	INT, NUMBER, NUMERIC;

	@Override
	public boolean contains(String str) {
		for (NumberEnum e: values())
			if (e.name().equals(str))
				return true;
		return false;
	}
}
