package GeraLaudo;

public class Laudo {

	private String versaoER;
	private String dataInicio;
	private String dataFim;
	private String roteiro;
	private String acompanhante;
	private int nroEnvelope;
	private String marcaEnvelope;
	private String modeloEnvelope;
	private String declaracao;

	public String getVersaoER() {
		return versaoER;
	}

	public void setVersaoER(String versaoER) {
		this.versaoER = versaoER;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public String getRoteiro() {
		return roteiro;
	}

	public void setRoteiro(String roteiro) {
		this.roteiro = roteiro;
	}

	public String getAcompanhante() {
		return acompanhante;
	}

	public void setAcompanhante(String acompanhante) {
		this.acompanhante = acompanhante;
	}

	public int getNroEnvelope() {
		return nroEnvelope;
	}

	public void setNroEnvelope(int nroEnvelope) {
		this.nroEnvelope = nroEnvelope;
	}

	public String getMarcaEnvelope() {
		return marcaEnvelope;
	}

	public void setMarcaEnvelope(String marcaEnvelope) {
		this.marcaEnvelope = marcaEnvelope;
	}

	public String getModeloEnvelope() {
		return modeloEnvelope;
	}

	public void setModeloEnvelope(String modeloEnvelope) {
		this.modeloEnvelope = modeloEnvelope;
	}

	public String getDeclaracao() {
		return declaracao;
	}

	public void setDeclaracao(String declaracao) {
		this.declaracao = declaracao;
	}

}