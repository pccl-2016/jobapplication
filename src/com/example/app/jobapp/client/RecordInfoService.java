/**
 * 
 */
package com.example.app.jobapp.client;

import java.io.IOException;

import com.example.app.jobapp.shared.ApplicantInfo;
import com.example.app.jobapp.shared.ApplicantInfoException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Interface for the remote service to record application information entered by
 * applicants.
 * @author xiaobin
 *
 */
@RemoteServiceRelativePath("recordInfo")
public interface RecordInfoService extends RemoteService {
	public void recordInfo(ApplicantInfo applicantInfo) throws IOException,
	ApplicantInfoException;
}
