package com.example.app.jobapp.client;

import com.example.app.jobapp.shared.ApplicantInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RecordInfoServiceAsync {

	void recordInfo(ApplicantInfo applicantInfo, AsyncCallback<Void> callback);


}
