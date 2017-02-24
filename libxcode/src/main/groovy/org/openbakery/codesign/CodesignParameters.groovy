package org.openbakery.codesign

import org.openbakery.CommandRunner

/**
 * Created by rene on 24.02.17.
 */
class CodesignParameters {

	String signingIdentity
	List<File> mobileProvisionFiles
	File keychain

	Security security

	CodesignParameters() {
		security = new Security(new CommandRunner())
	}

	String getSigningIdentity() {
		if (signingIdentity == null) {
			if (keychain != null && keychain.exists()) {
				signingIdentity = security.getIdentity(keychain)
			}
		}
		return signingIdentity
	}


	void mergeMissing(CodesignParameters parameters) {
		if (signingIdentity == null) {
			signingIdentity = parameters.signingIdentity
		}

		if (keychain == null) {
			keychain = parameters.keychain
		}

		if ((mobileProvisionFiles == null || mobileProvisionFiles.isEmpty()) &&  parameters.mobileProvisionFiles != null) {
			mobileProvisionFiles = parameters.mobileProvisionFiles.clone()
		}
	}

}