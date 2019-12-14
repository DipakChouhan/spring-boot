package com.owneroftime.enums;


public enum DateEnum implements EnumInterface{
	DATETIME, DATE, TIMESTAMP;

	@Override
	public boolean contains(String str) {
		for (DateEnum e: values())
			if (e.name().equals(str))
				return true;
		return false;
	}
}
