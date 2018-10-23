package br.com.alura.listavip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.alura.listavip.model.Convidado;
import br.com.alura.listavip.service.ConvidadoService;
import br.com.alura.listavip.service.EmailService;

@Controller
public class ConvidadoController {

	@Autowired
	private ConvidadoService convidadoService;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	//mandar a lista p view, deve-se usar o model
	@RequestMapping("listaconvidados")
	public String listaConvidados(Model model) {
		//vai retornar todos os resultados da tabela
		Iterable<Convidado> convidados = convidadoService.obterTodos();
		
		model.addAttribute("convidados", convidados);

		return "listaconvidados";
	}
	//recebe os dados vindos do formulário
	@RequestMapping(value="salvar", method = RequestMethod.POST)
	public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email,
			@RequestParam("telefone") String telefone, Model model) {
		//cria um objeto convidado
		Convidado novoConvidado = new Convidado(nome, email, telefone);
		//salva o objeto criado com o repository
		convidadoService.salvar(novoConvidado);
		
		new EmailService().enviar(nome, email);
		//recarrega os convidados para serem listados novamente
		Iterable<Convidado> convidados = convidadoService.obterTodos();
		model.addAttribute("convidados", convidados);
		
		return "listaconvidados";
	}
	
	//deletar um convidado usando o metodo herdado da interface repository
//	public void deletar(Long idConvidado) {
//		Convidado convidado = convidadoRepository.findOne(idConvidado);
//		convidadoRepository.delete(convidado);
//	}
//	
//	public void obterConvidadoPorNome(String nome) {
//		convidadoRepository.findByNome(nome);
//	}
}
