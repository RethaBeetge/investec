package evaluation.investec.address.test;

import static org.junit.Assert.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;

import org.hamcrest.collection.IsEmptyCollection;

import org.junit.Test;

import evaluation.investec.address.Address;
import evaluation.investec.address.AddressUtils;

public class AddressUtilsTest {

	@Test
	public void jsonFileParseTest() {
		AddressUtils addrUtils = new AddressUtils();

		List<Address> addrList = addrUtils.readAddressList("addresses.json");
		
		assertThat(addrList, not(IsEmptyCollection.empty()));
		assertThat(addrList, hasSize(3));
	}
	
	@Test
	public void prettyPrintTest() {
		AddressUtils addrUtils = new AddressUtils();
		Address addr = new Address();
		addr.setId(3);
		addr.setAddressType(addr.new AddressType());
		addr.getAddressType().setCode(5);
		addr.getAddressType().setName("Business Address");
		addr.setCityOrTown("Some City");
		addr.setAddressDetail(addr.new AddressDetail());
		addr.getAddressDetail().setLine1("Adderley Street");
		addr.setPostalCode("1234");
		addr.setProvinceState(addr.new ProvinceState());
		addr.getProvinceState().setCode(5);
		addr.getProvinceState().setName("Eastern Cape");
		addr.setCountry(addr.new Country());
		addr.getCountry().setCode("ZA");
		addr.getCountry().setName("South Africa");
		
		StringBuilder sb = new StringBuilder();
		sb.append(addr.getAddressType().getName()+": ");
		sb.append(addr.getAddressDetail().getLine1()+" - ");
		sb.append(addr.getCityOrTown()+" - ");
		sb.append(addr.getProvinceState().getName()+" - ");
		sb.append(addr.getPostalCode()+" - ");
		sb.append(addr.getCountry().getName());
		
		String prettyAddr = addrUtils.prettyPrint(addr);
		
		assertTrue(sb.toString().equals(prettyAddr));
	}

	@Test
	public void validAddressTest() {
		AddressUtils addrUtils = new AddressUtils();
		Address addr = new Address();
		addr.setId(3);
		addr.setAddressType(addr.new AddressType());
		addr.getAddressType().setCode(5);
		addr.getAddressType().setName("Business Address");
		addr.setCityOrTown("Some City");
		addr.setPostalCode("1234");
		addr.setCountry(addr.new Country());
		addr.getCountry().setCode("ZA");
		addr.getCountry().setName("South Africa");
		
		AddressUtils.ValidationFailure report = addrUtils.isValid(addr);
		
		assertFalse(report.isValid());
		assertThat(report.getFailures(), not(IsEmptyCollection.empty()));
	}
}
