package com.gwsm0.constants;

public enum ActionConstants {

	CHECKPIN(1),
	CHECKUTENTE(),
	CHECKMAIL(2),
	SENDOTPMAIL(),
	CHECKOTPMAIL(3),
	CREAZIONEPIN(4),
	CAMBIOPIN(5),
	RECUPERAPIN(),
	
	REGISTRATI(6),
	ANAGRAFICAADD(7),
	SICUREZZA(8),
	ANAGRAFICAGET(10),
	ANAGRAFICAMOD(11),
	RETRIVEANAGRAFICA(),
	
	ENFORCEMENT(9),
	CONSENT(),
	STATUS(),
	


	
	
	inutilemamiserveil;
	
	 private final int id;
	 ActionConstants(final int id) { this.id = id; }
	 ActionConstants() { this.id = 0; }
	    public int getId() { return this.id; }
}
