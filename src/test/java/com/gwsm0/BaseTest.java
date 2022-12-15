package com.gwsm0;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gwsm0.rest.fragment.otp.OtpComponentService;
import com.gwsm0.rest.fragment.session.SecuretySessionFrag;
import com.gwsm0.rest.fragment.wiam.AnagraficaWiam;
import com.gwsm0.rest.fragment.wiam.ChangePinWiam;
import com.gwsm0.rest.fragment.wiam.CheckPinWiam;
import com.gwsm0.rest.fragment.wiam.EnforcementWiam;
import com.gwsm0.rest.fragment.wiam.StatusWiam;
import com.gwsm0.rest.service.EnforcementService;
import com.gwsm0.rest.service.StatusService;
import com.gwsm0.rest.service.anagrafica.AnagraficaAddService;
import com.gwsm0.rest.service.anagrafica.RetriveAnagaficaService;
import com.gwsm0.rest.service.otp.CheckOtpService;
import com.gwsm0.rest.service.otp.GenerateOtpService;
import com.gwsm0.rest.service.pin.ChangePinService;
import com.gwsm0.rest.service.pin.CheckPinService;

public class BaseTest {

	@MockBean
	protected OtpComponentService otpComponent;
	@MockBean
	protected ChangePinWiam changePinWiam;
	@MockBean
	protected CheckOtpService checkOtpService;
	@MockBean
	protected CheckPinWiam pinWiam;
	@MockBean
	protected SecuretySessionFrag secSession;
	@MockBean 
	protected StatusWiam statusWiam;
	@Autowired 
	protected MockMvc mvc;
	@Mock
	protected EnforcementWiam enforcement;
	@InjectMocks 
	protected EnforcementService enforcementSer;
	@MockBean
	protected AnagraficaWiam anagWiam;
	@Autowired
	protected ObjectMapper objectMapper;
	@InjectMocks 
	protected AnagraficaAddService anagAdd;
	@Autowired
	protected StatusService statusService;
	@Autowired
	protected CheckOtpService otpCheckService;
	@Autowired
	protected GenerateOtpService generateOtp;
	@Autowired
	protected CheckPinService pinService;
	@Autowired
	protected ChangePinService changePinService;
	@Autowired 
	protected RetriveAnagaficaService retriveAnagService;
	protected ObjectMapper mapper = new ObjectMapper();
}
