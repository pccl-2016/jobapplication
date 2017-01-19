package com.example.app.jobapp.server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.example.app.jobapp.client.RecordInfoService;
import com.example.app.jobapp.shared.ApplicantInfo;
import com.example.app.jobapp.shared.ApplicantInfoException;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class RecordInfoServiceImpl extends RemoteServiceServlet implements RecordInfoService {

	@Override
	public void recordInfo(ApplicantInfo applicantInfo) throws IOException,
	ApplicantInfoException
	{

		//the file where applicant info is written to. Append mode set to true.
		BufferedWriter fout = new BufferedWriter(new FileWriter("applicantInfo.txt", true));

		//check if applicantInfo legitimate
		String nameStr = applicantInfo.getName();
		if(nameStr.length() < 5) {
			throw new ApplicantInfoException("Applicant name should be longer than 5 characters."); 
		} 

		//check gender field
		String gender = applicantInfo.getGender();
		if(!gender.equals("male") && !gender.equals("female") && !gender.equals("other")) {
			throw new ApplicantInfoException("Illegal or empty gender input.");
		}

		//check email field
		String emailStr = applicantInfo.getEmail();
		if(emailStr.isEmpty() || 
				!emailStr.matches("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$")) {
			throw new ApplicantInfoException("Illegal or empty email address.");
		}

		//check phone number field
		String phoneNumberStr = applicantInfo.getPhone();
		if(phoneNumberStr.isEmpty() || 
				RegExp.compile("[^0-9]").exec(phoneNumberStr) != null ) {
			throw new ApplicantInfoException("Illegal or empty phone number.");
		} 

		//check position field
		for(String pos: applicantInfo.getPositions()) {
			if(!pos.equals("Business Analyst") &&
					!pos.equals("UI Designer") &&
					!pos.equals("Java Developer") &&
					!pos.equals("Marketing")) {
			throw new ApplicantInfoException("Illegal or empty position selections.");
			}
		}

		//check passed, write info to file

		fout.append("Name: " + applicantInfo.getName() + "\n" 
				+ "Gender: " + applicantInfo.getGender() + "\n"
				+ "Email: " + applicantInfo.getEmail() + "\n"
				+ "Phone number: " + applicantInfo.getPhone() + "\n"
				+ "Interested position: " + applicantInfo.getPositions() + "\n"
				+ "Degree: " + applicantInfo.getDegree() + "\n"
				+ "CV: " + applicantInfo.getCv() + "\n\n"
				);
		fout.close();
	}

}
