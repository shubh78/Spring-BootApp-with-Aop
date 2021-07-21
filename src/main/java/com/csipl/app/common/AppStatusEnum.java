package com.csipl.app.common;

public enum AppStatusEnum {


	APPROVED("APR"), PENDING("PEN"), CANCEL("CEN"),
	REGECTED("REJ"), SUCCESS("SUC"), FAILED("FL");

	public String enumValue;

	AppStatusEnum(String pieChartValue) {
		this.enumValue = pieChartValue;
	}

	public String getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}

	
}
