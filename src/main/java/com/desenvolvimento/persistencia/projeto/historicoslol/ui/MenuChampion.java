package com.desenvolvimento.persistencia.projeto.historicoslol.ui;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desenvolvimento.persistencia.projeto.historicoslol.models.Champion;
import com.desenvolvimento.persistencia.projeto.historicoslol.dao.ChampionDao;
import javax.swing.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
public class MenuChampion {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	@Autowired
	private ChampionDao baseChampion;

	public void obterChampion(Champion champion) {
		String name = JOptionPane.showInputDialog("Name", champion.getName());
		String position = JOptionPane.showInputDialog("Position", champion.getPosition());
		String range = JOptionPane.showInputDialog("Range", champion.getRange());
        String region = JOptionPane.showInputDialog("Region", champion.getRegion());
        String resource = JOptionPane.showInputDialog("Resource", champion.getResource());
    
		champion.setName(name);
        champion.setPosition(position);
        champion.setRange(range);
        champion.setRegion(region);
        champion.setResource(resource);
        champion.setCreationDate(LocalDateTime.now().format(formatter));
	}

	public void listaChampion(List<Champion> champions) {
		StringBuilder listagem = new StringBuilder();
		for(Champion champion : champions) {
			listagem.append(champion.toString()).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum champion encontrado" : listagem);
	}

	public void listaChampion(Champion champion) {
		JOptionPane.showMessageDialog(null, champion == null ? "Nenhum champion encontrado" : champion.toString());
	}

	public void menu() {
		StringBuilder menu = new StringBuilder("Menu Champions\n")
			.append("1 - Inserir\n")
			.append("2 - Atualizar por nome\n")
			.append("3 - Remover por nome\n")
			.append("4 - Exibir por Determinados Caracteres\n")
			.append("5 - Exibir por região\n")
            .append("6 - Exibir por recurso\n")
            .append("7 - Exibir Quantidade de campeões cadastrados\n")
			.append("8 - Exibir todos\n")
			.append("9 - Menu anterior");
		char opcao = '0';
		do {
			try {
				Champion champion;
                List<Champion> champions;
				String nome;
				opcao = JOptionPane.showInputDialog(menu).charAt(0);
				switch (opcao) {
					case '1':     // Inserir
						champion = new Champion();
						obterChampion(champion);
						baseChampion.save(champion);
						break;
					case '2':     // Atualizar por Nome
						nome = JOptionPane.showInputDialog("Digite o Nome do champion a ser alterado");
						champion = baseChampion.findFirstByName(nome);
						if (champion != null) {
							obterChampion(champion);
							baseChampion.save(champion);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi possível atualizar, pois o champion não foi encontrado.");
						}
						break;
					case '3':     // Remover por nome
						nome = JOptionPane.showInputDialog("Nome");
						champion = baseChampion.findFirstByName(nome);
						if (champion != null) {
							baseChampion.deleteById(champion.getId());
						} else {
							JOptionPane.showMessageDialog(null, "Não foi possível remover, pois o champion não foi encontrado.");
						}
						break;
					case '4':    
						nome = JOptionPane.showInputDialog("Nome");
						
						champions = baseChampion.buscaPorNameContendoString(nome);
						listaChampion(champions);
						break;
					case '5':     
						String region = JOptionPane.showInputDialog("Região");
						champions = baseChampion.findByRegion(region);
						listaChampion(champions);
						break;
                    case '6':     
                        String range = JOptionPane.showInputDialog("Range");
                        champions = baseChampion.findByRange(range);
                        listaChampion(champions);
                        break; 
					case '7':  
                        int quantidade = baseChampion.conta();
						JOptionPane.showMessageDialog(null,"Foram cadastrados " + quantidade + " Campeões");
						break;   
					case '8':
						listaChampion(baseChampion.findAll());
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

		} while(opcao != '9');
	}
}