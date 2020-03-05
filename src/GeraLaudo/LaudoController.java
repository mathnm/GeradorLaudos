package GeraLaudo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import util.Conexao;
import util.Constante;

public class LaudoController {

	// bloco de variáveis com dados do LAUDO (terceira tela)
	@FXML
	private TextField txtVersaoER;
	@FXML
	private TextField txtDataInicio;
	@FXML
	private TextField txtDataFim;
	@FXML
	private TextField txtRoteiro;
	@FXML
	private TextField txtResponsTestes;
	@FXML
	private TextField txtNrEnvelope;
	@FXML
	private TextField txtMarcaEnvelope;
	@FXML
	private TextField txtModeloEnvelope;
	@FXML
	private RadioButton rdDeclaracaoS;
	@FXML
	private RadioButton rdDeclaracaoN;

	public void cadastraLaudo() {
		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
//			loader.load();
//			SampleController controller = loader.getController();
//			if(controller.txtNrLaudo.getText().isEmpty()) {
//				mostraAlert("Informe o número do laudo!");
//			}else {	
				int numeroLaudo = Integer.parseInt(Constante.nrLaudo);
				Laudo l = new Laudo();
				l.setVersaoER(txtVersaoER.getText());
				l.setDataInicio(txtDataInicio.getText());
				l.setDataFim(txtDataFim.getText());
				l.setRoteiro(txtRoteiro.getText());
				l.setAcompanhante(txtResponsTestes.getText());
				l.setNroEnvelope(Integer.parseInt(txtNrEnvelope.getText()));
				l.setMarcaEnvelope(txtMarcaEnvelope.getText());
				l.setModeloEnvelope(txtModeloEnvelope.getText());
				if (rdDeclaracaoS.isSelected()) {
					l.setDeclaracao("Sim");
				} else {
					l.setDeclaracao("Não");
				}
	
				Connection conn = Conexao.getConexao();
				String sql = "Insert into laudo (nrlaudo, er, inicio, fim, roteiro, responsavel_testes, nro_envelope, marca, modelo_envelope, declaracao)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, numeroLaudo);
				ps.setString(2, l.getVersaoER());
				ps.setString(3, l.getDataInicio());
				ps.setString(4, l.getDataFim());
				ps.setString(5, l.getRoteiro());
				ps.setString(6, l.getAcompanhante());
				ps.setInt(7, l.getNroEnvelope());
				ps.setString(8, l.getMarcaEnvelope());
				ps.setString(9, l.getModeloEnvelope());
				ps.setString(10, l.getDeclaracao());
				ps.executeUpdate();
				conn.close();
//			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mostraAlert(String msg) {
		Alert a = new Alert(AlertType.WARNING);
		a.setHeaderText(null);
		a.setContentText(msg);
		a.show();
	}

	public String txtLaudo(String nrLaudo) {
		String dadosLaudo = "";
		try {
			Connection conn = Conexao.getConexao();
			String sql = "Select * from laudo where nrlaudo = " + nrLaudo;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				dadosLaudo += rs.getString("er") + " | " + rs.getString("inicio") + " | " + rs.getString("fim") + " | "
						+ rs.getString("roteiro") + " | " + rs.getString("responsavel_testes") + " | "
						+ rs.getString("nro_envelope") + " | " + rs.getString("marca") + " | "
						+ rs.getString("modelo_envelope") + " | Declaração: " + rs.getString("declaracao") + "\n";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "DADOS DO LAUDO: " + dadosLaudo;

	}
}