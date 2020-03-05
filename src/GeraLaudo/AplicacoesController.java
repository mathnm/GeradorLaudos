package GeraLaudo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import util.Conexao;
import util.Constante;

public class AplicacoesController {

	// bloco de variáveis com dados das aplicações exercidas pelo PAF (quarta tela)
	@FXML
	private CheckBox ckPedagio;
	@FXML
	private CheckBox ckTranspPassageiro;
	@FXML
	private CheckBox ckFarmacia;
	@FXML
	private CheckBox ckDavOs;
	@FXML
	private CheckBox ckOficinaConsContaCliente;
	@FXML
	private CheckBox ckRestComBalanca;
	@FXML
	private CheckBox ckRestSemBalanca;
	@FXML
	private CheckBox ckPostoComBombas;
	@FXML
	private CheckBox ckPostoSemBombas;
	@FXML
	private CheckBox ckEstacionamento;
	@FXML
	private CheckBox ckCinema;
	@FXML
	private CheckBox ckDemaisAtvd;
	@FXML
	private CheckBox ckSimplesNacional;

	public String pedagio, transporte, farmacia, davOs, oficinaContaCliente, restComBalanca, restSemBalanca,
			postoComBomba, postoSemBomba, estacionamento, cinema, demaisAtvd, simplesNacional;

	public void aplicacoes() {
		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
//			loader.load();
//			SampleController controller = loader.getController();
//			if(controller.txtNrLaudo.getText().isEmpty()) {
//				mostraAlert("Informe o número do laudo!");
//			}else {			
				if (ckPedagio.isSelected()) {
					pedagio = "Sim";
				} else {
					pedagio = "Não";
				}
				if (ckTranspPassageiro.isSelected()) {
					transporte = "Sim";
				} else {
					transporte = "Não";
				}
				if (ckFarmacia.isSelected()) {
					farmacia = "Sim";
				} else {
					farmacia = "Não";
				}
				if (ckDavOs.isSelected()) {
					davOs = "Sim";
				} else {
					davOs = "Não";
				}
				if (ckOficinaConsContaCliente.isSelected()) {
					oficinaContaCliente = "Sim";
				} else {
					oficinaContaCliente = "Não";
				}
				if (ckRestComBalanca.isSelected()) {
					restComBalanca = "Sim";
				} else {
					restComBalanca = "Não";
				}
				if (ckRestSemBalanca.isSelected()) {
					restSemBalanca = "Sim";
				} else {
					restSemBalanca = "Não";
				}
				if (ckPostoComBombas.isSelected()) {
					postoComBomba = "Sim";
				} else {
					postoComBomba = "Não";
				}
				if (ckPostoSemBombas.isSelected()) {
					postoSemBomba = "Sim";
				} else {
					postoSemBomba = "Não";
				}
				if (ckEstacionamento.isSelected()) {
					estacionamento = "Sim";
				} else {
					estacionamento = "Não";
				}
				if (ckCinema.isSelected()) {
					cinema = "Sim";
				} else {
					cinema = "Não";
				}
				if (ckDemaisAtvd.isSelected()) {
					demaisAtvd = "Sim";
				} else {
					demaisAtvd = "Não";
				}
				if (ckSimplesNacional.isSelected()) {
					simplesNacional = "Sim";
				} else {
					simplesNacional = "Não";
				}
				int numeroLaudo = Integer.parseInt(Constante.nrLaudo);

				Connection conn = Conexao.getConexao();
				String sql = "Insert into aplicacoes (nrlaudo, pedagio, transporte, farmacia, dav_os, dav_os_contadecliente, rest_combalanca, rest_sembalanca, postocombombas,"
						+ "postosembombas, estacionamento, cinema, demais_atvd, simples_nacional)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, numeroLaudo);
				ps.setString(2, pedagio);
				ps.setString(3, transporte);
				ps.setString(4, farmacia);
				ps.setString(5, davOs);
				ps.setString(6, oficinaContaCliente);
				ps.setString(7, restComBalanca);
				ps.setString(8, restSemBalanca);
				ps.setString(9, postoComBomba);
				ps.setString(10, postoSemBomba);
				ps.setString(11, estacionamento);
				ps.setString(12, cinema);
				ps.setString(13, demaisAtvd);
				ps.setString(14, simplesNacional);
				ps.executeUpdate();
				conn.close();
		//	} 
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}

	public String txtAplicacoes(String nrLaudo) {
		String aplicacoesSim = "";
		try {
			Connection conn = Conexao.getConexao();
			String sql = "Select * from aplicacoes where nrlaudo = " + nrLaudo;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pedagio = (rs.getString("pedagio"));
				aplicacoesSim += (pedagio.equalsIgnoreCase("sim")) ? "Pedagio | " : "";

				transporte = (rs.getString("transporte"));
				aplicacoesSim += (transporte.equalsIgnoreCase("sim")) ? "Transporte | " : "";

				farmacia = (rs.getString("farmacia"));
				aplicacoesSim += (farmacia.equalsIgnoreCase("sim")) ? "Farmacia | " : "";

				davOs = (rs.getString("dav_os"));
				aplicacoesSim += (davOs.equalsIgnoreCase("sim")) ? "DAV-OS | " : "";

				oficinaContaCliente = (rs.getString("dav_os_contadecliente"));
				aplicacoesSim += (oficinaContaCliente.equalsIgnoreCase("sim")) ? "DAV-OS ContaCliente | " : "";

				restComBalanca = (rs.getString("rest_combalanca"));
				aplicacoesSim += (restComBalanca.equalsIgnoreCase("sim")) ? "Restaurante com balança | " : "";

				restSemBalanca = (rs.getString("rest_sembalanca"));
				aplicacoesSim += (restSemBalanca.equalsIgnoreCase("sim")) ? "Restaurante sem balança | " : "";

				postoComBomba = (rs.getString("postocombombas"));
				aplicacoesSim += (postoComBomba.equalsIgnoreCase("sim")) ? "Posto de combustíveis com bomba | " : "";

				postoSemBomba = (rs.getString("postosembombas"));
				aplicacoesSim += (postoSemBomba.equalsIgnoreCase("sim")) ? "Posto de combustíveis sem bomba | " : "";

				estacionamento = (rs.getString("estacionamento"));
				aplicacoesSim += (estacionamento.equalsIgnoreCase("sim")) ? "Estacionamento | " : "";

				cinema = (rs.getString("cinema"));
				aplicacoesSim += (cinema.equalsIgnoreCase("sim")) ? "Cinema | " : "";

				demaisAtvd = (rs.getString("demais_atvd"));
				aplicacoesSim += (demaisAtvd.equalsIgnoreCase("sim")) ? "Demais atividades | " : "";

				simplesNacional = (rs.getString("simples_nacional"));
				aplicacoesSim += (aplicacoesSim.equalsIgnoreCase("sim")) ? "Simples Nacional | " : "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "APLICAÇÕES: " + aplicacoesSim + "\n";

	}

	public void mostraAlert(String msg) {
		Alert a = new Alert(AlertType.WARNING);
		a.setHeaderText(null);
		a.setContentText(msg);
		a.show();
	}
}