package GeraLaudo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import util.Conexao;
import util.Constante;

public class SistemaController {

	// dados com variáveis dados do SISTEMA (segunda tela)
	@FXML
	private TextField txtVersaoPaf;
	@FXML
	private TextField txtNomePaf;
	@FXML
	private ComboBox<String> cbLinguagem;
	@FXML
	private ComboBox<String> cbBD;
	@FXML
	private ComboBox<String> cbSO;

	// desenvolvimento
	@FXML
	private RadioButton rdComercializavel;
	@FXML
	private RadioButton rdExclProprio;
	@FXML
	private RadioButton rdExclTercerizado;

	// funcionamento
	@FXML
	private RadioButton rdStandAlone;
	@FXML
	private RadioButton rdRede;
	@FXML
	private RadioButton rdParametrizavel;

	// geração SPED
	@FXML
	private RadioButton rdPeloPaf;
	@FXML
	private RadioButton rdPeloSG;
	@FXML
	private RadioButton rdPeloPED;

	// integração
	@FXML
	private RadioButton rdComSG;
	@FXML
	private RadioButton rdComPED;
	@FXML
	private RadioButton rdComAmbos;
	@FXML
	private RadioButton rdNaoIntegrado;

	// impressão item
	@FXML
	private CheckBox ckConcomitante;
	@FXML
	private CheckBox ckDAV;
	@FXML
	private CheckBox ckPV;
	@FXML
	private CheckBox ckContaCliente;
	@FXML
	private CheckBox ckDavSemImpr;
	@FXML
	private CheckBox ckDavNaoFiscal;
	@FXML
	private CheckBox ckDavECF;

	// queda de energia
	@FXML
	private RadioButton rdRecupDados;
	@FXML
	private RadioButton rdCancelamento;
	@FXML
	private RadioButton rdBloqueio;

	// NFe
	@FXML
	private RadioButton rdNfeSim;
	@FXML
	private RadioButton rdNfeNao;

	// perfis
	@FXML
	private CheckBox ckV;
	@FXML
	private CheckBox ckW;
	@FXML
	private CheckBox ckT;

	public void initialize() {
		startComboLinguagem();
		startComboBD();
		startComboSO();
	}

	public void cadastraSistema() {
		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
//			loader.load();
//			SampleController controller = loader.getController();
//			if(controller.txtNrLaudo.getText().isEmpty()) {
//				mostraAlert("Informe o número do laudo!");
//			}else {	
				int numeroLaudo = Integer.parseInt(Constante.nrLaudo);
				Sistema s = new Sistema();
				s.setVersao(txtVersaoPaf.getText());
				s.setNome(txtNomePaf.getText());
				s.setLinguagem(cbLinguagem.getValue());
				s.setBancoDados(cbBD.getValue());
				s.setSistemaOperacional(cbSO.getValue());
				if (rdComercializavel.isSelected()) {
					s.setDesenvolvimento("Comercializável");
				} else if (rdExclProprio.isSelected()) {
					s.setDesenvolvimento("Exclusivo Próprio");
				} else {
					s.setDesenvolvimento("Exclusivo Tercerizado");
				}
	
				if (rdStandAlone.isSelected()) {
					s.setFuncionamento("Exclusivamente Stand Alone");
				} else if (rdRede.isSelected()) {
					s.setFuncionamento("Em Rede");
				} else {
					s.setFuncionamento("Parametrizável");
				}
	
				if (rdPeloPaf.isSelected()) {
					s.setSped("Pelo PAF-ECF");
				} else if (rdPeloPED.isSelected()) {
					s.setSped("Pelo Sistema PED");
				} else {
					s.setSped("Pelo sistema de retaguarda");
				}
	
				if (rdNaoIntegrado.isSelected()) {
					s.setIntegracao("Não Integrado");
				} else if (rdComAmbos.isSelected()) {
					s.setIntegracao("Com Ambos");
				} else if (rdComPED.isSelected()) {
					s.setIntegracao("Com sistema PED");
				} else {
					s.setIntegracao("Com sistema de gestão ou retaguarda");
				}
	
				if (ckConcomitante.isSelected()) {
					s.setConcomitante("Sim");
				} else {
					s.setConcomitante("Não");
				}
				if (ckDAV.isSelected()) {
					s.setDav("Sim");
				} else {
					s.setDav("Não");
				}
				if (ckPV.isSelected()) {
					s.setPv("Sim");
				} else {
					s.setPv("Não");
				}
				if (ckContaCliente.isSelected()) {
					s.setContaCliente("Sim");
				} else {
					s.setContaCliente("Não");
				}
				if (ckDavSemImpr.isSelected()) {
					s.setDavSemImpressao("Sim");
				} else {
					s.setDavSemImpressao("Não");
				}
				if (ckDavNaoFiscal.isSelected()) {
					s.setDavNaoFiscal("Sim");
				} else {
					s.setDavNaoFiscal("Não");
				}
				if (ckDavECF.isSelected()) {
					s.setDavECF("Sim");
				} else {
					s.setDavECF("Não");
				}
	
				if (rdBloqueio.isSelected()) {
					s.setTratamentoQueda("Bloqueio de funções");
				} else if (rdCancelamento.isSelected()) {
					s.setTratamentoQueda("Cancelamento automático");
				} else {
					s.setTratamentoQueda("Recuperação de Dados");
				}
	
				if (rdNfeSim.isSelected()) {
					s.setNfe("Sim");
				} else {
					s.setNfe("Não");
				}
	
				if (ckV.isSelected()) {
					s.setPerfilV("Sim");
				} else {
					s.setPerfilV("Não");
				}
				if (ckT.isSelected()) {
					s.setPerfilT("Sim");
				} else {
					s.setPerfilT("Não");
				}
				if (ckW.isSelected()) {
					s.setPerfilW("Sim");
				} else {
					s.setPerfilW("Não");
				}
	
				Connection conn = Conexao.getConexao();
				String sql = "Insert into sistema (nrlaudo, versao, nome, linguagem, banco_dados, sistema_operacional, desenvolvimento_finalidade, funcionamento, geracao_sped, concomitante, dav, prevenda, contacliente, dav_semimpressao, dav_fiscal, dav_naofiscal, queda_energia, nfe, perfil_v, perfil_w, perfil_t)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?, ?)";
	
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, numeroLaudo);
				ps.setString(2, s.getVersao());
				ps.setString(3, s.getNome());
				ps.setString(4, s.getLinguagem());
				ps.setString(5, s.getBancoDados());
				ps.setString(6, s.getSistemaOperacional());
				ps.setString(7, s.getDesenvolvimento());
				ps.setString(8, s.getFuncionamento());
				ps.setString(9, s.getSped());
				ps.setString(10, s.getConcomitante());
				ps.setString(11, s.getDav());
				ps.setString(12, s.getPv());
				ps.setString(13, s.getContaCliente());
				ps.setString(14, s.getDavSemImpressao());
				ps.setString(15, s.getDavECF());
				ps.setString(16, s.getDavNaoFiscal());
				ps.setString(17, s.getTratamentoQueda());
				ps.setString(18, s.getNfe());
				ps.setString(19, s.getPerfilV());
				ps.setString(20, s.getPerfilW());
				ps.setString(21, s.getPerfilT());
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
	
