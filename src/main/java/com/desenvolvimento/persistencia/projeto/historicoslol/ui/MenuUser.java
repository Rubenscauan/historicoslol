package com.desenvolvimento.persistencia.projeto.historicoslol.ui;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desenvolvimento.persistencia.projeto.historicoslol.dao.UserDao;
import com.desenvolvimento.persistencia.projeto.historicoslol.models.User;

import javax.swing.*;

import java.util.List;

@Slf4j
@Component
public class MenuUser {

	@Autowired
	private UserDao baseUser;

	public void obterUsuario(User user) {

		String name = JOptionPane.showInputDialog("Name", user.getName());
		String password = JOptionPane.showInputDialog("Password", user.getPassword());

		user.setName(name);
		user.setPassword(password);
	}

	public void listaUser(List<User> usuarios) {
		StringBuilder listagem = new StringBuilder();
		for (User usuario : usuarios) {
			listagem.append(usuario.toString()).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum usuario encontrado" : listagem);
	}

	public void listaHistorico(User usuario) {
		JOptionPane.showMessageDialog(null,
				usuario == null ? "Nenhum usuario encontrado" : usuario.toStringComHistorico());
	}

	public void listaUser(User usuario) {
		JOptionPane.showMessageDialog(null, usuario == null ? "Nenhum usuario encontrado" : usuario.toString());
	}

	public void menu() {
		StringBuilder menu = new StringBuilder("Menu Usuario\n")
				.append("1 - Inserir\n")
				.append("2 - Atualizar\n")
				.append("3 - Remover por nome\n")
				.append("4 - Exibir Por Caractere\n")
				.append("5 - Exibir Historico\n")
				.append("6 - Exibir todos\n")
				.append("7 - Exibir quantidade de usuarios\n")
				.append("8 - Menu anterior");

		char opcao = '0';
		do {
			try {
				User usuario;
				List<User> usuarios;
				String nome;
				opcao = JOptionPane.showInputDialog(menu).charAt(0);
				switch (opcao) {
					case '1': // Inserir
						usuario = new User();
						obterUsuario(usuario);
						baseUser.save(usuario);
						break;
					case '2': // Atualizar por Nome
						nome = JOptionPane.showInputDialog("Digite o Nome do usuario a ser alterado");
						usuario = baseUser.findFirstByName(nome);
						if (usuario != null) {
							obterUsuario(usuario);
							baseUser.save(usuario);
						} else {
							JOptionPane.showMessageDialog(null,
									"Não foi possível atualizar, pois o usuario não foi encontrado.");
						}
						break;
					case '3': // Remover por nome
						nome = JOptionPane.showInputDialog("Nome");
						usuario = baseUser.findFirstByName(nome);
						if (usuario != null) {
							baseUser.deleteById(usuario.getId());
						} else {
							JOptionPane.showMessageDialog(null,
									"Não foi possível remover, pois o usuario não foi encontrado.");
						}
						break;
					case '4': // Buscar por main
						nome = JOptionPane.showInputDialog("Digite algo para busca do usuario:");
						// Use o UserDao para buscar usuários pelo campeão principal
						usuarios = baseUser.buscaPorNameContendoString(nome);
						listaUser(usuarios);
						break;
					case '5':
						User user = (User) JOptionPane.showInputDialog(null, "Selecione um usuario",
								"Usuarios", JOptionPane.PLAIN_MESSAGE, null, baseUser.findAll().toArray(), null);
						if (user!= null) {
							listaHistorico(user);
						} else {
							JOptionPane.showMessageDialog(null,
									"Não foi possível remover, pois o usuario não foi encontrado.");
						}
						break;
					case '6':
						try {
							usuarios = baseUser.findAll();
							listaUser(usuarios);
						} catch (Exception e) {
							log.error("Erro ao obter a lista de usuários: " + e.getMessage(), e);
							JOptionPane.showMessageDialog(null, "Erro ao obter a lista de usuários: " + e.getMessage());
						}
						break;

					case '7':
						int quantidade = baseUser.conta();
						JOptionPane.showMessageDialog(null, "Foram cadastrados " + quantidade + " usuarios");
						break;
					case '8':
						break;
					default:
						JOptionPane.showMessageDialog(null, "Opção Inválida");
						break;
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			}

		} while (opcao != '8');
	}
}