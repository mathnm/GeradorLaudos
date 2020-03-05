package GeraLaudo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import util.Conexao;
import util.Constante;

public class SampleController {

	@FXML
	TextField txtNrLaudo;
	@FXML
	TabPane tabPane;

	public boolean laudoExiste = false;
	public String dados;

	public TextField getTxtNrLaudo() {
		return txtNrLaudo;
	}

	public void setTxtNrLaudo(TextField txtNrLaudo) {
		this.txtNrLaudo = txtNrLaudo;
	}

	public boolean telasAbertas, tela1Aberta, tela2Aberta, tela3Aberta, tela4Aberta, tela5Aberta, tela6Aberta,
			tela7Aberta, tela8Aberta, tela9Aberta, tela10Aberta = false;

	public void initialize() {
		
	}

	public void carregaLaudo() {
		boolean existe = false;
		try {
			Connection conn = Conexao.getConexao();
			String sql = "Select * from empresa";
			PreparedStatement ps = conn.prepareStatement(sql);// manda pro banco um comando
			ResultSet rs = ps.executeQuery(); // recebe dados do banco de dados
			while (rs.next()) { // enquanto houver dado na tabela:
				if (rs.getString("nrlaudo").equals(txtNrLaudo.getText())) {
					mostraAlert("Laudo já existe!");
					existe = true;
				}
			}
			
			if (txtNrLaudo.getText().equals("")) {
				mostraAlert("Informe o número do laudo!");
			} else {
				if(!existe) {
					Constante.nrLaudo = txtNrLaudo.getText();
					abreTelas();
				}	
			}	
			
		}catch (Exception e) {
			mostraAlert("Erro! \n" + e.toString());
		}
	}

