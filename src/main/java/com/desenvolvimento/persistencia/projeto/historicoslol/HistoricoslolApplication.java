package com.desenvolvimento.persistencia.projeto.historicoslol;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.desenvolvimento.persistencia.projeto.historicoslol.ui.MenuChampion;
import com.desenvolvimento.persistencia.projeto.historicoslol.ui.MenuMatch;
import com.desenvolvimento.persistencia.projeto.historicoslol.ui.MenuUser;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(scanBasePackages = "com.desenvolvimento.persistencia.projeto.historicoslol")
@EntityScan("com.desenvolvimento.persistencia.projeto.historicoslol.models")
//@EnableJpaRepositories("com.desenvolvimento.persistencia.projeto.historicoslol.dao.jpa")
@EnableMongoRepositories("com.desenvolvimento.persistencia.projeto.historicolol.dao.mongo")

@Slf4j
public class HistoricoslolApplication implements CommandLineRunner {
	@Autowired
	private MenuChampion menuChampion;

	@Autowired
	private MenuUser menuUser;

	@Autowired
	private MenuMatch menuMatch;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(HistoricoslolApplication.class);
		builder.headless(false).run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		StringBuilder menu = new StringBuilder("Menu Principal\n")
			.append("1 - Champions\n")
			.append("2 - Users\n")
			.append("3 - Matches\n")
			.append("4 - Sair");
		char opcao = '0';
		do {
			try {
				opcao = JOptionPane.showInputDialog(menu).charAt(0);
				switch (opcao) {
					case '1':     // Clientes
						menuChampion.menu();
						break;
					case '2':
						menuUser.menu();
						break;
					case '3':
						menuMatch.menu();
						break;
					
					case '4':
						break;
					default:
						JOptionPane.showMessageDialog(null, "Opção Inválida");
						break;
					}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			}

		} while(opcao != '4');
	}
}
