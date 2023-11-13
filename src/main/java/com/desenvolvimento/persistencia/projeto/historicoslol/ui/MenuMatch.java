package com.desenvolvimento.persistencia.projeto.historicoslol.ui;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desenvolvimento.persistencia.projeto.historicoslol.dao.ChampionDao;
import com.desenvolvimento.persistencia.projeto.historicoslol.dao.MatchDao;
import com.desenvolvimento.persistencia.projeto.historicoslol.dao.UserDao;
import com.desenvolvimento.persistencia.projeto.historicoslol.models.Champion;
import com.desenvolvimento.persistencia.projeto.historicoslol.models.Match;
import com.desenvolvimento.persistencia.projeto.historicoslol.models.User;

import javax.swing.*;

import java.util.List;

@Slf4j
@Component
public class MenuMatch {

	@Autowired
	private MatchDao baseMatch;

	@Autowired
	private UserDao baseUser;

	@Autowired
	private ChampionDao baseChampion;

	public void obterPartida(Match match) {
		List<User> usuarios = baseUser.findAll();
		List<Champion> championsList = baseChampion.findAll();
		User user = (User) JOptionPane.showInputDialog(null, "Selecione um usuario",
				"Usuarios", JOptionPane.PLAIN_MESSAGE, null, usuarios.toArray(), match.getUser());
		Champion main = (Champion) JOptionPane.showInputDialog(null, "Selecione um champion",
				"Champions", JOptionPane.PLAIN_MESSAGE, null, championsList.toArray(), match.getChampion());
		match.setUser(user);
		match.setChampion(main);
		user.addNoHistoric(match);
		baseUser.save(user);
		baseMatch.save(match);
	}

	public void listaPartidas(List<Match> partidas) {
		StringBuilder listagem = new StringBuilder();
		for (Match partida : partidas) {
			listagem.append(partida.toString()).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhuma partida encontrada" : listagem);
	}

	public void listaChampion(Champion champion) {
		JOptionPane.showMessageDialog(null, champion == null ? "Nenhum champion encontrado" : champion.toString());
	}

	public void listaChampion(List<Champion> champions) {
		StringBuilder listagem = new StringBuilder();
		for (Champion champion : champions) {
			listagem.append(champion.toString()).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum champion encontrado" : listagem);
	}

	public void listaPartidas(Match partida) {
		JOptionPane.showMessageDialog(null, partida == null ? "Nenhuma partida encontrado" : partida.toString());
	}

	public void listaUser(User usuario) {
		JOptionPane.showMessageDialog(null, usuario == null ? "Nenhum usuario encontrado" : usuario.toString());
	}

	public void listaUser(List<User> usuarios) {
		StringBuilder listagem = new StringBuilder();
		for (User usuario : usuarios) {
			listagem.append(usuario.toString()).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum usuario encontrado" : listagem);
	}

	public void menu() {
		StringBuilder menu = new StringBuilder("Menu Match\n")
				.append("1 - Inserir\n")
				.append("2 - Atualizar\n")
				.append("3 - Remover por id\n")
				.append("4 - Exibir todas\n")
				.append("5 - Exibir quantidade de partidas jogadas\n")
				.append("6 - Exibir o campeão mais jogado\n")
				.append("7 - Exibir o player que mais jogou\n")
				.append("8 - Exibir partidas jogadas por determinado campeão\n")
				.append("9 - Menu anterior");
		char opcao = '0';
		do {
			try {
				Match match;
				List<Match> partidas;
				int id;
				opcao = JOptionPane.showInputDialog(menu).charAt(0);
				switch (opcao) {
					case '1': // Inserir
						match = new Match();
						obterPartida(match);
						baseMatch.save(match);

						break;
					case '2': // Atualizar por Id
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id da partida a ser alterada"));
						match = baseMatch.findFirstById(id);
						if (match != null) {
							obterPartida(match);
							baseMatch.save(match);
						} else {
							JOptionPane.showMessageDialog(null,
									"Não foi possível atualizar, pois a partida não foi encontrado.");
						}
						break;
					case '3': // Remover por id
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id da partida a ser deletada"));
						match = baseMatch.findFirstById(id);
						if (match != null) {
							baseMatch.deleteById(id);
						} else {
							JOptionPane.showMessageDialog(null,
									"Não foi possível remover, pois a partida não foi encontrado.");
						}
						break;

					case '4':
						try {
							partidas = baseMatch.findAll();
							listaPartidas(partidas);
						} catch (Exception e) {
							log.error("Erro ao obter a lista de partidas: " + e.getMessage(), e);
							JOptionPane.showMessageDialog(null, "Erro ao obter a lista de partidas: " +
									e.getMessage());
						}
						break;

					case '5':
						int quantidade = baseMatch.conta();
						JOptionPane.showMessageDialog(null, "Foram jogadas " + quantidade + " Partidas");
						break;

					case '6':
						int maxPlayedCount = baseMatch.findMaxPlayedCount();
						JOptionPane.showMessageDialog(null,
								"Os campeões com mais partidas possuem " + maxPlayedCount + " Partidas jogadas");
						List<Champion> championsWithMaxPlayedCount = baseMatch
								.findChampionsWithMaxPlayedCount(maxPlayedCount);
						listaChampion(championsWithMaxPlayedCount);
						break;
					case '7':
						int maxPlayedCount2 = baseMatch.findMaxPlayedMatchesCount();
						JOptionPane.showMessageDialog(null,
								"Os players com mais partidas possuem " + maxPlayedCount2 + " Partidas jogadas");
						List<User> usersWithMaxPlayedMatches = baseMatch.findUsersWithMaxPlayedMatches(maxPlayedCount2);
						listaUser(usersWithMaxPlayedMatches);
						break;
					case '8':
						Champion buscado = (Champion) JOptionPane.showInputDialog(null, "Selecione um champion",
								"Champions", JOptionPane.PLAIN_MESSAGE, null, baseChampion.findAll().toArray(),
								null);
						listaPartidas(baseMatch.findByChampionName(buscado.getName()));
						break;
					case '9':
						break;
					default:
						JOptionPane.showMessageDialog(null, "Opção Inválida");
						break;
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			}

		} while (opcao != '9');
	}
}