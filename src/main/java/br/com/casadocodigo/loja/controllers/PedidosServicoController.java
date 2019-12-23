package br.com.casadocodigo.loja.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.models.Pedido;

@Controller
public class PedidosServicoController {

  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping("/pedidos")
  public ModelAndView pedidos() {
    String url = "https://book-payment.herokuapp.com/orders";
    
    ModelAndView mav = new ModelAndView("/pedidos");
    
    Pedido[] resposta = restTemplate.getForObject(url, Pedido[].class);
    List<Pedido> pedidos = Arrays.asList(resposta);
    System.out.println(pedidos);
    
    mav.addObject("pedidos", pedidos);

    return mav;
  }

}