	public String txtSistema(String nrLaudo) {
		String sistema = "";
		try {
			Connection conn = Conexao.getConexao();
			String sql = "Select * from sistema where nrlaudo = " + nrLaudo;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				sistema += rs.getString("versao") + " | " + rs.getString("nome") + " | " + rs.getString("linguagem")
						+ " | " + rs.getString("banco_dados") + " | " + rs.getString("sistema_operacional")
						+ " | Desenvolvimento: " + rs.getString("desenvolvimento_finalidade") + " | Funcionamento: "
						+ rs.getString("funcionamento") + " | Geração Sped:" + rs.getString("geracao_sped")
						+ " | Concomitante: " + rs.getString("concomitante") + " | DAV: " + rs.getString("dav")
						+ " | Pré-venda:" + rs.getString("prevenda") + " | Conta Cliente: "
						+ rs.getString("contacliente") + " | DAV emitido sem impressão: "
						+ rs.getString("dav_semimpressao") + " | DAV impresso em ECF: " + rs.getString("dav_fiscal")
						+ " | DAV não fiscal: " + rs.getString("dav_naofiscal") + " | Tratamento queda de energia: "
						+ rs.getString("queda_energia") + " | NF-e: " + rs.getString("nfe") + " | Perfil V: "
						+ rs.getString("perfil_v") + " | Perfil T: " + rs.getString("perfil_T") + " | Perfil W: "
						+ rs.getString("perfil_W") + "\n";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "DADOS DO SISTEMA: " + sistema;

	}

	public void startComboLinguagem() {
		cbLinguagem.getItems().add("Java");
		cbLinguagem.getItems().add("Python");
		cbLinguagem.getItems().add("Delphi");
		cbLinguagem.getItems().add("C++");
		cbLinguagem.getItems().add("C#");
	}

	public void startComboBD() {
		cbBD.getItems().add("MySQL");
		cbBD.getItems().add("SQLite");
		cbBD.getItems().add("PostgreSQL");
		cbBD.getItems().add("Oracle");
		cbBD.getItems().add("SQL Server");
	}

	public void startComboSO() {
		cbSO.getItems().add("Windows");
		cbSO.getItems().add("Linux");
		cbSO.getItems().add("MacOS");
	}

}