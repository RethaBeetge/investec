package evaluation.investec;

import java.util.List;

import evaluation.investec.address.Address;
import evaluation.investec.address.AddressUtils;
import evaluation.investec.gcd.CommonFactor;

public class Evaluation {
	
	public static void main(String[] args) {
		Evaluation evaluation = new Evaluation();
		
		evaluation.addressEvaluation();
		
	}
	
	public void gcdEvaluation() {
		CommonFactor cf = new CommonFactor();
	}

	public void addressEvaluation() {
		AddressUtils addressUtils = new AddressUtils();
		List<Address> addressList = addressUtils.readAddressList("addresses.json");
		
		//Pretty print an address from the list
		String prettyAddress = addressUtils.prettyPrint(addressList.get(0));
		System.out.println(prettyAddress);
		
		//Pretty print all the addresses in the file
		List<String> prettyAddresses = addressUtils.prettyPrintAll(addressList);
		prettyAddresses.stream().forEach(addr -> System.out.println(addr));
		
		//Print Physical Addresses
		List<Address> physicalAddr = addressUtils.filterByType(1, addressList);
		prettyAddresses = addressUtils.prettyPrintAll(physicalAddr);
		System.out.println("List of Physical Addresses:");
		prettyAddresses.stream().forEach(addr -> System.out.println(addr));
		
		//Validate the first address in the list
		AddressUtils.ValidationFailure validation = addressUtils.isValid(addressList.get(0));
		System.out.println(validation.isValid()?"Address passed validation": "Address invalid");
		
		//Validate all addresses in file
		List<String> fullValidation = addressUtils.printValidationReport(addressList);
		fullValidation.stream().forEach(err -> System.out.println(err));
		
	}
	
	
}
