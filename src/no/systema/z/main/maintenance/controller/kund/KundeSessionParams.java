package no.systema.z.main.maintenance.controller.kund;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Object for transfering data between jsp in Kunderegister
 * 
 * @author Fredrik Möller
 * @date Okt 31, 2016
 *
 */
public class KundeSessionParams implements Serializable {

	private static final long serialVersionUID = -1889823127739746766L;

	private String kundnr;
	private String knavn;
	private String firma;
	private String sonavn;
	private boolean exportNo;
	private boolean importNo;
	private boolean exportDk;
	private boolean importDk;
	private boolean exportSe;
	private boolean importSe;
	private int fantomSpaceWidth;

	public boolean isExportSe() {
		return exportSe;
	}

	public void setExportSe(boolean exportSe) {
		this.exportSe = exportSe;
	}

	public boolean isImportSe() {
		return importSe;
	}

	public void setImportSe(boolean importSe) {
		this.importSe = importSe;
	}

	public boolean isExportNo() {
		return exportNo;
	}

	public void setExportNo(boolean exportNo) {
		this.exportNo = exportNo;
	}

	public boolean isImportNo() {
		return importNo;
	}

	public void setImportNo(boolean importNo) {
		this.importNo = importNo;
	}

	public boolean isExportDk() {
		return exportDk;
	}

	public void setExportDk(boolean exportDk) {
		this.exportDk = exportDk;
	}

	public boolean isImportDk() {
		return importDk;
	}

	public void setImportDk(boolean importDk) {
		this.importDk = importDk;
	}

	public int getFantomSpaceWidth() {
		return fantomSpaceWidth;
	}

	public void setFantomSpaceWidth(int fantomSpaceWidth) {
		this.fantomSpaceWidth = fantomSpaceWidth;
	}

	public String getKundnr() {
		return kundnr;
	}

	public void setKundnr(String kundnr) {
		this.kundnr = kundnr;
	}

	public String getKnavn() {
		return knavn;
	}

	public void setKnavn(String knavn) {
		this.knavn = knavn;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getSonavn() {
		return sonavn;
	}

	public void setSonavn(String sonavn) {
		this.sonavn = sonavn;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
