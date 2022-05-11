package br.ufs.esii.toh.controller.usuario.usutickets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("usuario/usutickets/ticket")
public class TicketController {

	@RequestMapping("/")
	public String ticket() {
		return "usuario/usutickets/ticket";
	}
	
}
