package com.amaysim.examination;

/**
 * Enumerations for Product
 * @author rhealamsen
 *
 */
public enum ProductEnum {
	
	UnlimitedOneGb("Unlimited 1GB"),
	UnlimitedTwoGb("Unlimited 1GB"),
	UnlimitedFiveGb("Unlimited 5GB"),
	OneGbDatapack("1 GB Data-pack ");
	
	
	private final String value;

	private ProductEnum(final String value) {
        this.value = value;
    }	

}
