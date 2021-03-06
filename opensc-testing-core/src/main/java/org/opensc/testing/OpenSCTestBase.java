/* 
 * OpenSCTestBase.java Copyright (C) 2012 This file is part of OpenSC project
 * 
 * This software is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * As a special exception, if you link this library with other files to
 * produce an executable, this library does not by itself cause the
 * resulting executable to be covered by the GNU General Public License.
 * This exception does not however invalidate any other reasons why the
 * executable file might be covered by the GNU General Public License.
 * 
 * Authors:: Alejandro Díaz Torres (mailto:adiaz@emergya.com)
 * Authors:: Alejandro Díaz Torres (mailto:aledt84@gmail.com)
 */
package org.opensc.testing;

import java.security.KeyStore;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class for OpenSC testing from Junit 
 * 
 * @author <a href="mailto:adiaz@emergya.com">Alejandro Diaz Torres</a>
 *
 */
public class OpenSCTestBase {
	
	//TODO: READ all from properties
	protected KeyStore myStore;
	protected String pin = null;
	protected String selectedAlias = null;
	protected boolean verbose = false;
	protected boolean requestPin = false;
	protected int numCertificates = 0;
	protected Map<Integer, String> aliasesMap;
	
	/**
	 * Init the keystore and read certificates
	 * 
	 * @throws Exception
	 */
	public void init() throws Exception {
		if(requestPin){
			pin = PinDialog.showPinDialog();
		}
		if (pin==null) {
			System.out.println("Empty PIN, trying with 0000...");
			pin = "0000";
		}
		myStore = SecurityUtils.getInitializedKeyStore(pin);
		Enumeration<String> aliases = myStore.aliases();
		aliasesMap = new HashMap<Integer, String>();
		if(verbose){
			System.out.println("********************************************************** " +
					"aliases list **********************************************************");
		}
		while (aliases.hasMoreElements()){
			selectedAlias= aliases.nextElement();
			aliasesMap.put(new Integer(numCertificates), selectedAlias);

			if(verbose){
				System.out.println("alias[" + (numCertificates) + "] --> " +selectedAlias);
			}
			
			numCertificates++;
		}
		if(verbose){
			System.out.println("******************************************************** " +
					"EoF aliases list ********************************************************");
		}
	}

}