	public void emitir() {
		// método para gerar um txt com os dados da empresa e o número do laudo

		try {
			Connection conn = Conexao.getConexao();
			String sql = "Select * from empresa";
			PreparedStatement ps = conn.prepareStatement(sql);// manda pro banco um comando
			ResultSet rs = ps.executeQuery(); // recebe dados do banco de dados
			while (rs.next()) { // enquanto houver dado na tabela:
				if (rs.getString("nrlaudo").equals(txtNrLaudo.getText())) {
					laudoExiste = true;
					try {
						// carrega controllers
						FXMLLoader loaderEmpresa = new FXMLLoader(getClass().getResource("DadosEmpresa.fxml"));
						loaderEmpresa.load();
						EmpresaController controllerEmpresa = loaderEmpresa.getController();

						FXMLLoader loaderSistema = new FXMLLoader(getClass().getResource("DadosSistema.fxml"));
						loaderSistema.load();
						SistemaController controllerSistema = loaderSistema.getController();

						FXMLLoader loaderLaudo = new FXMLLoader(getClass().getResource("DadosLaudo.fxml"));
						loaderLaudo.load();
						LaudoController controllerLaudo = loaderLaudo.getController();

						FXMLLoader loaderAplicacoes = new FXMLLoader(getClass().getResource("Aplicacoes.fxml"));
						loaderAplicacoes.load();
						AplicacoesController controllerAplicacoes = loaderAplicacoes.getController();

						FXMLLoader loaderRetaguarda = new FXMLLoader(getClass().getResource("DadosRetaguarda.fxml"));
						loaderRetaguarda.load();
						RetaguardaController controllerRetaguarda = loaderRetaguarda.getController();

						FXMLLoader loaderPED = new FXMLLoader(getClass().getResource("DadosPED.fxml"));
						loaderPED.load();
						DadosPEDController controllerPED = loaderPED.getController();

						FXMLLoader loaderEcf = new FXMLLoader(getClass().getResource("ECFs.fxml"));
						loaderEcf.load();
						ECFsController controllerEcf = loaderEcf.getController();

						FXMLLoader loaderArquivos = new FXMLLoader(getClass().getResource("ArqAutenticados.fxml"));
						loaderArquivos.load();
						ArqAutenticadosController controllerArquivos = loaderArquivos.getController();

						FXMLLoader loaderObservacoes = new FXMLLoader(getClass().getResource("Observacoes.fxml"));
						loaderObservacoes.load();
						ObservacoesController controllerObservacoes = loaderObservacoes.getController();

						FXMLLoader loaderInconsistencias = new FXMLLoader(
								getClass().getResource("Inconsistencias.fxml"));
						loaderInconsistencias.load();
						InconsistenciasController controllerInconsistencias = loaderInconsistencias.getController();

						FileWriter fw = new FileWriter("UNS"+txtNrLaudo.getText() + ".txt", true);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.append(controllerEmpresa.txtEmpresa(Constante.nrLaudo) + "\n"
								+ controllerSistema.txtSistema(Constante.nrLaudo) + "\n"
								+ controllerLaudo.txtLaudo(Constante.nrLaudo) + "\n"
								+ controllerAplicacoes.txtAplicacoes(Constante.nrLaudo) + "\n"
								+ controllerRetaguarda.txtRetaguarda(Constante.nrLaudo) + "\n"
								+ controllerPED.txtPed(Constante.nrLaudo) + "\n"
								+ controllerEcf.txtEcf(Constante.nrLaudo) + "\n"
								+ controllerArquivos.txtArquivos(Constante.nrLaudo) + "\n"
								+ controllerObservacoes.txtObservacoes(Constante.nrLaudo) + "\n"
								+ controllerInconsistencias.txtInconsistencia(Constante.nrLaudo));
						bw.close();
						fw.close();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (txtNrLaudo.getText().equals("")) {
			mostraAlert("Informe o número do laudo!");
		}

	}
	
	public void abreTelas() {
		abreTela1();
		abreTela2();
		abreTela3();
		abreTela4();
		abreTela5();
		abreTela6();
		abreTela7();
		abreTela8();
		abreTela9();
		abreTela10();
		telasAbertas = true;
	}

	@FXML
	public void abreTela1() {
		if (!tela1Aberta) {
			abreTab("Empresa", "DadosEmpresa.fxml");
			tela1Aberta = true;
		} else {
			try {
				// FXMLLoader loader = new
				// FXMLLoader(getClass().getResource("DadosEmpresa.fxml"));
				// loader.load();
				// EmpresaController controller = loader.getController();
				//
				// controller.carregaEmpresa(Constante.nrLaudo);

				// procurar uma forma de atualizar a parte visual da aba empresa, os dados estão
				// sendo capturados corretamente
				// fazer o mesmo para as outras classes
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	public void abreTela2() {
		if (!tela2Aberta) {
			abreTab("Sistema", "DadosSistema.fxml");
			tela2Aberta = true;
		} else {
			try {
				// FXMLLoader loader = new
				// FXMLLoader(getClass().getResource("DadosSistema.fxml"));
				// loader.load();
				// SistemaController controller = loader.getController();
				//
				// //controller.carregaSistema(Constante.nrLaudo); <- fazer um método para cada
				// classe
				// //procurar uma forma de atualizar a parte visual da aba empresa, os dados
				// estão sendo capturados corretamente
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	public void abreTela3() {
		if (!tela3Aberta) {
			abreTab("Laudo", "DadosLaudo.fxml");
			tela3Aberta = true;
		} else {
			try {
				// FXMLLoader loader = new
				// FXMLLoader(getClass().getResource("DadosLaudo.fxml"));
				// loader.load();
				// LaudoController controller = loader.getController();
				//
				// //controller.carregaLaudo(Constante.nrLaudo); <- fazer um método para cada
				// classe
				// //procurar uma forma de atualizar a parte visual da aba empresa, os dados
				// estão sendo capturados corretamente
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void abreTela4() {
		if (!tela4Aberta) {
			abreTab("Aplicações", "Aplicacoes.fxml");
			tela4Aberta = true;
		} else {
			try {
				// FXMLLoader loader = new
				// FXMLLoader(getClass().getResource("Aplicacoes.fxml"));
				// loader.load();
				// AplicacoesController controller = loader.getController();
				//
				// //controller.carregaAplicacoes(Constante.nrLaudo); <- fazer um método para
				// cada classe
				// //procurar uma forma de atualizar a parte visual da aba empresa, os dados
				// estão sendo capturados corretamente
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	public void abreTela5() {
		if (!tela5Aberta) {
			abreTab("Sistema de Retaguarda", "DadosRetaguarda.fxml");
			tela5Aberta = true;
		} else {
			try {
				// FXMLLoader loader = new
				// FXMLLoader(getClass().getResource("DadosRetaguarda.fxml"));
				// loader.load();
				// RetaguardaController controller = loader.getController();
				//
				// //controller.carregaRetaguarda(Constante.nrLaudo); <- fazer um método para
				// cada classe
				// //procurar uma forma de atualizar a parte visual da aba empresa, os dados
				// estão sendo capturados corretamente
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	public void abreTela6() {
		if (!tela6Aberta) {
			abreTab("Sistema PED", "DadosPED.fxml");
			tela6Aberta = true;
		} else {
			try {
				// FXMLLoader loader = new FXMLLoader(getClass().getResource("DadosPED.fxml"));
				// loader.load();
				// DadosPEDController controller = loader.getController();
				//
				// //controller.carregaPed(Constante.nrLaudo); <- fazer um método para cada
				// classe
				// //procurar uma forma de atualizar a parte visual da aba empresa, os dados
				// estão sendo capturados corretamente
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	public void abreTela7() {
		if (!tela7Aberta) {
			abreTab("ECFs", "ECFs.fxml");
			tela7Aberta = true;
		} else {
			try {
				// FXMLLoader loader = new FXMLLoader(getClass().getResource("ECFs.fxml"));
				// loader.load();
				// ECFsController controller = loader.getController();
				//
				// //controller.carregaEcfs(Constante.nrLaudo); <- fazer um método para cada
				// classe
				// //procurar uma forma de atualizar a parte visual da aba empresa, os dados
				// estão sendo capturados corretamente
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	public void abreTela8() {
		if (!tela8Aberta) {
			abreTab("Arquivos Autenticados", "ArqAutenticados.fxml");
			tela8Aberta = true;
		} else {
			try {
				// FXMLLoader loader = new
				// FXMLLoader(getClass().getResource("ArqAutenticados.fxml"));
				// loader.load();
				// ArqAutenticadosController controller = loader.getController();
				//
				// //controller.carregaArquivos(Constante.nrLaudo); <- fazer um método para cada
				// classe
				// //procurar uma forma de atualizar a parte visual da aba empresa, os dados
				// estão sendo capturados corretamente
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	public void abreTela9() {
		if (!tela9Aberta) {
			abreTab("Observações", "Observacoes.fxml");
			tela9Aberta = true;
		} else {
			try {
				// FXMLLoader loader = new
				// FXMLLoader(getClass().getResource("Observacoes.fxml"));
				// loader.load();
				// ObservacoesController controller = loader.getController();
				//
				// //controller.carregaObservacoes(Constante.nrLaudo); <- fazer um método para
				// cada classe
				// //procurar uma forma de atualizar a parte visual da aba empresa, os dados
				// estão sendo capturados corretamente
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	public void abreTela10() {
		if (!tela10Aberta) {
			abreTab("Inconsistências", "Inconsistencias.fxml");
			tela10Aberta = true;
		} else {
			try {
				// FXMLLoader loader = new
				// FXMLLoader(getClass().getResource("Inconsistencias.fxml"));
				// loader.load();
				// InconsistenciasController controller = loader.getController();
				//
				// //controller.carregaInconsistencias(Constante.nrLaudo); <- fazer um método
				// para cada classe
				// //procurar uma forma de atualizar a parte visual da aba empresa, os dados
				// estão sendo capturados corretamente
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void abreTab(String titulo, String path) {
		try {
			Tab tab = new Tab(titulo);
			tab.setClosable(true);
			tabPane.getTabs().add(tab);
			tab.setContent((Node) FXMLLoader.load(getClass().getResource(path)));
			selecionaTab(tab);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void selecionaTab(Tab tab) {
		tabPane.getSelectionModel().select(tab);
	}

	public void mostraMensagem(String msg) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setHeaderText(null);
		a.setContentText(msg);
		a.show();
	}

	public void mostraAlert(String msg) {
		Alert a = new Alert(AlertType.WARNING);
		a.setHeaderText(null);
		a.setContentText(msg);
		a.show();
	}
}

// permite que eu consiga acessar variáveis em outras telas
// public SampleController controller;
// public void start(Stage stage) {
// try {
// FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
// Parent root = (Parent) loader.load();
// this.controller = loader.getController();
// Scene scene = new Scene(root);
// stage.setScene(scene);
// stage.showAndWait();
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
