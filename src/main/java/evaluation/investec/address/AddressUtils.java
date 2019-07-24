package evaluation.investec.address;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class to perform various functions arround the Address data structure.
 * Performs :
 * 		1. Read of a list of address from a specified file located in the project resources
 * 		2. Creates a String representation of Address in Type: Line details - city - province/state - postal code – country
 * 		3. Pretty prints all addresses in a file to console
 * 		4. Pretty prints addresses of a specific type to console
 * 		5. Validates that the address is valid in respect of address rules
 * 		6. Prints validation results for each address in the file to the console
 * @author Retha
 *
 */
public class AddressUtils {
	
	public Function<String, File> getAddressFileReader = filename -> {
	    ClassLoader cl = getClass().getClassLoader();
	    File addressFile = new File(cl.getResource(filename).getFile());
	    return addressFile;
	};

	public List<Address> readAddressList(String filename) {
	    ObjectMapper mapper = new ObjectMapper();
	    
	    List<Address> addressList = new ArrayList<Address>();
		try {
			File addressFile = getAddressFileReader.apply(filename);
			
			addressList = mapper.readValue(addressFile, new TypeReference<List<Address>>() {});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	    return addressList;
	}
	
	/*
	 * pre condition for a successful pretty print would be a valid address
	 */
	public String prettyPrint(Address address) {
		StringBuilder prettyString = new StringBuilder();
		//Type: Line details - city - province/state - postal code – country
		prettyString.append(address.getAddressType().getName() + ": ");
		if (address.getAddressDetail() != null ) {
			String line1 = address.getAddressDetail().getLine1();
			String line2 = address.getAddressDetail().getLine2();
			prettyString.append(line1 != null && !line1.isEmpty()?line1:"");
			String seperator = line1 != null && !line1.isEmpty()?", ":"";
			prettyString.append(line2 != null && !line2.isEmpty()
					?seperator+line2+ " - ":" - " );
		}
		prettyString.append(address.getCityOrTown() + " - ");
		if (address.getProvinceState() != null) {
			prettyString.append(address.getProvinceState().getName() + " - ");
		}
		prettyString.append(address.getPostalCode() + " - ");
		if (address.getCountry() != null) {
			prettyString.append(address.getCountry().getName());
		}
		
		return prettyString.toString();
	}
	
	public List<String> prettyPrintAll(List<Address> addressList) {
		List<String> prettyPrintList = new ArrayList<>();
		
		if (addressList != null && !addressList.isEmpty()) {
			addressList.stream().forEach(addr ->prettyPrintList.add(prettyPrint(addr)));
		} else {
			prettyPrintList.add("Oops no addresses were found.");
		}
		
		return prettyPrintList;
	}
	
	public List<Address> filterByType(int type, List<Address> addressList) {
		List<Address> filteredAddresses = null;
		
		if (addressList != null && !addressList.isEmpty()) {
			filteredAddresses = addressList.stream().filter(addr -> addr.getAddressType().getCode() == type).collect(Collectors.toList());
		}
		
		return filteredAddresses;
	}

	
	// This can also be solved by returning a boolean value and using exceptions
	public ValidationFailure isValid(Address address) {
		ValidationFailure report = new ValidationFailure();
		report.setValid(true);
		
		
		if (!StringUtils.isNumeric(address.getPostalCode())) {
			report.setValid(false);
			report.addFailure("Invalid Postal code");
		}
		if (address.getCountry() == null || address.getCountry().isEmpty()) {
			report.setValid(false);
			report.addFailure("Invalid Country");
		}
		
		if (address.getCountry() != null && 
			address.getCountry().isEmpty() &&
			"ZA".equals(address.getCountry().getCode()) && address.getProvinceState().isEmpty()) {
			report.setValid(false);
			report.addFailure("Province/State required");
		}
		
		if (address.getAddressDetail() == null) {
			report.setValid(false);
			report.addFailure("Address details line required");
		} else if ((address.getAddressDetail().getLine1() == null || address.getAddressDetail().getLine1().isEmpty()) &&
			(address.getAddressDetail().getLine2() == null || address.getAddressDetail().getLine2().isEmpty())) {
			report.setValid(false);
			report.addFailure("Address details line required");
		}
		return report;
	}
	
	public List<String> printValidationReport(List<Address> addressList) {
		List<String> validationReport = new ArrayList<>();
		
		addressList.forEach(item -> {
			ValidationFailure report = isValid(item);
			if (report.isValid()) {
				validationReport.add("Address id("+item.getId()+") - valid" );
			} else {
				StringBuilder validation = new StringBuilder();
				validation.append("Address id("+item.getId()+") - invalid\n" );
				report.getFailures().forEach(err -> validation.append("\t"+err+"\n"));
				validationReport.add(validation.toString());
			}
		});
		
		return validationReport;
	}

	public static class ValidationFailure {
		private boolean valid;
		private List<String> failedOnList;
		
		public boolean isValid() {
			return this.valid;
		}
		public void setValid(boolean valid) {
			this.valid = valid;
		}
		
		public List<String> getFailures() {
			return this.failedOnList;
		}
		public void addFailure(String message) {
			if (this.failedOnList == null) {
				failedOnList = new ArrayList<>();
			}
			failedOnList.add(message);
		}
	}
}
