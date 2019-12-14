package com.owneroftime.enums;


public enum StringEnum implements EnumInterface{
	STRING,VARCHAR, VARCHAR2, CHAR;

	@Override
	public boolean contains(String str) {
		for (StringEnum e: values())
			if (e.name().equals(str))
				return true;
		return false;
	}
}
